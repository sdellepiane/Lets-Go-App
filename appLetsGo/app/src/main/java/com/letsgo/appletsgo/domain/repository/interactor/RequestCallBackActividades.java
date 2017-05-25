package com.letsgo.appletsgo.domain.repository.interactor;

/**
 * Created by louislopez on 4/03/17.
 */

public interface RequestCallBackActividades<T> {
    void onRequestSuccess(T object);
    void onRequestSuccess(T object, int type);
    void onCategoriesFromPreferencesRequestSuccess(T object);
    void onSaveFavoriteSuccess(T object);
    void onDeleteFavoriteSuccess(T object);
    void onAssignFavoriteSuccess(T object);
    void onRequestFailure(Throwable e);
    void onRequestFailure(Throwable throwable, int type);
}
