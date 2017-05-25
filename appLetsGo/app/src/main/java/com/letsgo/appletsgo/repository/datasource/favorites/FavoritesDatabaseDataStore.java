package com.letsgo.appletsgo.repository.datasource.favorites;

import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.repository.Favorites.RepositoryCallBackFavorites;
import com.letsgo.appletsgo.repository.actividades.RepositoryCallBackActividades;

import java.util.List;

/**
 * Created by sergio on 18/5/17.
 */

public interface FavoritesDatabaseDataStore {

    void getFavorites(RepositoryCallBackFavorites repositoryCallBackFavorites);
}
