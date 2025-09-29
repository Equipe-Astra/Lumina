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

@WebServlet("/Reacoes")
public class Reacoes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String usuarioLogado = (String) session.getAttribute("usuarioLogado");
		String idPublicacao = request.getParameter("idPublicacao");
		String tipo = request.getParameter("tipo");
		String acao = request.getParameter("acao");

		if (session == null || session.getAttribute("usuarioLogado") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		} else {

			FeedDao dao = new FeedDao();

			dao.cadastraReacao(idPublicacao, tipo, acao, usuarioLogado);

			response.sendRedirect("feedGerenteProjetos");
		}
	}

}
