package com.letsgo.appletsgo.data.entity;

import java.util.List;

/**
 * Created by sergio on 8/4/17.
 */

public class TypesCategoriesListEntity {

    private List<CategoriesEntity> types;

    public List<CategoriesEntity> getTypes() {
        return types;
    }

    public void setTypes(List<CategoriesEntity> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "TypesCategoriesListEntity{" +
                "types=" + types +
                '}';
    }
}
