package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Entidades.LucroProjeto;
import Entidades.Projetos;
import Entidades.Publicacao;

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

	public Double buscaArea(String usuarioLogado) {
		try {
			String sql = "SELECT id_area FROM Funcionarios WHERE lower(id_funcionario) = lower(:matricula)";
			Query query = em.createNativeQuery(sql);
			query.setParameter("matricula", usuarioLogado);

			if (query.getSingleResult() == null) {
				return null;
			}
			return Double.parseDouble(query.getSingleResult().toString());
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Projetos> buscaProjetos(String usuarioLogado) {
		Double area = buscaArea(usuarioLogado);
		try {
			String jpql = "SELECT p FROM Projetos p WHERE p.idArea.id = :area AND p.status = 3";
			TypedQuery<Projetos> query = em.createQuery(jpql, Projetos.class);
			query.setParameter("area", area);

			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Projetos buscaProjeto(String idProjeto) {
		return em.find(Projetos.class, Long.valueOf(idProjeto));
		
	}

	public void cadastraPublicacao(Publicacao publicacao) {
		try {
			em.getTransaction().begin();
			em.persist(publicacao);
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}

	public void cadastraLucro(LucroProjeto lucro) {
		try {
			em.getTransaction().begin();
			em.persist(lucro);
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		
	}

}
