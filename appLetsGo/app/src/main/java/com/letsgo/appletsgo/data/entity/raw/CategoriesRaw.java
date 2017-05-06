package com.letsgo.appletsgo.data.entity.raw;

import java.io.Serializable;
import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public class CategoriesRaw implements Serializable {
    private int id;
    private List<SubcategoriesRaw> filterSubtypes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SubcategoriesRaw> getFilterSubtypes() {
        return filterSubtypes;
    }

    public void setFilterSubtypes(List<SubcategoriesRaw> filterSubtypes) {
        this.filterSubtypes = filterSubtypes;
    }
}
