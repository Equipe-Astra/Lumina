package Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import Dao.DashboardsDao;
import Dto.LucroProjetoDTO;
import Entidades.Area;

@WebServlet("/Dashboards")
public class Dashboards extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String action = request.getParameter("action");
		String idAreaParam = request.getParameter("idArea");

		if (session == null || session.getAttribute("usuarioLogado") == null) {
			if ("filtrarPorArea".equals(action) || "buscarStatusTodasAreas".equals(action)
					|| "filtrarStatusPorArea".equals(action)) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.getWriter().write("{\"error\":\"Sessão expirada. Por favor, faça o login novamente.\"}");
				return;
			} else {
				response.sendRedirect("login.jsp");
				return;
			}
		}

		DashboardsDao dao = new DashboardsDao();

		if (action == null) {
			try {

				List<LucroProjetoDTO> lucrosPorArea = dao.buscaLucroPorArea();

				List<Area> todasAsAreas = dao.listarTodasAreas();

				List<Object[]> statusTotais = dao.buscaSomaStatusTodasAreas();

				List<Object[]> quantidadeProjetos = dao.buscaQuantidadeProjetosPorArea();

				List<Map<String, Object>> statusTotalList = new ArrayList<>();
				for (Object[] row : statusTotais) {
					Map<String, Object> statusMap = new HashMap<>();
					statusMap.put("status", (String) row[0]);
					statusMap.put("quantidade", ((Number) row[1]).intValue());
					statusTotalList.add(statusMap);
				}

				List<Map<String, Object>> quantidadeProjetosList = new ArrayList<>();
				for (Object[] row : quantidadeProjetos) {
					Map<String, Object> projetoMap = new HashMap<>();
					projetoMap.put("area", (String) row[0]);
					projetoMap.put("quantidade", ((Number) row[1]).intValue());
					quantidadeProjetosList.add(projetoMap);
				}

				request.setAttribute("lucrosPorArea", lucrosPorArea);
				request.setAttribute("todasAsAreas", todasAsAreas);
				request.setAttribute("statusTotalList", statusTotalList);

				request.setAttribute("quantidadeProjetosList", quantidadeProjetosList);

				request.getRequestDispatcher("dashboards.jsp").forward(request, response);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao buscar dados iniciais.");
				return;
			}
		}

		if ("filtrarPorArea".equals(action) && idAreaParam != null && !idAreaParam.isEmpty()) {
			try {
				int idArea = Integer.parseInt(idAreaParam);
				List<LucroProjetoDTO> lucroProjetos = dao.buscaLucroPorProjeto(idArea);
				Gson gson = new Gson();
				String json = gson.toJson(lucroProjetos);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} catch (SQLException | NumberFormatException e) {
				e.printStackTrace();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().write("{\"error\":\"Erro ao filtrar por área.\"}");
			}
			return;
		}

		if ("filtrarStatusPorArea".equals(action) && idAreaParam != null && !idAreaParam.isEmpty()) {
			try {
				int idArea = Integer.parseInt(idAreaParam);
				List<Object[]> statusProjetos = dao.buscaStatusPorProjetoPorArea(idArea);
				List<Map<String, Object>> lista = new ArrayList<>();
				for (Object[] row : statusProjetos) {
					Map<String, Object> map = new HashMap<>();
					map.put("status", (String) row[0]);
					map.put("quantidade", ((Number) row[1]).intValue());
					lista.add(map);
				}
				Gson gson = new Gson();
				String json = gson.toJson(lista);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} catch (SQLException | NumberFormatException e) {
				e.printStackTrace();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().write("{\"error\":\"Erro ao buscar status por área.\"}");
			}
			return;
		}

		if ("quantidadeProjetosPorArea".equals(action)) {
			try {
				List<Object[]> quantidadeProjetos = dao.buscaQuantidadeProjetosPorArea();

				List<Map<String, Object>> lista = new ArrayList<>();
				for (Object[] row : quantidadeProjetos) {
					Map<String, Object> map = new HashMap<>();
					map.put("area", (String) row[0]);
					map.put("quantidade", ((Number) row[1]).intValue());
					lista.add(map);
				}

				Gson gson = new Gson();
				String json = gson.toJson(lista);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			} catch (SQLException e) {
				e.printStackTrace();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().write("{\"error\":\"Erro ao buscar quantidade de projetos por área.\"}");
			}
			return;
		}
	}
}