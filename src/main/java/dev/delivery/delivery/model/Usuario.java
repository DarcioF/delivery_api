package dev.delivery.delivery.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "GEN_ID_USER", sequenceName = "GEN_ID_USER")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "GEN_ID_USER")
    private Integer id;
    @Column(unique = true)
    private String login;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
