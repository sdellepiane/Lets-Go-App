package com.letsgo.appletsgo.data.entity.raw;

import java.io.Serializable;
import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public class ActividadesRaw implements Serializable {
    private String filterPublics;
    private List<CategoriesRaw> filterTypes;
    private String filterPrices;
    private List<PlacesRaw> filterPlaces;
    private int date_days;
    private String date_since;
    private String date_until;
    private String latitude;
    private String longitude;
    private String quantity;
    private String from;

    public String getFilterPublics() {
        return filterPublics;
    }

    public void setFilterPublics(String filterPublics) {
        this.filterPublics = filterPublics;
    }

    public List<CategoriesRaw> getFilterTypes() {
        return filterTypes;
    }

    public void setFilterTypes(List<CategoriesRaw> filterTypes) {
        this.filterTypes = filterTypes;
    }

    public String getFilterPrices() {
        return filterPrices;
    }

    public void setFilterPrices(String filterPrices) {
        this.filterPrices = filterPrices;
    }

    public List<PlacesRaw> getFilterPlaces() {
        return filterPlaces;
    }

    public void setFilterPlaces(List<PlacesRaw> filterPlaces) {
        this.filterPlaces = filterPlaces;
    }

    public int getDate_days() {
        return date_days;
    }

    public void setDate_days(int date_days) {
        this.date_days = date_days;
    }

    public String getDate_since() {
        return date_since;
    }

    public void setDate_since(String date_since) {
        this.date_since = date_since;
    }

    public String getDate_until() {
        return date_until;
    }

    public void setDate_until(String date_until) {
        this.date_until = date_until;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
