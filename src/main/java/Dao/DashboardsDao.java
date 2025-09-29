package Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Dto.EvolucaoMensalDTO;
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
				+ "WHERE p.ID_AREA = :idArea AND p.ID_STATUS = 3 " + "ORDER BY l.LUCRO DESC";

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

	public List<EvolucaoMensalDTO> buscaEvolucaoMensalPorArea(int idArea) throws SQLException {
	    List<EvolucaoMensalDTO> lista = new ArrayList<>();

	    String sql = "WITH Meses AS ( "
	               + "    SELECT 1 AS mes, 'Janeiro' AS nome FROM dual UNION ALL "
	               + "    SELECT 2, 'Fevereiro' FROM dual UNION ALL "
	               + "    SELECT 3, 'Março' FROM dual UNION ALL "
	               + "    SELECT 4, 'Abril' FROM dual UNION ALL "
	               + "    SELECT 5, 'Maio' FROM dual UNION ALL "
	               + "    SELECT 6, 'Junho' FROM dual UNION ALL "
	               + "    SELECT 7, 'Julho' FROM dual UNION ALL "
	               + "    SELECT 8, 'Agosto' FROM dual UNION ALL "
	               + "    SELECT 9, 'Setembro' FROM dual UNION ALL "
	               + "    SELECT 10, 'Outubro' FROM dual UNION ALL "
	               + "    SELECT 11, 'Novembro' FROM dual UNION ALL "
	               + "    SELECT 12, 'Dezembro' FROM dual "
	               + ") "
	               + "SELECT "
	               + "    m.nome AS mes, "
	               + "    NVL(SUM(CASE WHEN s.descricao = 'Não Iniciado' THEN 1 ELSE 0 END), 0) AS naoIniciado, "
	               + "    NVL(SUM(CASE WHEN s.descricao = 'Em andamento' THEN 1 ELSE 0 END), 0) AS emAndamento, "
	               + "    NVL(SUM(CASE WHEN s.descricao = 'Concluído' THEN 1 ELSE 0 END), 0) AS concluido "
	               + "FROM Meses m "
	               + "LEFT JOIN projetos p "
	               + "    ON EXTRACT(MONTH FROM p.data_criacao) = m.mes "
	               + "   AND EXTRACT(YEAR FROM p.data_criacao) = EXTRACT(YEAR FROM SYSDATE) "
	               + "LEFT JOIN status s "
	               + "    ON p.id_status = s.id_status "
	               + "AND p.id_area = :idArea "
	               + "GROUP BY m.mes, m.nome "
	               + "ORDER BY m.mes";

	    Query query = em.createNativeQuery(sql);
	    query.setParameter("idArea", idArea);

	    List<Object[]> resultados = query.getResultList();

	    for (Object[] row : resultados) {
	        String mes = (String) row[0];
	        int naoIniciado = ((Number) row[1]).intValue();
	        int emAndamento = ((Number) row[2]).intValue();
	        int concluido = ((Number) row[3]).intValue();

	        lista.add(new EvolucaoMensalDTO(mes, naoIniciado, emAndamento, concluido));
	    }

	    return lista;
	}


	public List<EvolucaoMensalDTO> buscaEvolucaoMensalTodasAreas() throws SQLException {
		List<EvolucaoMensalDTO> lista = new ArrayList<>();

		  String sql = "WITH Meses AS ( "
	               + "    SELECT 1 AS mes, 'Janeiro' AS nome FROM dual UNION ALL "
	               + "    SELECT 2, 'Fevereiro' FROM dual UNION ALL "
	               + "    SELECT 3, 'Março' FROM dual UNION ALL "
	               + "    SELECT 4, 'Abril' FROM dual UNION ALL "
	               + "    SELECT 5, 'Maio' FROM dual UNION ALL "
	               + "    SELECT 6, 'Junho' FROM dual UNION ALL "
	               + "    SELECT 7, 'Julho' FROM dual UNION ALL "
	               + "    SELECT 8, 'Agosto' FROM dual UNION ALL "
	               + "    SELECT 9, 'Setembro' FROM dual UNION ALL "
	               + "    SELECT 10, 'Outubro' FROM dual UNION ALL "
	               + "    SELECT 11, 'Novembro' FROM dual UNION ALL "
	               + "    SELECT 12, 'Dezembro' FROM dual "
	               + ") "
	               + "SELECT "
	               + "    m.nome AS mes, "
	               + "    NVL(SUM(CASE WHEN s.descricao = 'Não Iniciado' THEN 1 ELSE 0 END), 0) AS naoIniciado, "
	               + "    NVL(SUM(CASE WHEN s.descricao = 'Em andamento' THEN 1 ELSE 0 END), 0) AS emAndamento, "
	               + "    NVL(SUM(CASE WHEN s.descricao = 'Concluído' THEN 1 ELSE 0 END), 0) AS concluido "
	               + "FROM Meses m "
	               + "LEFT JOIN projetos p "
	               + "    ON EXTRACT(MONTH FROM p.data_criacao) = m.mes "
	               + "   AND EXTRACT(YEAR FROM p.data_criacao) = EXTRACT(YEAR FROM SYSDATE) "
	               + "LEFT JOIN status s "
	               + "    ON p.id_status = s.id_status "
	               + "GROUP BY m.mes, m.nome "
	               + "ORDER BY m.mes";

		Query query = em.createNativeQuery(sql);
		List<Object[]> resultados = query.getResultList();

		for (Object[] row : resultados) {
			EvolucaoMensalDTO dto = new EvolucaoMensalDTO(((String) row[0]), ((Number) row[1]).intValue(),
					((Number) row[2]).intValue(), ((Number) row[3]).intValue());
			lista.add(dto);
		}

		return lista;
	}

}
