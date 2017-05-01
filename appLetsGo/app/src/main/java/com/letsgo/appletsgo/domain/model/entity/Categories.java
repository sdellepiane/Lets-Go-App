package com.letsgo.appletsgo.domain.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public class Categories implements Serializable{

    private int id_activities_types;
    private String description;
    private boolean selected;
    private List<Subcategories> subcategoriesList;

    public int getId_activities_types() {
        return id_activities_types;
    }

    public void setId_activities_types(int id_activities_types) {
        this.id_activities_types = id_activities_types;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Subcategories> getSubcategoriesList() {
        return subcategoriesList;
    }

    public void setSubcategoriesList(List<Subcategories> subcategoriesList) {
        this.subcategoriesList = subcategoriesList;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id_activities_types=" + id_activities_types +
                ", description='" + description + '\'' +
                ", subcategoriesList=" + subcategoriesList +
                '}';
    }
}
