package Servlet;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextShape;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("arquivo");
        InputStream fileContent = filePart.getInputStream();

        // Lendo PPTX
        StringBuilder textoExtraido = new StringBuilder();
        try (XMLSlideShow ppt = new XMLSlideShow(fileContent)) {
            for (XSLFSlide slide : ppt.getSlides()) {
                for (XSLFShape shape : slide.getShapes()) {
                    if (shape instanceof XSLFTextShape) {
                        textoExtraido.append(((XSLFTextShape) shape).getText()).append("\n");
                    }
                }
            }
        }

        String textoDoPPT = textoExtraido.toString();

        // Monta o prompt
        String prompt = "Separe o seguinte texto nos tópicos abaixo e faça uma publicação completa e um pouco informal, mas com seriedade, pois os gestores irão ver e acompanhar os resultados por esse post, para o feed da empresa (quero apenas o texto, não coloque trechos como 'aqui está um texto...'). Por gentileza, não deixar lacunas para o usuário preencher: "
                      + "Descrição do projeto, Objetivos e Resultados.\n\n"
                      + textoDoPPT;

        // Chama o Gemini API
        String json = GeminiService.enviarPrompt(prompt);

        JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
        String textoGerado = jsonObj
            .getAsJsonArray("candidates").get(0).getAsJsonObject()
            .getAsJsonObject("content")
            .getAsJsonArray("parts").get(0).getAsJsonObject()
            .get("text").getAsString();

        // Remove * e ** de Markdown e outros símbolos de itálico/negrito
        textoGerado = textoGerado.replaceAll("(\\*\\*|\\*|__|_)", "").trim();

        // Remove múltiplas quebras de linha seguidas (2 ou mais) e deixa só uma quebra
        textoGerado = textoGerado.replaceAll("\\n{2,}", "\n\n");

        // Remove espaços extras no início e fim
        textoGerado = textoGerado.trim();


        // Agora separa por tópicos
        String descricao = "";
        String objetivos = "";
        String resultados = "";

        String[] partes = textoGerado.split("(?i)(?=Objetivos:|Resultados:)");

        if (partes.length > 0) descricao = partes[0].replaceFirst("(?i)Descrição do projeto:", "").trim();
        if (partes.length > 1) objetivos = partes[1].replaceFirst("(?i)Objetivos:", "").trim();
        if (partes.length > 2) resultados = partes[2].replaceFirst("(?i)Resultados:", "").trim();

        // Envia para o JSP
        request.setAttribute("descricao", descricao);
        request.setAttribute("objetivos", objetivos);
        request.setAttribute("resultados", resultados);
        request.getRequestDispatcher("feedGerenteProjetos.jsp").forward(request, response);
    }
}
