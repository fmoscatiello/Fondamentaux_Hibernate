package org.example.config;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaConfig {

    /**
     * Methode pour ouvrir la connexion
     * @return em , un objet EntityManager
     */
    public static EntityManager ouvrirEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("java-sql");
        EntityManager em = emf.createEntityManager();
        return em;
    }

    /**
     * CLoture de l'entity manager et de l'entity manager factory
     * @param em
     */
    public static void fermerEntityManager(EntityManager em){
        EntityManagerFactory emf = em.getEntityManagerFactory();
        em.close();
        emf.close();
    }
}
