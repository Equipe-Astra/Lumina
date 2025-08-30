package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.FeedDao;
import Dto.ComentarioDTO;
import Dto.PublicacoesDTO;
import Entidades.Comentarios;
import Entidades.Funcionarios;
import Entidades.Publicacao;

@WebServlet("/feedGerenteProjetos")
public class feedGerenteProjetos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");

		FeedDao dao = new FeedDao();
		if (session != null) {
			String usuarioLogado = (String) session.getAttribute("usuarioLogado");
			session.setAttribute("usuarioLogado", usuarioLogado);
			
			String caminhoImagem;
			
				caminhoImagem = dao.buscaFotoPerfil(usuarioLogado, getServletContext());
				request.setAttribute("caminhoFotoPerfil", caminhoImagem);

			
			
			String nome = dao.buscaNome(usuarioLogado);
			request.setAttribute("nome", nome);

			List<Entidades.Projetos> lista = dao.buscaProjetos(usuarioLogado);
			request.setAttribute("projetos", lista);

			List<PublicacoesDTO> publicacoes;
			try {
				publicacoes = dao.buscaPublicacao();
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

			RequestDispatcher rd = request.getRequestDispatcher("feedGerenteProjetos.jsp");
			rd.forward(request, response);
		}

		else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

	}

}
