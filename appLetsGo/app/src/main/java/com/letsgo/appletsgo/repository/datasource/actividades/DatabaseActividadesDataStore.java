package com.letsgo.appletsgo.repository.datasource.actividades;

import com.letsgo.appletsgo.data.store.db.DaoFactory;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.repository.actividades.RepositoryCallBackActividades;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergio on 18/5/17.
 */

public class DatabaseActividadesDataStore implements ActividadesDatabaseDataStore {

    private DaoFactory daoFactory;

    public DatabaseActividadesDataStore(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public List<Actividades> getActividadesList() {
        List<Actividades> actividadesList = new ArrayList<>();
        try {
            daoFactory.getActivitiesDAO();
            actividadesList = daoFactory.getFavorites();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actividadesList;
    }

    @Override
    public void saveFavorite(Actividades actividades, RepositoryCallBackActividades repositoryCallBackActividades) {
        try {
            daoFactory.getActivitiesDAO();
            daoFactory.createUpdateFavorite(actividades);
            repositoryCallBackActividades.onSuccess(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFavorite(int idFavorite, RepositoryCallBackActividades repositoryCallBackActividades) {
        try {
            daoFactory.getActivitiesDAO();
            daoFactory.deleteFavorite(idFavorite);
            repositoryCallBackActividades.onSuccess(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getFavorite(int idActivity, RepositoryCallBackActividades repositoryCallBackActividades) {
        try {
            daoFactory.getActivitiesDAO();
            repositoryCallBackActividades.onSuccess(daoFactory.getFavorite(idActivity));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void assignFavorites(List<Actividades> actividadesList, RepositoryCallBackActividades repositoryCallBackActividades) {
        try {
            daoFactory.getActivitiesDAO();
            repositoryCallBackActividades.onSuccess(daoFactory.assignFavorites(actividadesList));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}