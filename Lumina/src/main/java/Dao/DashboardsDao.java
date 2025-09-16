package Dao;

import java.sql.SQLException;
import java.util.*;
import javax.persistence.*;

import Dto.LucroProjetoDTO;
import Entidades.Area;

public class DashboardsDao {

	private EntityManagerFactory emf;
	private EntityManager em;

	public DashboardsDao() {
		emf = Persistence.createEntityManagerFactory("Lumina");
		em = emf.createEntityManager();
	}

	public List<Area> listarTodasAreas() {
		String sql = "SELECT ID_AREA, UPPER(DESCRICAO) AS DESCRICAO FROM AREA";
		Query query = em.createNativeQuery(sql, Area.class);

		return query.getResultList();
	}

	public List<LucroProjetoDTO> buscaLucroPorArea() throws SQLException {
		String sql = "SELECT a.ID_AREA, UPPER(a.DESCRICAO) AS DESCRICAO, COALESCE(SUM(l.LUCRO), 0) AS LUCRO_TOTAL "
				+ "FROM AREA a " + "LEFT JOIN PROJETOS p ON a.ID_AREA = p.ID_AREA "
				+ "LEFT JOIN LUCROPROJETO l ON p.ID_PROJETO = l.ID_PROJETO " + "GROUP BY a.ID_AREA, a.DESCRICAO "
				+ "ORDER BY LUCRO_TOTAL DESC";

		Query query = em.createNativeQuery(sql);
		List<Object[]> resultados = query.getResultList();
		List<LucroProjetoDTO> listaLucros = new ArrayList<>();

		for (Object[] row : resultados) {
			Integer idArea = ((Number) row[0]).intValue();
			String descricaoArea = (String) row[1];
			Double lucroTotal = ((Number) row[2]).doubleValue();

			LucroProjetoDTO dto = new LucroProjetoDTO(idArea, descricaoArea, null, lucroTotal);
			listaLucros.add(dto);
		}
		return listaLucros;
	}

	public List<LucroProjetoDTO> buscaLucroPorProjeto(int idArea) throws SQLException {
		String sql = "SELECT p.ID_PROJETO, p.TITULO, p.DATA_CRIACAO, COALESCE(l.LUCRO, 0) AS LUCRO "
				+ "FROM PROJETOS p " + "LEFT JOIN LUCROPROJETO l ON p.ID_PROJETO = l.ID_PROJETO "
				+ "WHERE p.ID_AREA = :idArea " + "ORDER BY l.LUCRO DESC";

		Query query = em.createNativeQuery(sql);
		query.setParameter("idArea", idArea);
		List<Object[]> resultados = query.getResultList();
		List<LucroProjetoDTO> listaLucros = new ArrayList<>();

		for (Object[] row : resultados) {
			Integer idProjeto = ((Number) row[0]).intValue();
			String titulo = (String) row[1];
			Date dataCriacao = (java.util.Date) row[2];
			Double lucro = ((Number) row[3]).doubleValue();

			LucroProjetoDTO dto = new LucroProjetoDTO(idProjeto, titulo, dataCriacao, lucro);
			listaLucros.add(dto);
		}
		return listaLucros;
	}

	public List<LucroProjetoDTO> buscaTodosLucrosPorProjeto() throws SQLException {
		String sql = "SELECT p.ID_PROJETO, p.TITULO, p.DATA_CRIACAO, COALESCE(l.LUCRO, 0) AS LUCRO "
				+ "FROM PROJETOS p " + "LEFT JOIN LUCROPROJETO l ON p.ID_PROJETO = l.ID_PROJETO "
				+ "ORDER BY l.LUCRO DESC";

		Query query = em.createNativeQuery(sql);
		List<Object[]> resultados = query.getResultList();
		List<LucroProjetoDTO> listaLucros = new ArrayList<>();

		for (Object[] row : resultados) {
			Integer idProjeto = ((Number) row[0]).intValue();
			String titulo = (String) row[1];
			Date dataCriacao = (java.util.Date) row[2];
			Double lucro = ((Number) row[3]).doubleValue();

			LucroProjetoDTO dto = new LucroProjetoDTO(idProjeto, titulo, dataCriacao, lucro);
			listaLucros.add(dto);
		}
		return listaLucros;
	}

	public List<Object[]> buscaSomaStatusTodasAreas() throws SQLException {
		String sql = "SELECT UPPER(s.DESCRICAO) AS STATUS_DESC, COUNT(p.ID_PROJETO) AS QTD " + "FROM STATUS s "
				+ "LEFT JOIN PROJETOS p ON s.ID_STATUS = p.ID_STATUS " + "GROUP BY s.DESCRICAO, s.ID_STATUS "
				+ "ORDER BY s.ID_STATUS";
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
	}

	public List<Object[]> buscaStatusPorProjetoPorArea(int idArea) throws SQLException {
		String sql = "SELECT UPPER(s.DESCRICAO) AS STATUS_DESC, COUNT(p.ID_PROJETO) AS QTD " + "FROM PROJETOS p "
				+ "LEFT JOIN STATUS s ON p.ID_STATUS = s.ID_STATUS " + "WHERE p.ID_AREA = :idArea "
				+ "GROUP BY s.DESCRICAO " + "ORDER BY s.DESCRICAO";

		Query query = em.createNativeQuery(sql);
		query.setParameter("idArea", idArea);
		return query.getResultList();
	}

	public List<Object[]> buscaQuantidadeProjetosPorArea() throws SQLException {
		String sql = "SELECT a.DESCRICAO, COUNT(p.ID_PROJETO) AS QTD_PROJETOS " + "FROM AREA a "
				+ "LEFT JOIN PROJETOS p ON a.ID_AREA = p.ID_AREA " + "GROUP BY a.DESCRICAO " + "ORDER BY a.DESCRICAO";
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
	}
}