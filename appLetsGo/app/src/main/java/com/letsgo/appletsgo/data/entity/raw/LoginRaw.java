package com.letsgo.appletsgo.data.entity.raw;

import java.io.Serializable;

/**
 * Created by louislopez on 3/06/17.
 {

 "first_name" : "",
 "last_name" : "",
 "email" : "",
 "id_facebook" : "22222222"
 }

 */

public class LoginRaw implements Serializable {
    private String first_name;
    private String last_name;
    private String email;
    private String id_facebook;

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
        return "LoginEntity{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", id_facebook='" + id_facebook + '\'' +
                '}';
    }
}
