package com.letsgo.appletsgo.repository.datasource.favorites;

import android.content.Context;

import com.letsgo.appletsgo.data.rest.ApiClient;
import com.letsgo.appletsgo.data.store.SessionUser;
import com.letsgo.appletsgo.data.store.db.DaoFactory;
import com.letsgo.appletsgo.repository.datasource.actividades.DatabaseActividadesDataStore;
import com.letsgo.appletsgo.repository.datasource.categories.PreferencesCategoriesDataStore;

/**
 * Created by louislopez on 4/03/17.
 */

public class FavoritesDataStoreFactory {
    public static final  int DB=1;
    public static final  int CLOUD=2;
    public static final  int PREFERENCES=3;
    public static final  int IMG_LIBRARY=4;
    private final Context context;

    public FavoritesDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
    }

    public Object create(int dataSource){
        Object object = null;

        switch (dataSource) {
            case CLOUD:
                break;
            case DB:
                object= new DatabaseFavoritesDataStore(new DaoFactory(context));
                break;
            case PREFERENCES:
                //detailWalkingServiceDataStore = new PrefDetailWalkingServiceDataStore(context,new PreferencesHelper());
                break;
            case IMG_LIBRARY:
                //detailWalkingServiceDataStore = new ImageLibraryWalkingServiceDataStore(context, new ImageHelper());
                break;
        }
        return object;
    }
}
