package com.letsgo.appletsgo.data.mapper;

import com.letsgo.appletsgo.data.entity.ActividadesEntity;
import com.letsgo.appletsgo.data.entity.CategoriesEntity;
import com.letsgo.appletsgo.data.entity.DateGroupEntity;
import com.letsgo.appletsgo.data.entity.DistritoEntity;
import com.letsgo.appletsgo.data.entity.ImagenesActividadesEntity;
import com.letsgo.appletsgo.data.entity.PlacesEntity;
import com.letsgo.appletsgo.data.entity.PricesEntity;
import com.letsgo.appletsgo.data.entity.SubcategoriesEntity;
import com.letsgo.appletsgo.data.entity.TimesGroupEntity;
import com.letsgo.appletsgo.data.entity.TypesCategoriesListEntity;
import com.letsgo.appletsgo.data.entity.response.ActividadesResponse;
import com.letsgo.appletsgo.data.entity.response.CategoriesResponse;
import com.letsgo.appletsgo.data.entity.response.DetalleActividadResponse;
import com.letsgo.appletsgo.data.entity.response.DistritosResponse;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.Categories;
import com.letsgo.appletsgo.domain.model.entity.DateGroup;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.model.entity.ImagenesActividades;
import com.letsgo.appletsgo.domain.model.entity.Places;
import com.letsgo.appletsgo.domain.model.entity.Prices;
import com.letsgo.appletsgo.domain.model.entity.Subcategories;
import com.letsgo.appletsgo.domain.model.entity.TimesGroup;
import com.letsgo.appletsgo.domain.model.entity.TypeCategoriesList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by louislopez on 5/03/17.
 */

public class CategoriesDataMapper {
    private static String TAG = "CategoriesDataMapper";

    public Subcategories transformSubcategorie(SubcategoriesEntity subcategoriesEntity) {
        Subcategories subcategories = null;
        if(subcategoriesEntity !=null){
            subcategories = new Subcategories();

            if(subcategoriesEntity.getId_activities_subtypes() != 0) subcategories.
                    setId_activities_subtypes(subcategoriesEntity.getId_activities_subtypes());
            else subcategories.setId_activities_subtypes(0);

            if(subcategoriesEntity.getDescription() != null) subcategories.
                    setDescription(subcategoriesEntity.getDescription());
            else subcategories.setDescription("");
            subcategories.setSelected(false);
        }

        return subcategories;
    }

    public Categories transformCategories(CategoriesEntity categoriesEntity) {
        List<Subcategories> subcategoriesList = new ArrayList<>();
        Categories categories = null;
        if(categoriesEntity != null){
            categories = new Categories();
            if(categoriesEntity.getId_activities_types() != 0) categories.
                    setId_activities_types(categoriesEntity.getId_activities_types());
            else categories.setId_activities_types(0);

            if(categoriesEntity.getDescription() != null) categories.
                    setDescription(categoriesEntity.getDescription());
            categories.setSelected(false);

            List<SubcategoriesEntity> subcategoriesEntityList = categoriesEntity.getSubtypes();

            if(subcategoriesEntityList != null){
                if(subcategoriesEntityList.size() > 0){
                    try {
                        for(SubcategoriesEntity subcategoriesEntity : subcategoriesEntityList){
                            subcategoriesList.add(transformSubcategorie(subcategoriesEntity));
                        }
                    } catch (Exception e){

                    }
                    categories.setSubcategoriesList(subcategoriesList);
                } else{
                    categories.setSubcategoriesList(new ArrayList<Subcategories>());
                }
            } else{
                categories.setSubcategoriesList(new ArrayList<Subcategories>());
            }
        }

        return categories;
    }

    public TypeCategoriesList transformTypeCategories(TypesCategoriesListEntity typesCategoriesListEntity) {
        List<Categories> categoriesList = new ArrayList<>();
        TypeCategoriesList typeCategoriesList = null;
        if(typesCategoriesListEntity != null){
            typeCategoriesList = new TypeCategoriesList();

            List<CategoriesEntity> categoriesEntityList = typesCategoriesListEntity.getTypes();

            if(categoriesEntityList != null){
                if(categoriesEntityList.size() > 0){
                    try {
                        for(CategoriesEntity categoriesEntity : categoriesEntityList){
                            categoriesList.add(transformCategories(categoriesEntity));
                        }
                    } catch (Exception e){

                    }
                    typeCategoriesList.setCategoriesList(categoriesList);
                } else{
                    typeCategoriesList.setCategoriesList(new ArrayList<Categories>());
                }
            } else{
                typeCategoriesList.setCategoriesList(new ArrayList<Categories>());
            }
        }

        return typeCategoriesList;
    }



}
