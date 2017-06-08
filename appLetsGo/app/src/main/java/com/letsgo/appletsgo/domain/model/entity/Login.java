package com.letsgo.appletsgo.domain.model.entity;

import java.io.Serializable;

/**
 * Created by louislopez on 3/06/17.
 */

public class Login implements Serializable{
    private String id_users;
    private String first_name;
    private String last_name;
    private String email;
    private String id_facebook;

    public String getId_users() {
        return id_users;
    }

    public void setId_users(String id_users) {
        this.id_users = id_users;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_facebook() {
        return id_facebook;
    }

    public void setId_facebook(String id_facebook) {
        this.id_facebook = id_facebook;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id_users='" + id_users + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", id_facebook='" + id_facebook + '\'' +
                '}';
    }
}
