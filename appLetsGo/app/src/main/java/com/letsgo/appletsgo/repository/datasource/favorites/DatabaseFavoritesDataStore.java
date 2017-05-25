package com.letsgo.appletsgo.repository.datasource.favorites;

import com.letsgo.appletsgo.data.store.db.DaoFactory;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.repository.Favorites.RepositoryCallBackFavorites;
import com.letsgo.appletsgo.repository.actividades.RepositoryCallBackActividades;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesDatabaseDataStore;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergio on 18/5/17.
 */

public class DatabaseFavoritesDataStore implements FavoritesDatabaseDataStore {

    private DaoFactory daoFactory;

    public DatabaseFavoritesDataStore(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void getFavorites(RepositoryCallBackFavorites repositoryCallBackFavorites) {
        try {
            daoFactory.getActivitiesDAO();
            List<Actividades> favoritesList = daoFactory.getFavorites();
            repositoryCallBackFavorites.onSuccess(favoritesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}