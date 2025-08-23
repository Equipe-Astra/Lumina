package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Service.GeminiService;

@WebServlet("/Gemini")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 50,
    maxRequestSize = 1024 * 1024 * 100
)
public class GeminiServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        Part filePart = request.getPart("arquivo");
        String fileName = filePart.getSubmittedFileName();
        String extensao = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        InputStream fileContent = filePart.getInputStream();
        StringBuilder textoExtraido = new StringBuilder();

        try {
            switch (extensao) {
                // PDF
                case "pdf":
                    try (PDDocument document = PDDocument.load(fileContent)) {
                        PDFTextStripper stripper = new PDFTextStripper();
                        textoExtraido.append(stripper.getText(document));
                    }
                    break;

                // CSV
                case "csv":
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(fileContent))) {
                        String linha;
                        while ((linha = br.readLine()) != null) {
                            textoExtraido.append(linha).append("\n");
                        }
                    }
                    break;

                // XLS / XLSX
                case "xls":
                case "xlsx":
                    try (Workbook workbook = WorkbookFactory.create(fileContent)) {
                        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                            Sheet sheet = workbook.getSheetAt(i);
                            for (Row row : sheet) {
                                for (Cell cell : row) {
                                    switch (cell.getCellType()) {
                                        case STRING:
                                            textoExtraido.append(cell.getStringCellValue()).append(" ");
                                            break;
                                        case NUMERIC:
                                            textoExtraido.append(cell.getNumericCellValue()).append(" ");
                                            break;
                                        default:
                                            break;
                                    }
                                }
                                textoExtraido.append("\n");
                            }
                        }
                    }
                    break;

 
                case "docx":
                    try (XWPFDocument doc = new XWPFDocument(fileContent)) {
                        for (XWPFParagraph p : doc.getParagraphs()) {
                            textoExtraido.append(p.getText()).append("\n");
                        }
                    }
                    break;

                // PPTX
                case "pptx":
                    try (XMLSlideShow ppt = new XMLSlideShow(fileContent)) {
                        for (XSLFSlide slide : ppt.getSlides()) {
                            for (XSLFShape shape : slide.getShapes()) {
                                if (shape instanceof XSLFTextShape) {
                                    textoExtraido.append(((XSLFTextShape) shape).getText()).append("\n");
                                }
                            }
                        }
                    }
                    break;

                default:
                    throw new ServletException("Tipo de arquivo não suportado: " + extensao);
            }
        } catch (Exception e) {
            throw new ServletException("Erro ao processar arquivo: " + e.getMessage(), e);
        }

        String texto = textoExtraido.toString();
        
        String prompt = "Separe o seguinte texto nos tópicos Descrição do projeto, Objetivos, Resultados (o título do tópico deve ser o nome dele) e faça uma publicação completa e um pouco informal, mas com seriedade, pois os gestores irão ver e acompanhar os resultados por esse post, para o feed da empresa (quero apenas o texto, não coloque trechos como 'aqui está um texto...'). Por gentileza, não deixar lacunas para o usuário preencher (como: [cite aqui exemplos...])"
                      + texto;

        String json = GeminiService.enviarPrompt(prompt);

        JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
        String textoGerado = jsonObj
            .getAsJsonArray("candidates").get(0).getAsJsonObject()
            .getAsJsonObject("content")
            .getAsJsonArray("parts").get(0).getAsJsonObject()
            .get("text").getAsString();

       
        textoGerado = textoGerado.replaceAll("(\\*\\*|\\*|__|_)", "").trim();
        textoGerado = textoGerado.replaceAll("\\n{2,}", "\n\n").trim();

        String descricao = "";
        String objetivos = "";
        String resultados = "";

        String[] partes = textoGerado.split("(?i)(?=Objetivos:|Resultados:)");

        if (partes.length > 0) descricao = partes[0].replaceFirst("(?i)Descrição do projeto:", "").trim();
        if (partes.length > 1) objetivos = partes[1].replaceFirst("(?i)Objetivos:", "").trim();
        if (partes.length > 2) resultados = partes[2].replaceFirst("(?i)Resultados:", "").trim();

        request.setAttribute("descricao", descricao);
        request.setAttribute("objetivos", objetivos);
        request.setAttribute("resultados", resultados);
        request.getRequestDispatcher("feedGerenteProjetos.jsp").forward(request, response);
    }
}
