package org.example.model;
import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "Utilisateur")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @Column(name="nom", nullable = false)
    private String nomUser;
    @Column(name="prenom", nullable = false)
    private String prenomUser;
    @Column(name="email", nullable = false)
    private String email;

    public User(){

    };

    public User(Long idUser,String nomUser, String prenomUser, String email) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.prenomUser = prenomUser;
        this.email = email;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("idUser=" + idUser)
                .add("nomUser='" + nomUser + "'")
                .add("prenomUser='" + prenomUser + "'")
                .add("email='" + email + "'")
                .toString();
    }
}
