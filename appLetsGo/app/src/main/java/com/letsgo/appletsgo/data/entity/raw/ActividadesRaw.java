package com.letsgo.appletsgo.data.entity.raw;

import java.io.Serializable;
import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public class ActividadesRaw implements Serializable {
    private String filterPublics;
    private String filterFree;
    private String q;
    private List<filterPlacesRaw> filterPlaces;

    public String getFilterPublics() {
        return filterPublics;
    }

    public void setFilterPublics(String filterPublics) {
        this.filterPublics = filterPublics;
    }

    public String getFilterFree() {
        return filterFree;
    }

    public void setFilterFree(String filterFree) {
        this.filterFree = filterFree;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public List<filterPlacesRaw> getFilterPlaces() {
        return filterPlaces;
    }

    public void setFilterPlaces(List<filterPlacesRaw> filterPlaces) {
        this.filterPlaces = filterPlaces;
    }

    @Override
    public String toString() {
        return "ActividadesRaw{" +
                "filterPublics='" + filterPublics + '\'' +
                ", filterFree='" + filterFree + '\'' +
                ", q='" + q + '\'' +
                ", filterPlaces=" + filterPlaces +
                '}';
    }
}
