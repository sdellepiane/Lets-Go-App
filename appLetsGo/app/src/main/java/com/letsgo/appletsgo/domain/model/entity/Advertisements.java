package com.letsgo.appletsgo.domain.model.entity;

import java.io.Serializable;

/**
 * Created by louislopez on 13/06/17.
 */

public class Advertisements implements Serializable {
    private String description;
    private String path;
    private String link;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "AdvertisementsEntity{" +
                "description='" + description + '\'' +
                ", path='" + path + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
