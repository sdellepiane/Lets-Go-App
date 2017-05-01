package com.letsgo.appletsgo.domain.model.entity;

import java.io.Serializable;

/**
 * Created by sergio on 8/4/17.
 */

public class Subcategories implements Serializable {

    private int id_activities_subtypes;
    private String description;
    private boolean selected;

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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Subcategories{" +
                "id_activities_subtypes=" + id_activities_subtypes +
                ", description='" + description + '\'' +
                '}';
    }
}
