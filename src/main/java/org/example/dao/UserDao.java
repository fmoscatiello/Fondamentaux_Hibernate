package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface UserDao {

    /**
     * Création de l'Utilisateur dans la BDD
     *
     * @param user
     */
    public void creerUser(User user);

    /**
     * Methode pour retourner l'ensemble des Users dans une liste
     *
     * @return la liste des users
     */
    public List<User> afficherTousLesUsers();

    /**
     * methode pour trouver un user en fonction de son Id
     *
     * @param id
     * @return l'Objet User trouvé
     */
    public User findById(Long id);

    /**
     * methode pour mettre un jour l'utilisateur
     *
     * @param user
     */
    public void updateUser(User user);

    /**
     * methode pour supprimer un utilisateur depuis son Id
     *
     * @param idToDelete
     * @return l'utilisateur supprimé.
     * si nous voulons rollback par exemple
     */
    public void deleteUserById(Long idToDelete);

}
