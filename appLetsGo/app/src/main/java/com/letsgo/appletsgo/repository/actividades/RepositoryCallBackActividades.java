package com.letsgo.appletsgo.repository.actividades;

/**
 * Created by louislopez on 4/03/17.
 */

public interface RepositoryCallBackActividades<T> {
    public void onSuccess(T object);
    public void onSuccess(T response, T header);
    public void onFailure(Throwable throwable);
}
