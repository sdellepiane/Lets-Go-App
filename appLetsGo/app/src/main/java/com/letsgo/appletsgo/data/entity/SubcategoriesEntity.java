package com.letsgo.appletsgo.data.entity;

/**
 * Created by sergio on 8/4/17.
 */

public class SubcategoriesEntity {

    private int id_activities_subtypes;
    private String description;

    public int getId_activities_subtypes() {
        return id_activities_subtypes;
    }

    public void setId_activities_subtypes(int id_activities_subtypes) {
        this.id_activities_subtypes = id_activities_subtypes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SubcategoriesEntity{" +
                "id_activities_subtypes=" + id_activities_subtypes +
                ", description='" + description + '\'' +
                '}';
    }
}
