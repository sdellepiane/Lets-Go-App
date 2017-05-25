package com.letsgo.appletsgo.repository.datasource.actividades;

import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.repository.actividades.RepositoryCallBackActividades;

import java.util.List;

/**
 * Created by sergio on 18/5/17.
 */

public interface ActividadesDatabaseDataStore {

    List<Actividades> getActividadesList();
    void saveFavorite(Actividades actividades, RepositoryCallBackActividades repositoryCallBackActividades);
    void deleteFavorite(int idActivity, RepositoryCallBackActividades repositoryCallBackActividades);
    void getFavorite(int idActivity, RepositoryCallBackActividades repositoryCallBackActividades);
    void assignFavorites(List<Actividades> actividadesList, RepositoryCallBackActividades repositoryCallBackActividades);
}
