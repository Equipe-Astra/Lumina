package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

import Dao.FeedDao;
import Dto.ComentarioDTO;
import Dto.PublicacoesDTO;
import Entidades.Area;
import Entidades.Funcionarios;
import Entidades.LucroProjeto;
import Entidades.Publicacao;
import Service.GeminiService;

@WebServlet("/Gemini")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 100)
public class GeminiServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		String usuarioLogado = (String) session.getAttribute("usuarioLogado");
		
		FeedDao dao = new FeedDao();
		
		String caminhoImagem;
		
			caminhoImagem = dao.buscaFotoPerfil(usuarioLogado, getServletContext());
			request.setAttribute("caminhoFotoPerfil", caminhoImagem);
		
		String nome = dao.buscaNome(usuarioLogado);
		request.setAttribute("nome", nome);
		
		List<Entidades.Projetos> lista = dao.buscaProjetos(usuarioLogado);
		request.setAttribute("projetos", lista);
		
		Funcionarios funcionario = new Funcionarios();
		funcionario.setId(usuarioLogado);
		
		Area area = new Area();
		area.setIdArea(dao.buscaArea(usuarioLogado));
		
		List<PublicacoesDTO> publicacoes;
		try {
			publicacoes = dao.buscaPublicacao(usuarioLogado);
			request.setAttribute("publicacoes", publicacoes);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<ComentarioDTO> comentarios;
		try {
			comentarios = dao.buscaComentarios(getServletContext());
			request.setAttribute("comentarios", comentarios);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Part arquivo = request.getPart("arquivo");
		String nomeArquivo = arquivo.getSubmittedFileName();
		String extensao = nomeArquivo.substring(nomeArquivo.lastIndexOf(".") + 1).toLowerCase();
		String acao = request.getParameter("acao");

		InputStream fileContent = arquivo.getInputStream();
		StringBuilder textoExtraido = new StringBuilder();

		if (session == null || session.getAttribute("usuarioLogado") == null) {
		    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		    rd.forward(request, response);
		    return;
		}
		
		else{
			if ("gemini".equals(acao)) {
				try {
					switch (extensao) {
					case "pdf":
						try (PDDocument document = PDDocument.load(fileContent)) {
							PDFTextStripper stripper = new PDFTextStripper();
							textoExtraido.append(stripper.getText(document));
						}
						break;

					case "csv":
						try (BufferedReader br = new BufferedReader(new InputStreamReader(fileContent))) {
							String linha;
							while ((linha = br.readLine()) != null) {
								textoExtraido.append(linha).append("\n");
							}
						}
						break;
						
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

				String prompt = "Separe o seguinte texto nos tópicos Descrição do projeto, Objetivos, Resultados, Lucro (o título dos tópicos devem ser os especificados anteriormente) e faça uma publicação completa e um pouco informal, mas com seriedade, pois os gestores irão ver e acompanhar os resultados por esse post, para o feed da empresa (quero apenas o texto, não coloque trechos como 'aqui está um texto...'). O campo de lucro deve ser preenchido apenas com um valor numérico (essa informação vai para o banco de dados para um campo number), se não houver essa informação no texto, preencher com 0, essa deve ser a ÚLTIMA informção do texto, não pode vir nada a mais abaixo dela. Por gentileza, não deixar lacunas para o usuário preencher (como: [cite aqui exemplos...])"
						+ texto;

				String json = GeminiService.enviarPrompt(prompt);

				JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
				String textoGerado = jsonObj.getAsJsonArray("candidates").get(0).getAsJsonObject()
						.getAsJsonObject("content").getAsJsonArray("parts").get(0).getAsJsonObject().get("text")
						.getAsString();

				textoGerado = textoGerado.replaceAll("(\\*\\*|\\*|__|_)", "").trim();
				textoGerado = textoGerado.replaceAll("(\\##\\##|\\##|__|_)", "").trim();
				textoGerado = textoGerado.replaceAll("\\n{2,}", "\n\n").trim();


				String descricao = "", objetivos = "", resultados = "", lucro = "";

				String regex = "(?is)" +
					    "Descrição do projeto:??\\s*(?<descricao>.*?)(?=\\n\\s*Objetivos:??|\\n\\s*Resultados:??|\\n\\s*Lucro:??|$)" +
					    "(?:\\n\\s*Objetivos:??\\s*(?<objetivos>.*?)(?=\\n\\s*Resultados:??|\\n\\s*Lucro:??|$))?" +
					    "(?:\\n\\s*Resultados:??\\s*(?<resultados>.*?)(?=\\n\\s*Lucro:??|$))?" +
					    "(?:\\n\\s*Lucro:??\\s*(?<lucro>.*))?";


				
				System.out.println(textoGerado);

				Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
				Matcher matcher = pattern.matcher(textoGerado);

				if (matcher.find()) {
				    descricao = matcher.group("descricao") != null ? matcher.group("descricao").trim() : "";
				    objetivos = matcher.group("objetivos") != null ? matcher.group("objetivos").trim() : "";
				    resultados = matcher.group("resultados") != null ? matcher.group("resultados").trim() : "";
				    lucro = matcher.group("lucro") != null ? matcher.group("lucro").trim() : "";
				}

				request.setAttribute("descricao", descricao);
				request.setAttribute("objetivos", objetivos);
				request.setAttribute("resultados", resultados);
				request.setAttribute("lucro", lucro);
				
				request.setAttribute("abrirModal", true);
				request.getRequestDispatcher("feedGerenteProjetos.jsp").forward(request, response);
			}
			else if ("manual".equals(acao)) {
				Publicacao publicacao = new Publicacao();
				LucroProjeto lucro = new LucroProjeto();
				
				Part imagem = request.getPart("imagemProjeto");
				if (imagem != null && imagem.getSize() > 0) {
				    publicacao.setImagem(imagem.getInputStream().readAllBytes());
				}
				
				String descricao = request.getParameter("descricao");
				String objetivos = request.getParameter("objetivos");
				String resultados = request.getParameter("resultados");
				String idProjeto = request.getParameter("idProjeto");
				String lucros = request.getParameter("lucro").replaceAll("[^0-9.]", "");
				
				if(lucros.isEmpty()) {
				    lucros = "0";
				}
				lucro.setLucro(Double.parseDouble(lucros));
				
				lucro.setIdProjeto(dao.buscaProjeto(idProjeto));
				publicacao.setIdFuncionario(funcionario);
				publicacao.setIdArea(area);
				publicacao.setIdProjeto(dao.buscaProjeto(idProjeto));
				publicacao.setDescricao(descricao);
				publicacao.setObjetivos(objetivos);
				publicacao.setResultados(resultados);
				
				dao.cadastraLucro(lucro);
				dao.cadastraPublicacao(publicacao);
				
				response.sendRedirect("feedGerenteProjetos");
			}
		}
	}
}