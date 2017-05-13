package ru.kpfu.itis.model.additional;

import ru.kpfu.itis.model.User;

import javax.persistence.*;

/**
 * Created by vladislav on 07.05.17.
 */
@Entity
public class AuthToken {
    public AuthToken(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    private String token;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

