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
import Entidades.Projetos;

@WebServlet("/feedColaboradorEurofarma")
public class feedColaboradorEurofarma extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final FeedDao dao = new FeedDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioLogado") == null) {
            forward(request, response, "login.jsp");
            return;
        }

        String usuarioLogado = (String) session.getAttribute("usuarioLogado");
        session.setAttribute("usuarioLogado", usuarioLogado);

        try {

            String caminhoImagem = dao.buscaFotoPerfil(usuarioLogado, getServletContext());
            String nome = dao.buscaNome(usuarioLogado);
            List<Projetos> projetos = dao.buscaProjetos(usuarioLogado);
            List<PublicacoesDTO> publicacoes = dao.buscaPublicacao(usuarioLogado);
            List<ComentarioDTO> comentarios = dao.buscaComentarios(getServletContext());

            request.setAttribute("caminhoFotoPerfil", caminhoImagem);
            request.setAttribute("nome", nome);
            request.setAttribute("projetos", projetos);
            request.setAttribute("publicacoes", publicacoes);
            request.setAttribute("comentarios", comentarios);

            forward(request, response, "feedColaboradorEurofarma.jsp");

        } catch (SQLException e) {
            throw new ServletException("Erro ao carregar feed", e);
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String destino)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher(destino);
        rd.forward(request, response);
    }
}
