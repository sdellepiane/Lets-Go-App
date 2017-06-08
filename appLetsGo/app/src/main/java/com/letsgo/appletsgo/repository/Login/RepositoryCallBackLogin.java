package com.letsgo.appletsgo.repository.Login;

/**
 * Created by louislopez on 3/06/17.
 */

public interface RepositoryCallBackLogin<T> {
    void onSuccess(T object);
    void onSuccess(T response, T header);
    void onFailure(Throwable throwable);
}
