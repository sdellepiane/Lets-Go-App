package com.letsgo.appletsgo.domain.repository.interactor;

/**
 * Created by louislopez on 4/03/17.
 */

public interface RequestCallBackPreferencesCategories<T> {
    void onSaveCategoriesPreferencesSuccess(T object);
    void onGetCategoriesPreferencesSuccess(T object);
}
