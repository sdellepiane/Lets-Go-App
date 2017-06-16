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
    private List<FilterDateRaw> filterDate;
    private String date_days;
    private String latitude;
    private String longitude;
    private String quantity;
    private String from;
    private String q;

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

    public String getDate_days() {
        return date_days;
    }

    public void setDate_days(String date_days) {
        this.date_days = date_days;
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

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public List<FilterDateRaw> getFilterDate() {
        return filterDate;
    }

    public void setFilterDate(List<FilterDateRaw> filterDate) {
        this.filterDate = filterDate;
    }
}
