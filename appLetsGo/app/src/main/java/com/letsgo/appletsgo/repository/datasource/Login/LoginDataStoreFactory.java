package com.letsgo.appletsgo.repository.datasource.Login;

import android.content.Context;

import com.letsgo.appletsgo.data.rest.ApiClient;
import com.letsgo.appletsgo.data.store.SessionUser;
import com.letsgo.appletsgo.data.store.db.DaoFactory;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesServiceDataStore;
import com.letsgo.appletsgo.repository.datasource.actividades.CloudActividadesSdataStore;
import com.letsgo.appletsgo.repository.datasource.actividades.DatabaseActividadesDataStore;
import com.letsgo.appletsgo.repository.datasource.categories.PreferencesCategoriesDataStore;

/**
 * Created by louislopez on 3/06/17.
 */

public class LoginDataStoreFactory {
    public static final  int DB=1;
    public static final  int CLOUD=2;
    public static final  int PREFERENCES=3;
    public static final  int IMG_LIBRARY=4;
    private final Context context;

    public LoginDataStoreFactory(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
    }

    public Object create(int dataSource){
        Object object = null;

        switch (dataSource) {
            case CLOUD:
                object = createCloudDataStore();
                break;
            case DB:
                object= new DatabaseActividadesDataStore(new DaoFactory(context));
                break;
            case PREFERENCES:
                object = new PreferencesCategoriesDataStore(context, new SessionUser());
                //detailWalkingServiceDataStore = new PrefDetailWalkingServiceDataStore(context,new PreferencesHelper());
                break;
            case IMG_LIBRARY:
                //detailWalkingServiceDataStore = new ImageLibraryWalkingServiceDataStore(context, new ImageHelper());
                break;
        }
        return object;
    }

    public LoginServiceDataStore createCloudDataStore(){
        ApiClient restApi = new ApiClient(this.context);
        return new CloudLoginSdataStore(restApi);
    }

}
