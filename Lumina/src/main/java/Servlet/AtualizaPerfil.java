package Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Dao.FeedDao;
import Entidades.Funcionarios;

@WebServlet("/AtualizaPerfil")
@MultipartConfig
public class AtualizaPerfil extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Funcionarios funcionario = new Funcionarios();

		HttpSession session = request.getSession();
		String usuarioLogado = (String) session.getAttribute("usuarioLogado");

		if (session == null || session.getAttribute("usuarioLogado") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		} else {
			Part imagem = request.getPart("fotoPerfil");
			if (imagem != null && imagem.getSize() > 0) {
				funcionario.setFoto(imagem.getInputStream().readAllBytes());
			}
			
			funcionario.setId(usuarioLogado);

			FeedDao dao = new FeedDao();
			dao.cadastraFotoPerfil(funcionario);
			response.sendRedirect("feedGerenteProjetos");
		}

	}
}
