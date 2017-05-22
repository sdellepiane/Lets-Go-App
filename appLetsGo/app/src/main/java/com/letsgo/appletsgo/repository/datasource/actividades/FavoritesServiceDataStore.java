package com.letsgo.appletsgo.repository.datasource.actividades;

import com.letsgo.appletsgo.data.entity.raw.ActividadesRaw;
import com.letsgo.appletsgo.data.entity.raw.DetalleActividadRaw;
import com.letsgo.appletsgo.repository.Favorites.RepositoryCallBackFavorites;
import com.letsgo.appletsgo.repository.actividades.RepositoryCallBackActividades;

/**
 * Created by louislopez on 4/03/17.
 */

public interface FavoritesServiceDataStore {
    void getFavorites(RepositoryCallBackFavorites repositoryCallBackFavorites);
}
