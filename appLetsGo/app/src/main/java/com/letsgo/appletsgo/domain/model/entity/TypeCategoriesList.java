package com.letsgo.appletsgo.domain.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sergio on 8/4/17.
 */

public class TypeCategoriesList implements Serializable {

    private List<Categories> categoriesList;

    public List<Categories> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @Override
    public String toString() {
        return "TypeCategoriesList{" +
                "categoriesList=" + categoriesList +
                '}';
    }
}
