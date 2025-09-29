package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CadastroDao;
import Entidades.Area;
import Entidades.Cargo;
import Entidades.Funcionarios;

@WebServlet("/Cadastro")
public class Cadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Funcionarios funcionario = new Funcionarios();
		CadastroDao dao = new CadastroDao();
		Cargo cargoFunc = new Cargo();

		String matricula = request.getParameter("matricula");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String cargo = request.getParameter("cargo");
		String area = request.getParameter("area");
		String senha = request.getParameter("password");
		String confsenha = request.getParameter("confpassword");
		
		request.setAttribute("matricula", matricula);
		request.setAttribute("nome", nome);
		request.setAttribute("email", email);
		request.setAttribute("cargo", cargo);
		request.setAttribute("area", area);
		

		Cargo cargoId = dao.buscaCargo(cargo);
		Area areaId = dao.buscaArea(area);
		Integer usuarioExistente = dao.buscaUsuario(matricula);
		Integer emailExistente = dao.buscaEmail(email);
		
		
		if (senha.equals(confsenha)) {
			if (!email.endsWith("@euron.com.br") && !email.endsWith("@eurofarma.com.br")) {
				request.setAttribute("erro", "O e-mail deve ser corporativo");
				request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
				return;
			} else if (!(usuarioExistente == null)){
				request.setAttribute("erro", "Matrícula já cadastrada");
				request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
			}
			else if(!(emailExistente == null)) {
				request.setAttribute("erro", "E-mail já cadastrado");
				request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
			}
			else {
			
				funcionario.setId(matricula);
				funcionario.setNome(nome);
				funcionario.setEmail(email);
				funcionario.setIdCargo(cargoId);
				funcionario.setIdArea(areaId);
				funcionario.setSenha(confsenha);

				dao.cadastraFuncionario(funcionario);

				response.sendRedirect("/Lumina/login.jsp");

			}
		}
		else {
			request.setAttribute("erro", "As senhas não conferem");
			request.getRequestDispatcher("/cadastro.jsp").forward(request, response);
		}

	}

}
