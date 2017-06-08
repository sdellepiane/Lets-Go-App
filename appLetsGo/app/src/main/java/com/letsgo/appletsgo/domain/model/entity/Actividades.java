package com.letsgo.appletsgo.domain.model.entity;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by louislopez on 4/03/17.
 */

public class Actividades implements Serializable{

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String id_activities;

    @DatabaseField
    private String type;

    @DatabaseField
    private String activity;

    @DatabaseField
    private String schedul;

    @DatabaseField
    private String schedul_date;

    @DatabaseField
    private String schedul_time;

    @DatabaseField
    private String price;

    @DatabaseField
    private String path;

    private boolean favorite;

    private String advertisements;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_activities() {
        return id_activities;
    }

    public void setId_activities(String id_activities) {
        this.id_activities = id_activities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getSchedul() {
        return schedul;
    }

    public void setSchedul(String schedul) {
        this.schedul = schedul;
    }

    public String getSchedul_date() {
        return schedul_date;
    }

    public void setSchedul_date(String schedul_date) {
        this.schedul_date = schedul_date;
    }

    public String getSchedul_time() {
        return schedul_time;
    }

    public void setSchedul_time(String schedul_time) {
        this.schedul_time = schedul_time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(String advertisements) {
        this.advertisements = advertisements;
    }

    @Override
    public String toString() {
        return "Actividades{" +
                "id=" + id +
                ", id_activities='" + id_activities + '\'' +
                ", type='" + type + '\'' +
                ", activity='" + activity + '\'' +
                ", schedul='" + schedul + '\'' +
                ", schedul_date='" + schedul_date + '\'' +
                ", schedul_time='" + schedul_time + '\'' +
                ", price='" + price + '\'' +
                ", path='" + path + '\'' +
                ", favorite=" + favorite +
                ", advertisements='" + advertisements + '\'' +
                '}';
    }
}
