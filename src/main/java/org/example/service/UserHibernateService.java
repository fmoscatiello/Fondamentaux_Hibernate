package org.example.service;


import org.example.model.User;
import org.hibernate.Session;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Moscatiello Farid
 * @version 1.0.0
 *
 * Cette classe Service sert à implementer le CRUD via le framework Hibernate
 *
 */
public class UserHibernateService {

    //ouverture de l'entity manager factory pour créer une section de connection
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("java-sql");
    //Création de la session
    private final Session session = emf.createEntityManager().unwrap(Session.class);

    /**
     * Methode pour créer un user et l'inserer dans la BDD
     * @param user {User} , prends l'utilisateur créé
     */
    public void CreerUser(User user){
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        System.out.println("utilisateur bien ajouté");
    }

    /**
     * Methode pour afficher en console tous les users stockées de la BDD
     */
    public void afficherTousLesUsers(){
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        List<User> users = session.createQuery(criteriaQuery).getResultList();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
        session.close();
    }

    /**
     * Method pour trouver un User à partir de son idUser
     * @param idUser
     * @return l'utilisateur trouvé
     */
    public User findUserById(Long idUser){
        session.beginTransaction();
        // trouver l'objet utilisateur avec l'id correspondant
       User userFound =  session.find(User.class,idUser);
       session.close();
        System.out.println(userFound);
        return userFound;
    }

    /**
     * Méthode pour supprimer un user de la BDD à partir de son idUser
     * @param idUser
     */
    public void deleteUserById(Long idUser){
        session.beginTransaction();
        User userToDelete =  session.find(User.class,idUser);
        session.delete(userToDelete);
        session.getTransaction().commit();
        session.close();
        System.out.println("utilisateur supprimé");
    }

    /**
     * Methode pour mettre à jour l'utilisateur dans la BDD
     * @param userToUpdate
     */
    public void updateUser(User userToUpdate){
        session.beginTransaction();
        session.update(userToUpdate);
        session.getTransaction().commit();
        session.close();
    }


}
