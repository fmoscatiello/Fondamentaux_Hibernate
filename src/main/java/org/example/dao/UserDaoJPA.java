package org.example.dao;
import org.example.config.JpaConfig;
import org.example.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDaoJPA implements UserDao{

    /**
     * Création de l'Utilisateur dans la BDD
     *
     * @param user
     */
    @Override
    public void creerUser(User user) {
        EntityManager em=null;
        EntityTransaction tx = null;
        try {
            em = JpaConfig.ouvrirEntityManager();
            tx = em.getTransaction();
            tx.begin();
            em.persist(user);
            tx.commit();
            System.out.println("Utilisateur a bien été créé");
        }catch (Exception e){
            System.out.println("Echec de la création");
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            if (em.isOpen()) {
                JpaConfig.fermerEntityManager(em);
            }
        }
    }

    /**
     * Methode pour retourner l'ensemble des Users dans une liste
     *
     * @return la liste des users
     */
    @Override
    public List<User> afficherTousLesUsers() {
        List<User> users;
        EntityManager em=null;
        try {
            em = JpaConfig.ouvrirEntityManager();
            /*EntityTransaction tx = em.getTransaction();
            tx.begin();*/
            String jpql = "SELECT u FROM User u";
            users = em.createQuery(jpql, User.class).getResultList();
            // tx.commit();

        }catch (Exception e){
            System.out.println("Echec de la création");
            throw e;
        }finally {
            JpaConfig.fermerEntityManager(em);
        }

        return users;
    }

    /**
     * methode pour trouver un user en fonction de son Id
     *
     * @param idToFind
     * @return l'Objet User trouvé
     */
    @Override
    public User findById(Long idToFind) {
        EntityManager em=null;
        User user;
        try {
            em = JpaConfig.ouvrirEntityManager();
            String jpql = "SELECT u FROM User u WHERE u.idUser = :id";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            //On remplace le :id dans la requete par le idToFind
            //dans la requete. Cela permet de securiser le jpql
            query.setParameter("id", idToFind);
            user = query.getSingleResult();


        }catch (Exception e){
            System.out.println("Echec lors de la recherche");
            throw e;
        }finally {
            JpaConfig.fermerEntityManager(em);
        }
        return user;
    }

    /**
     * methode pour mettre un jour l'utilisateur
     *
     * @param userToUpdate
     */
    @Override
    public void updateUser(User userToUpdate) {
        EntityManager em=null;
        EntityTransaction tx=null;
        try {
            em = JpaConfig.ouvrirEntityManager();
            tx = em.getTransaction();
            tx.begin();
            //réecrire la ligne user avec le nouvel userUpdate
            em.merge(userToUpdate);
            tx.commit();
            System.out.println("************utilisateur bien mis à jour ***************");
        }catch (Exception e){
            System.out.println("Echec de la mise à jour");
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            if (em.isOpen()) {
                JpaConfig.fermerEntityManager(em);
            }
        }

    }

    /**
     * methode pour supprimer un utilisateur
     *
     * @param idToDelete
     * @return l'utilisateur supprimé.
     * si nous voulons rollback par exemple
     */
    @Override
    public void deleteUserById(Long idToDelete) {
        EntityManager em=null;
        EntityTransaction tx=null;
        User user;
        try {
            em = JpaConfig.ouvrirEntityManager();
            tx = em.getTransaction();
            tx.begin();
            UserDaoJPA userDaoJPA = new UserDaoJPA();
            //trouver l'utilisateur à partir de son Id
            user = userDaoJPA.findById(idToDelete);
            em.merge(user);
            //réecrire la ligne user avec le nouvel userUpdate
            em.remove(user);
            tx.commit();
            System.out.println("************utilisateur bien supprimé ***************");
            //System.out.println("user supprimé : "+user);
        }catch (Exception e){
            System.out.println("Echec de la mise à jour");
            if (tx != null && tx.isActive()){
                tx.rollback();
            }
            e.printStackTrace();
        }finally {
            if (em.isOpen()) {
                JpaConfig.fermerEntityManager(em);
            }
        }
    }
}
