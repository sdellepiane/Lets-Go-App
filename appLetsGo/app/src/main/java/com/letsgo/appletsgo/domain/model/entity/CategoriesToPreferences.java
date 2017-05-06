package com.letsgo.appletsgo.domain.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sergio on 1/5/17.
 */

public class CategoriesToPreferences implements Serializable {

    private int publicType;
    private List<Categories> categoriesList;

    public int getPublicType() {
        return publicType;
    }

    public void setPublicType(int publicType) {
        this.publicType = publicType;
    }

    public List<Categories> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }
}
