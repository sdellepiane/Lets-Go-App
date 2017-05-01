package com.letsgo.appletsgo.domain.repository.interactor;

/**
 * Created by louislopez on 4/03/17.
 */

public interface RequestCallBackCategories<T> {
    void onRequestSuccess(T object);
    void onRequestFailure(Throwable e);
}
