package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.LoginDao;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("password");

		LoginDao dao = new LoginDao();

		boolean loginSuccess = false;
		String usuarioValido = dao.buscaUsuario(email);

		if (usuarioValido != null && dao.buscaSenha(email).equals(senha)) {
			String cargo = dao.buscaCargo(usuarioValido);
			loginSuccess = true;
			HttpSession session = request.getSession();
			session.setAttribute("usuarioLogado", usuarioValido);
			
			if (cargo.equalsIgnoreCase("gerente de projetos")) {

				response.sendRedirect("/Lumina/feedGerenteProjetos");
			} else if (cargo.equalsIgnoreCase("gerente")) {
				response.sendRedirect("/Lumina/feedGerente.html");
			}
			else if (cargo.equalsIgnoreCase("executivo")) {
				response.sendRedirect("/Lumina/feedExecutivo.html");
			}
			else if (cargo.equalsIgnoreCase("colaborador euron")) {
				response.sendRedirect("/Lumina/feedColaboradorEuron.html");
			}
			else if (cargo.equalsIgnoreCase("colaborador eurofarma")) {
				response.sendRedirect("/Lumina/feedColaboradorEurofarma.html");
			}
		}
		else if(usuarioValido == null) {
			request.setAttribute("erro", "Funcionário não cadastrado");
			 request.setAttribute("email", email);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		else if(dao.buscaSenha(email) != (senha)) {
			request.setAttribute("erro", "Senha incorreta");
			 request.setAttribute("email", email);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

}
