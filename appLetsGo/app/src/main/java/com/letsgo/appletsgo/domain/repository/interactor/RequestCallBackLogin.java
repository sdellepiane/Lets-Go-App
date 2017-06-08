package com.letsgo.appletsgo.domain.repository.interactor;

/**
 * Created by louislopez on 3/06/17.
 */

public interface RequestCallBackLogin<T> {
    void onRequestSuccess(T object);
    void onRequestSuccess(T object, int type);
    void onRequestFailure(Throwable e);
}
