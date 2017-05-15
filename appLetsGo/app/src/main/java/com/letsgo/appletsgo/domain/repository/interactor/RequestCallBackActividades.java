package com.letsgo.appletsgo.domain.repository.interactor;

/**
 * Created by louislopez on 4/03/17.
 */

public interface RequestCallBackActividades<T> {
    public void onRequestSuccess(T object);
    public void onRequestSuccess(T object, int type);
    public void onCategoriesFromPreferencesRequestSuccess(T object);
    public void onRequestFailure(Throwable e);
    public void onRequestFailure(Throwable throwable, int type);
}
