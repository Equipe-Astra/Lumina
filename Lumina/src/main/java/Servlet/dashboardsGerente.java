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

import Dao.DashboardsDao;
import Dao.FeedDao;
import Dto.EvolucaoMensalDTO;
import Dto.LucroProjetoDTO;
import Entidades.Area;

@WebServlet("/dashboardsGerente")
public class dashboardsGerente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String usuarioLogado = (String) session.getAttribute("usuarioLogado");

		if (session == null || session.getAttribute("usuarioLogado") == null) {
			response.sendRedirect("login.jsp");
			return;
		} else {
			DashboardsDao dao = new DashboardsDao();
			FeedDao feedDao = new FeedDao();

			int area = (int) Double.parseDouble(feedDao.buscaArea(usuarioLogado).toString());

			List<LucroProjetoDTO> lucroProjetos = null;
			List<Object[]> statusProjetos = null;
			List<Object[]> quantidadeProjetos = null;
			List<EvolucaoMensalDTO> evolucao = null;
			List<Object[]> statusTotais = null;

			try {
				lucroProjetos = dao.buscaLucroPorProjeto(area);
				quantidadeProjetos = dao.buscaQuantidadeProjetosPorArea();
				evolucao = dao.buscaEvolucaoMensalPorArea(area);
				statusTotais = dao.buscaStatusPorProjetoPorArea(area);
				
				List<Map<String, Object>> quantidadeProjetosList = new ArrayList<>();
				for (Object[] row : quantidadeProjetos) {
					Map<String, Object> projetoMap = new HashMap<>();
					projetoMap.put("area", (String) row[0]);
					projetoMap.put("quantidade", ((Number) row[1]).intValue());
					quantidadeProjetosList.add(projetoMap);
				}
				List<Map<String, Object>> statusTotalList = new ArrayList<>();
				for (Object[] row : statusTotais) {
					Map<String, Object> statusMap = new HashMap<>();
					statusMap.put("status", (String) row[0]);
					statusMap.put("quantidade", ((Number) row[1]).intValue());
					statusTotalList.add(statusMap);
				}
				
				request.setAttribute("lucrosPorArea", lucroProjetos);
				request.setAttribute("evolucaoMensalList", evolucao);
				request.setAttribute("quantidadeProjetosList", quantidadeProjetosList);
				request.setAttribute("statusTotalList", statusTotalList);

				request.getRequestDispatcher("dashboardsGerente.jsp").forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
