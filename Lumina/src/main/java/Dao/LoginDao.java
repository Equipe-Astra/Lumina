package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Dto.FuncionarioInfo;

public class LoginDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public String buscaUsuario(String email) {
		try {
			String sql = "SELECT id_funcionario FROM Funcionarios WHERE lower(email) = lower(:email)";
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

	public String buscaCargo(String usuario) {
		try {
			String sql = "SELECT b.descricao FROM funcionarios a JOIN cargo b ON a.id_cargo = b.id_cargo WHERE lower(a.id_funcionario) = lower(:usuario)";
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

	public FuncionarioInfo buscaUsuarioCompleto(String email) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			String sql = "SELECT f.id_funcionario, f.senha, c.descricao " + "FROM funcionarios f "
					+ "JOIN cargo c ON f.id_cargo = c.id_cargo " + "WHERE lower(f.email) = lower(:email)";
			Query query = em.createNativeQuery(sql);
			query.setParameter("email", email);

			Object[] resultado = (Object[]) query.getSingleResult();
			return new FuncionarioInfo(resultado[0].toString(), resultado[1].toString(), resultado[2].toString());
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close(); 
		}
	}
}
