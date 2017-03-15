package com.letsgo.appletsgo.domain.model.entity;

import java.io.Serializable;

/**
 * Created by louislopez on 28/02/17.
 */

public class User implements Serializable {
    private String idFacebook;
    private String name_complete;
    private String first_name;
    private String last_name;
    private String email;
    private String photo;
    private String telephone;
    private String gender;
    private String id_ubigeosh;

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
    }

    public String getName_complete() {
        return name_complete;
    }

    public void setName_complete(String name_complete) {
        this.name_complete = name_complete;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getId_ubigeosh() {
        return id_ubigeosh;
    }

    public void setId_ubigeosh(String id_ubigeosh) {
        this.id_ubigeosh = id_ubigeosh;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "idFacebook='" + idFacebook + '\'' +
                ", name_complete='" + name_complete + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", photo='" + photo + '\'' +
                ", telephone='" + telephone + '\'' +
                ", gender='" + gender + '\'' +
                ", id_ubigeosh='" + id_ubigeosh + '\'' +
                '}';
    }
}
