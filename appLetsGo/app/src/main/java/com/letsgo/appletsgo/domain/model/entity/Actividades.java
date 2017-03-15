package com.letsgo.appletsgo.domain.model.entity;

import java.io.Serializable;

/**
 * Created by louislopez on 4/03/17.
 */

public class Actividades implements Serializable{
    private String id_activities;
    private String type;
    private String activity;
    private String schedul;
    private String schedul_date;
    private String schedul_time;
    private String price;
    private String path;


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

    @Override
    public String toString() {
        return "ActividadesEntity{" +
                "id_activities='" + id_activities + '\'' +
                ", type='" + type + '\'' +
                ", activity='" + activity + '\'' +
                ", schedul='" + schedul + '\'' +
                ", schedul_date='" + schedul_date + '\'' +
                ", schedul_time='" + schedul_time + '\'' +
                ", price='" + price + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
