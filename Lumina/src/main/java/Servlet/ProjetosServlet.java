package Servlet;

import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Dao.ProjetosDao;
import Entidades.Area;
import Entidades.Funcionarios;
import Entidades.ProjetoFuncionario;
import Entidades.ProjetoFuncionarioId;
import Entidades.Status;
import Entidades.Projetos;
import Dto.FuncionariosDTO;

@WebServlet("/ProjetosServlet")
public class ProjetosServlet extends HttpServlet {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lumina");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            String usuarioLogado = (String) session.getAttribute("usuarioLogado");

            if (usuarioLogado != null) {
                ProjetosDao dao = new ProjetosDao();

                Double idArea = dao.buscaArea(usuarioLogado); 
                List<FuncionariosDTO> colaboradores = dao.buscarFuncionariosEuron(idArea);
                request.setAttribute("colaboradores", colaboradores);

                List<Projetos> projetos = dao.listarProjetos();

                // Para cada projeto, busca os participantes
                for (Projetos projeto : projetos) {
                    List<FuncionariosDTO> participantes = dao.buscarParticipantesPorProjeto(projeto.getIdProjeto());
                    projeto.setParticipantes(participantes);
                }

                request.setAttribute("projetos", projetos);

                RequestDispatcher rd = request.getRequestDispatcher("/projetos.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            String usuarioLogado = (String) session.getAttribute("usuarioLogado");

            if (usuarioLogado != null) {
                request.setCharacterEncoding("UTF-8");
                ProjetosDao dao = new ProjetosDao();

                // === PARTE 1: Atualizar status existente ===
                String projetoIdStr = request.getParameter("projetoId");
                String statusIdStr = request.getParameter("statusId");

                if (projetoIdStr != null && statusIdStr != null) {
                    try {
                        Long projetoId = Long.parseLong(projetoIdStr);
                        Double statusId = Double.parseDouble(statusIdStr);

                        dao.atualizarStatusProjeto(projetoId, statusId);

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    response.sendRedirect(request.getContextPath() + "/ProjetosServlet");
                    return; // evita que o fluxo de criação de projeto seja executado
                }

                // === PARTE 2: Criar novo projeto ===
                String titulo = request.getParameter("titulo");
                String descricao = request.getParameter("descricao");
                String[] participantes = request.getParameterValues("participantes");
                String statusStr = request.getParameter("status");

                // Mapear status para ID
                double statusId = 1; // default "Não Iniciado"
                if ("Em andamento".equalsIgnoreCase(statusStr)) {
                    statusId = 2;
                } else if ("Concluído".equalsIgnoreCase(statusStr)) {
                    statusId = 3;
                }

                Status status = new Status();
                status.setId(statusId);

                Double idArea = dao.buscaArea(usuarioLogado);
                Area area = new Area();
                area.setIdArea(idArea);

                Projetos projeto = new Projetos();
                projeto.setTitulo(titulo);
                projeto.setDescricao(descricao);
                projeto.setStatus(status);
                projeto.setDataCriacao(new Date());
                projeto.setCriadoPor(usuarioLogado);
                projeto.setId_area(area);

                // Salvar projeto
                Projetos projetoSalvo = dao.cadastrarProjeto(projeto);

                if (projetoSalvo == null) {
                    request.setAttribute("erro", "Erro ao salvar projeto");
                    request.getRequestDispatcher("/projetos.jsp").forward(request, response);
                    return;
                }

                // Associar participantes
                if (participantes != null) {
                    for (String idFuncionario : participantes) {
                        dao.salvarProjetoFuncionario(projetoSalvo.getIdProjeto(), idFuncionario);
                    }
                }

                response.sendRedirect(request.getContextPath() + "/ProjetosServlet");

            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }


}
