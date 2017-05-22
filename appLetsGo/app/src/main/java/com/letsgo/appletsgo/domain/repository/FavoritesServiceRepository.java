package com.letsgo.appletsgo.domain.repository;

import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackActividades;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackFavorites;
import com.letsgo.appletsgo.repository.Favorites.RepositoryCallBackFavorites;

import java.util.List;

/**
 * Created by louislopez on 4/03/17.
 */

public interface FavoritesServiceRepository {
    void getFavoritesRequest(RequestCallBackFavorites requestCallBackFavorites);
}
