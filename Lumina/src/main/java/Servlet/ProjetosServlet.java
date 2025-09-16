package Servlet;

import java.io.IOException;
import java.sql.SQLException;

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
                
                // Busca o cargo do funcionário logado
                Double cargoId = dao.buscaCargo(usuarioLogado);
                
                List<Projetos> projetos = null;
                String jspPath = "";

                if (cargoId != null && cargoId == 1.0) { 
                    projetos = dao.listarProjetos(); 
                    jspPath = "/projetosExecGe.jsp";
                } else if (cargoId != null && cargoId == 2.0) { 
                    Double idArea = dao.buscaArea(usuarioLogado);
                    projetos = dao.listarProjetosPorArea(idArea); 
                    jspPath = "/projetosGerenteProjetos.jsp";
                } else if (cargoId != null && cargoId == 3.0) { 
                    Double idArea = dao.buscaArea(usuarioLogado);
                    projetos = dao.listarProjetosPorParticipacao(idArea, usuarioLogado); // Lista projetos da área
                    jspPath = "/projetosFuncionariosEuron.jsp";
                } else {
                    response.sendRedirect(request.getContextPath() + "/feedFuncionario.jsp");
                    return;
                }
                
                // Popula os participantes de cada projeto
                if (projetos != null) {
                    for (Projetos projeto : projetos) {
                        try {
                            List<FuncionariosDTO> participantes = dao.buscarParticipantesPorProjeto(projeto.getIdProjeto());
                            projeto.setParticipantes(participantes);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                
                // Busca colaboradores para o modal de criação/edição
                Double idArea = dao.buscaArea(usuarioLogado);
                try {
                    List<FuncionariosDTO> colaboradores = dao.buscarFuncionariosEuron(idArea);
                    request.setAttribute("colaboradores", colaboradores);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                // Define os atributos e direciona para a página JSP correta
                request.setAttribute("projetos", projetos);
                RequestDispatcher rd = request.getRequestDispatcher(jspPath);
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

                String projetoIdStr = request.getParameter("projetoId");
                String statusIdStr = request.getParameter("statusId");

             // Caso 1: Atualizar status diretamente (vindo de dropdown de status)
                if (projetoIdStr != null && statusIdStr != null && request.getParameter("titulo") == null) {
                    try {
                        Long projetoId = Long.parseLong(projetoIdStr);
                        Double statusId = Double.parseDouble(statusIdStr);
                        dao.atualizarStatusProjeto(projetoId, statusId);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    response.sendRedirect(request.getContextPath() + "/ProjetosServlet");
                    return;
                }
                
             // Caso 2: Editar projeto existente
                if (projetoIdStr != null && request.getParameter("titulo") != null) {
                    try {
                        Long projetoId = Long.parseLong(projetoIdStr);
                        String titulo = request.getParameter("titulo");
                        String descricao = request.getParameter("descricao");
                        String[] participantesArray = request.getParameterValues("participantes");

                        // Atualiza título e descrição
                        dao.atualizarProjeto(projetoId, titulo, descricao);

                        // Sincroniza participantes
                        if (participantesArray != null) {
                            List<String> novosParticipantes = List.of(participantesArray);
                            dao.sincronizarParticipantes(projetoId, novosParticipantes);
                        }

                        response.sendRedirect(request.getContextPath() + "/ProjetosServlet");
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
             // Caso 3: Deletar projeto
                if (request.getParameter("deletarId") != null) {
                    try {
                        Long projetoId = Long.parseLong(request.getParameter("deletarId"));
                        dao.deletarProjeto(projetoId);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }

                    response.sendRedirect(request.getContextPath() + "/ProjetosServlet");
                    return;
                }


             // === PARTE 2: Criar novo projeto ===
                String titulo = request.getParameter("titulo");
                String descricao = request.getParameter("descricao");
                String[] participantes = request.getParameterValues("participantes");
                String statusStr = request.getParameter("statusId"); // Já é o ID

                // Mapear status para ID
                double statusId = 1; // default "Não Iniciado"
                if (statusStr != null && !statusStr.trim().isEmpty()) {
                    try {
                        statusId = Double.parseDouble(statusStr);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
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
                projeto.setIdArea(area);

                // Salvar projeto
                Projetos projetoSalvo = dao.cadastrarProjeto(projeto);
                System.out.println("Projeto salvo com sucesso." + projetoSalvo);


                if (participantes != null && participantes.length > 0) {
                    for (String idFuncionario : participantes) {
                        // Garante que o ID do funcionário não é nulo antes de chamar o método
                        if (idFuncionario != null && !idFuncionario.trim().isEmpty()) {
                            dao.salvarProjetoFuncionario(projetoSalvo.getIdProjeto(), idFuncionario);
                        }
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
