package com.letsgo.appletsgo.data.entity.raw;

/**
 * Created by louislopez on 4/06/17.
 {
 "id_users": "9",
 "id_ubigeos" : "150101",
 "birthdate" : "1991-02-01"
 }

 */

public class CompleteUserRaw {
    private String id_users;
    private String id_ubigeos;
    private String birthdate;
    private String gender;

    public String getId_users() {
        return id_users;
    }

    public void setId_users(String id_users) {
        this.id_users = id_users;
    }

    public String getId_ubigeos() {
        return id_ubigeos;
    }

    public void setId_ubigeos(String id_ubigeos) {
        this.id_ubigeos = id_ubigeos;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "CompleteUserRaw{" +
                "id_users='" + id_users + '\'' +
                ", id_ubigeos='" + id_ubigeos + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
