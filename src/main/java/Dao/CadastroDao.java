package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Entidades.Area;
import Entidades.Cargo;
import Entidades.Funcionarios;

public class CadastroDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public CadastroDao() {
		emf = Persistence.createEntityManagerFactory("Lumina");
		em = emf.createEntityManager();
	}

	public Cargo buscaCargo(String cargo) {
		try {
			String sql = "SELECT id_cargo FROM Cargo WHERE lower(descricao) = lower(:cargo)";
			Query query = em.createNativeQuery(sql);
			query.setParameter("cargo", cargo);

			Cargo cargoFunc = new Cargo();

			if (query.getSingleResult() == null) {
				return cargoFunc = null;
			} else {

				cargoFunc.setIdCargo(Double.parseDouble(query.getSingleResult().toString()));
			}

			return cargoFunc;

		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Area buscaArea(String area) {
		try {
			String sql = "SELECT ID_AREA FROM Area WHERE lower(descricao) = lower(:area)";
			Query query = em.createNativeQuery(sql);
			query.setParameter("area", area);

			Area areaFunc = new Area();

			if (query.getSingleResult() == null) {
				return areaFunc = null;
			} else {
				areaFunc.setIdArea(Double.parseDouble(query.getSingleResult().toString()));
			}

			return areaFunc;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Integer buscaUsuario(String matricula) {
		try {
			String sql = "SELECT 1 FROM Funcionarios WHERE lower(id_funcionario) = lower(:matricula)";
			Query query = em.createNativeQuery(sql);
			query.setParameter("matricula", matricula);

			if (query.getSingleResult() == null) {
				return null;
			}
			return Integer.parseInt(query.getSingleResult().toString());
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Integer buscaEmail(String email) {
		try {
			String sql = "SELECT 1 FROM Funcionarios WHERE lower(email) = lower(:email)";
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

	public String cadastraFuncionario(Funcionarios funcionario) {
		try {
			em.getTransaction().begin();
			em.persist(funcionario);
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return funcionario.getId();
	}

}
