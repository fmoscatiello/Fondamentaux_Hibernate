package org.example.service;

import org.example.dao.UserDaoJPA;
import org.example.model.User;

import java.util.List;

/**
 * Création de la classe UserJpaService afin d'utiliser la norme JPA standard, sans framework.
 */
public class UserJpaService {

    UserDaoJPA userDaoJPA = new UserDaoJPA();

    /**
     * Création de l'utilisateur et insertion dans la BDD
     * @param user
     */
    public void creerUser(User user){
        userDaoJPA.creerUser(user);
    }

    /**
     * Affichage dans la console de l'ensemble des utilisateurs dans la BDD
     */
    public void afficherTout(){
        System.out.println("************La liste de tous les users*********");
        List<User> users = userDaoJPA.afficherTousLesUsers();
        for (User user : users){
            System.out.println(user);
        }
    }

    /**
     * Affichage en console de l'utilisateur trouvé en fonction de son id
     * @param id
     */
    public void findUserById(Long id){
        System.out.println(userDaoJPA.findById(id));
    }

    /**
     * Mise à jour de l'utilisateur dans la BDD
     * @param user
     */
    public void updateUser(User user){
        userDaoJPA.updateUser(user);
    }

    /**
     * Suppression de l'utilisateur dans la BDD
     * @param id
     */
    public void deleteUserFromId(Long id){
        userDaoJPA.deleteUserById(id);
    }



}
