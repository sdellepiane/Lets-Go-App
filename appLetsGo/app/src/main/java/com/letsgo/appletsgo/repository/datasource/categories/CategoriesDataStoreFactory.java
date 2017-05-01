package com.letsgo.appletsgo.repository.datasource.categories;

import android.content.Context;

import com.letsgo.appletsgo.data.rest.ApiClient;
import com.letsgo.appletsgo.data.store.SessionUser;
import com.letsgo.appletsgo.repository.datasource.actividades.ActividadesServiceDataStore;
import com.letsgo.appletsgo.repository.datasource.actividades.CloudActividadesSdataStore;

/**
 * Created by louislopez on 4/03/17.
 */

public class CategoriesDataStoreFactory {
    public static final  int DB=1;
    public static final  int CLOUD=2;
    public static final  int PREFERENCES=3;
    public static final  int IMG_LIBRARY=4;
    private final Context context;

    public CategoriesDataStoreFactory(Context context) {
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
                //detailWalkingServiceDataStore= new DbDetailWalkingServiceDataStore();
                break;
            case PREFERENCES:
                object = new PreferencesCategoriesDataStore(context, new SessionUser());
                break;
            case IMG_LIBRARY:
                //detailWalkingServiceDataStore = new ImageLibraryWalkingServiceDataStore(context, new ImageHelper());
                break;
        }
        return object;
    }

    public CategoriesServiceDataStore createCloudDataStore(){
        ApiClient restApi = new ApiClient(this.context);
        return new CloudCategoriesDataStore(restApi);
    }

}
