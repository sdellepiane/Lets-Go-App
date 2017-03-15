package com.letsgo.appletsgo.presenter;

/**
 * Created by louislopez on 5/03/17.
 */

public interface Presenter<V> {
    void attachedView(V view);
    void deatchView();
}
