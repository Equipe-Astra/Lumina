package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class LoginDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public LoginDao() {
		emf = Persistence.createEntityManagerFactory("Lumina");
		em = emf.createEntityManager();
	}

	public Integer buscaUsuario(String email) {
		try {
			String sql = "SELECT id FROM Funcionarios WHERE lower(email) = lower(:email)";
			Query query = em.createNativeQuery(sql);
			query.setParameter("email", email);

			if (query.getSingleResult() == null) {
				return null;
			}
			return Integer.parseInt(query.getSingleResult().toString());
		} catch (NoResultException e) {
			return null;
		}
	}

	public String buscaSenha(String email) {
		try {
			String sql = "SELECT senha FROM Funcionarios WHERE lower(email) = lower(:email)";
			Query query = em.createNativeQuery(sql);
			query.setParameter("email", email);

			if (query.getSingleResult() == null) {
				return null;
			}
			return query.getSingleResult().toString();
		} catch (NoResultException e) {
			return null;
		}
	}

	public String buscaCargo(Integer usuario) {
			try {
				String sql = "SELECT b.descricao FROM funcionarios a JOIN cargo b ON a.id_cargo = b.id_cargo WHERE a.id = :usuario";
				Query query = em.createNativeQuery(sql);
				query.setParameter("usuario", usuario);

				if (query.getSingleResult() == null) {
					return null;
				}
				return query.getSingleResult().toString();
			} catch (NoResultException e) {
				return null;
			}
		}

}
