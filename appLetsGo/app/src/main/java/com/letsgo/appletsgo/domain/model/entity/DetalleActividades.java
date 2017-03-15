package com.letsgo.appletsgo.domain.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by louislopez on 6/03/17.
 */

public class DetalleActividades implements Serializable {
    private String id_activities;
    private String activity;
    private String types;
    private String subtypes;
    private String long_description;
    //private String public;
    private String logo_organizer;
    private String web_organizer;
    private String business_name;
    private String face_organizer;
    private List<Places> places ;
    /* TODO
    "group_date": []
    "prices": []
    */
    private List<ImagenesActividades> img ;

    public String getId_activities() {
        return id_activities;
    }

    public void setId_activities(String id_activities) {
        this.id_activities = id_activities;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(String subtypes) {
        this.subtypes = subtypes;
    }

    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    public String getLogo_organizer() {
        return logo_organizer;
    }

    public void setLogo_organizer(String logo_organizer) {
        this.logo_organizer = logo_organizer;
    }

    public String getWeb_organizer() {
        return web_organizer;
    }

    public void setWeb_organizer(String web_organizer) {
        this.web_organizer = web_organizer;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getFace_organizer() {
        return face_organizer;
    }

    public void setFace_organizer(String face_organizer) {
        this.face_organizer = face_organizer;
    }

    public List<Places> getPlaces() {
        return places;
    }

    public void setPlaces(List<Places> places) {
        this.places = places;
    }

    public List<ImagenesActividades> getImg() {
        return img;
    }

    public void setImg(List<ImagenesActividades> img) {
        this.img = img;
    }




    @Override
    public String toString() {
        return "DetalleActividades{" +
                "id_activities='" + id_activities + '\'' +
                ", activity='" + activity + '\'' +
                ", types='" + types + '\'' +
                ", subtypes='" + subtypes + '\'' +
                ", long_description='" + long_description + '\'' +
                ", logo_organizer='" + logo_organizer + '\'' +
                ", web_organizer='" + web_organizer + '\'' +
                ", business_name='" + business_name + '\'' +
                ", face_organizer='" + face_organizer + '\'' +
                ", places=" + places +
                ", img=" + img +
                '}';
    }
}
