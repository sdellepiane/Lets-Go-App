package com.letsgo.appletsgo.data.entity;

import java.util.List;

/**
 * Created by sergio on 8/4/17.
 */

public class CategoriesEntity {

    private int id_activities_types;
    private String description;
    private List<SubcategoriesEntity> subtypes;

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

    public List<SubcategoriesEntity> getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(List<SubcategoriesEntity> subtypes) {
        this.subtypes = subtypes;
    }
}
