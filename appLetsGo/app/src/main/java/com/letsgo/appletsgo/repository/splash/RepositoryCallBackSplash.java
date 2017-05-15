package com.letsgo.appletsgo.repository.splash;

/**
 * Created by louislopez on 4/03/17.
 */

public interface RepositoryCallBackSplash<T> {
    public void onSuccess(T object);
    public void onSuccess(T response, T header);
    public void onFailure(Throwable throwable);
}
