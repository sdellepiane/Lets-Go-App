package com.letsgo.appletsgo.data.store.db;

import android.content.Context;
import android.database.SQLException;

import com.j256.ormlite.dao.Dao;
import com.letsgo.appletsgo.domain.model.entity.Actividades;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by sergio on 7/7/16.
 *
 * Clase de manejo de eventos de la base de datos
 */
public class DaoFactory {

    private DatabaseHelper db;
    private Dao<Actividades, Integer> activitiesDAO;

    public DaoFactory(Context context) {
        try {
            DatabaseManager dbManager = new DatabaseManager();
            db = dbManager.getHelper(context);
        } catch (SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
    }

    public Dao<Actividades, Integer> getActivitiesDAO() throws java.sql.SQLException {
        if (activitiesDAO == null) {
            activitiesDAO = db.getDao(Actividades.class);
        }
        return activitiesDAO;
    }

    public void createUpdateFavorite(Actividades actividades) {
        try {
            activitiesDAO.createOrUpdate(actividades);
        }  catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Actividades> getFavorites() {
        try {
            return activitiesDAO.queryBuilder().query();
        } catch (java.sql.SQLException e) {
            // TODO: Exception Handling
            e.printStackTrace();
        }
        return null;
    }

    public void deleteFavorite(int idFavorite) {
        try {
            Actividades actividades = getFavorite(idFavorite);
            if(actividades != null){
                activitiesDAO.delete(actividades);
            }
        }  catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public Actividades getFavorite(int idFavorite) {
        try {
            List<Actividades> actividadesList = activitiesDAO.queryBuilder().where().eq("id_activities", idFavorite).query();
            if(actividadesList.size() > 0){
                return actividadesList.get(0);
            } else{
                return null;
            }
        }  catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Actividades> assignFavorites(List<Actividades> actividadesList) {
        try {
            for(Actividades actividades : actividadesList){
                List<Actividades> favoriteList = activitiesDAO.queryBuilder().where().eq("id_activities", actividades.getId_activities()).query();
                if(favoriteList.size() > 0){
                    actividades.setFavorite(true);
                } else{
                    actividades.setFavorite(false);
                }
            }
            return actividadesList;
        }  catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clearDatabase(){
        db.clearDatabase();
    }
}
