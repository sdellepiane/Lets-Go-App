package com.letsgo.appletsgo.data.entity.raw;

import java.io.Serializable;
import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public class ActividadesRaw implements Serializable {
    private String filterPublics;
    private String filterFree;
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

    public List<filterPlacesRaw> getFilterPlaces() {
        return filterPlaces;
    }

    public void setFilterPlaces(List<filterPlacesRaw> filterPlaces) {
        this.filterPlaces = filterPlaces;
    }
}
