package com.letsgo.appletsgo.data.entity;

import java.io.Serializable;

/**
 * Created by louislopez on 13/06/17.
 "description": "Gatito",
 "path": "http://imaginaccion.net/cms/App/Assets/images/advertisements/gatito.jpg",
 "link": "https://www.google.com.pe/?gfe_rd=cr&ei=zd4gWY-VCdHI8Af3uYKABw"
 */

public class AdvertisementsEntity implements Serializable{
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
