package Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("Lumina");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void fecharFactory() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
