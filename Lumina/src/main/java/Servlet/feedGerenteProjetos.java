package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.FeedDao;

@WebServlet("/feedGerenteProjetos")
public class feedGerenteProjetos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		FeedDao dao = new FeedDao();
		if (session != null) {
			String usuarioLogado = (String) session.getAttribute("usuarioLogado");
			session.setAttribute("usuarioLogado", usuarioLogado);

			String nome = dao.buscaNome(usuarioLogado);
			request.setAttribute("nome", nome);
			List<Entidades.Projetos> lista = dao.buscaProjetos(usuarioLogado);
			request.setAttribute("projetos", lista);
			System.out.println("Projetos encontrados: " + lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("feedGerenteProjetos.jsp");
			rd.forward(request, response);
		}

		else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		}

	}

}
