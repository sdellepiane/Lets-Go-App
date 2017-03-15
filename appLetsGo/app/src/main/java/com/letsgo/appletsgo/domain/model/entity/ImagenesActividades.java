package com.letsgo.appletsgo.domain.model.entity;

/**
 * Created by louislopez on 6/03/17.
 */

public class ImagenesActividades {
    private String id_activities_collections;
    private String path;
    private String weight;

    public String getId_activities_collections() {
        return id_activities_collections;
    }

    public void setId_activities_collections(String id_activities_collections) {
        this.id_activities_collections = id_activities_collections;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "ImagenesActividades{" +
                "id_activities_collections='" + id_activities_collections + '\'' +
                ", path='" + path + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
