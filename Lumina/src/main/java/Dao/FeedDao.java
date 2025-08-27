package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FeedDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public FeedDao() {
		emf = Persistence.createEntityManagerFactory("Lumina");
		em = emf.createEntityManager();
	}

	public String buscaNome(String usuarioLogado) {

		try {
			String sql = "SELECT UPPER(nome) FROM Funcionarios WHERE lower(id_funcionario) = lower(:matricula)";
			Query query = em.createNativeQuery(sql);
			query.setParameter("matricula", usuarioLogado);

			if (query.getSingleResult() == null) {
				return null;
			}
			return query.getSingleResult().toString();
		} catch (NoResultException e) {
			return null;
		}
	}

}
