package org.example.service;

import org.example.dao.UserDaoJPA;
import org.example.model.User;

import java.util.List;

public class UserJpaService {

    UserDaoJPA userDaoJPA = new UserDaoJPA();

    public void creerUser(User user){
        userDaoJPA.creerUser(user);
    }

    public void afficherTout(){
        System.out.println("************La liste de tous les users*********");
        List<User> users = userDaoJPA.afficherTousLesUsers();
        for (User user : users){
            System.out.println(user);
        }
    }

    public void findUserById(Long id){
        System.out.println(userDaoJPA.findById(id));
    }

    public void updateUser(User user){
        userDaoJPA.updateUser(user);
    }

    public void deleteUserFromId(Long id){
        userDaoJPA.deleteUserById(id);
    }



}
