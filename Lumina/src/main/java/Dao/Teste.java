package Dao;

import Entidades.Cargo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Teste {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lumina");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Cargo cargo = new Cargo();
            cargo.setIdCargo(4.0); // valor fixo para teste
            cargo.setDescricao("Gerente de Projetos");

            em.persist(cargo);

            em.getTransaction().commit();
            System.out.println("Cargo inserido com sucesso!");

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Erro: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
}
