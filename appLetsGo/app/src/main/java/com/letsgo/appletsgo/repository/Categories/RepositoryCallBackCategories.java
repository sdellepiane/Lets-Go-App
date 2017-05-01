package com.letsgo.appletsgo.repository.Categories;

/**
 * Created by louislopez on 4/03/17.
 */

public interface RepositoryCallBackCategories<T> {
    void onSuccess(T object);
    void onFailure(Throwable throwable);
}
