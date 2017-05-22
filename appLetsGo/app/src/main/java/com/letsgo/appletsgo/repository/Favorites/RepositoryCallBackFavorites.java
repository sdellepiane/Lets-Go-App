package com.letsgo.appletsgo.repository.Favorites;

/**
 * Created by louislopez on 4/03/17.
 */

public interface RepositoryCallBackFavorites<T> {
    void onSuccess(T object);
    void onFailure(Throwable throwable);
}
