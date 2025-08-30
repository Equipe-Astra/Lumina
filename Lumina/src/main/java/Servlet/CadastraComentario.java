package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.FeedDao;
import Entidades.Comentarios;
import Entidades.Funcionarios;
import Entidades.Publicacao;


@WebServlet("/CadastraComentario")
public class CadastraComentario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);

		String usuarioLogado = (String) session.getAttribute("usuarioLogado");

		if (session != null && usuarioLogado != null) {
			FeedDao dao = new FeedDao();

			Funcionarios funcionario = new Funcionarios();
			funcionario.setId(usuarioLogado);

			String idPublicacao = request.getParameter("publicacao");
			String descricao = request.getParameter("descricao");

			Publicacao publicacao = new Publicacao();
			publicacao.setIdPublicacao(Long.parseLong(idPublicacao));

			Comentarios comentario = new Comentarios();
			comentario.setDescricao(descricao);
			comentario.setIdPublicacao(publicacao);
			comentario.setIdFuncionario(funcionario);

			dao.cadastraComentario(comentario);
			
			response.sendRedirect("feedGerenteProjetos");

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}

	}
}