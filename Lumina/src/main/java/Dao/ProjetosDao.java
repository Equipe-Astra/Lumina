package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Entidades.Area;
import Entidades.Cargo;
import Entidades.Funcionarios;

public class ProjetosDao {
	private EntityManagerFactory emf;
	private EntityManager em;

	public ProjetosDao() {
		emf = Persistence.createEntityManagerFactory("Lumina");
		em = emf.createEntityManager();
	}

	
}
