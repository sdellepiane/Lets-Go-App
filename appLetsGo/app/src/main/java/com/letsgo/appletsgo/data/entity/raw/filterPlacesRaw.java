package com.letsgo.appletsgo.data.entity.raw;

import java.io.Serializable;

/**
 * Created by louislopez on 13/03/17.
 */

public class filterPlacesRaw implements Serializable{
    private String id;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "filterPlacesRaw{" +
                "id='" + id + '\'' +
                '}';
    }
}
