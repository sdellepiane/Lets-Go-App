package com.letsgo.appletsgo.data.rest;

import android.content.Context;
import android.util.Log;

import com.letsgo.appletsgo.data.entity.response.ActividadesResponse;
import com.letsgo.appletsgo.data.entity.response.CategoriesResponse;
import com.letsgo.appletsgo.data.entity.response.DetalleActividadResponse;
import com.letsgo.appletsgo.data.entity.response.DistritosResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by louislopez on 4/03/17.
 */

public class ApiClient {
    private static LetsGoInterface letsGoInterface;

    private static final String TAG = "ApliCliente";
    public static final String HOST   = "http://imaginaccion.net/";
    public static final String HEADER_TOKEN = "";
    private final Context context;

    public ApiClient(Context context) {
        if (context == null ) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
    }




    public LetsGoInterface getLetsGoTokenInterface(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .client(getBasicClientInterceptor())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        letsGoInterface = retrofit.create(LetsGoInterface.class);
        return letsGoInterface;
    }

    public interface LetsGoInterface{
        @POST("cms/api/activities_for_filters")
        Call<ActividadesResponse> getActividades(@Body Object raw);

        @POST("cms/api/activities_detail")
        Call<DetalleActividadResponse> getDetailActividad(@Body Object raw);

        @POST("cms/api/ubigeos_actives")
        Call<DistritosResponse> getDistritos();

        @GET("cms/api/activities_subtypes_by_types")
        Call<CategoriesResponse> getCategories();



//        @POST("api?action=get_items_temps_15_apps")
//        Call<ProductResponse> getDetailProduct(@Body Object raw);
//
//        @GET("api?action=get_stores_to_apps")
//        Call<TiendasResponse> getTiendas();
    }

    public static OkHttpClient getBasicClientInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //builder.interceptors().add(new LoggingInterceptor());
        builder.interceptors().add(logging);
        OkHttpClient client = builder.build();
        return client;
    }

    public static OkHttpClient getTokenClientInterceptor(final String token){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(00, TimeUnit.MILLISECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Authorization", token)
                        .method(original.method(), original.body());
                Request request = requestBuilder.build();
                Response response = chain.proceed(request);
                //return chain.proceed(request);
                ResponseBody responseBody = response.body();
                long contentLength = responseBody.contentLength();
                String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
                Log.v(TAG, "<-- " + response.code() + ' ' + response.message() + ' '
                        + response.request().url() + " (" + bodySize + " body - " + response.protocol() + ')');

                return response;
            }
        });
        OkHttpClient client = builder.build();

        return client;
    }


}
