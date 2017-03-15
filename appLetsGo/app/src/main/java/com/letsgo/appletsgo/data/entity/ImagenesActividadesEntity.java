package com.letsgo.appletsgo.data.entity;

/**
 * Created by louislopez on 6/03/17.
 {
 "id_activities_collections": "54",
 "path": "http://imaginaccion.net/cms/App/Assets/images/activities/201703050627140.jpg",
 "weight": "1"
 }
 */

public class ImagenesActividadesEntity {
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
        return "ImagenesActividadesEntity{" +
                "id_activities_collections='" + id_activities_collections + '\'' +
                ", path='" + path + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
