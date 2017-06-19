package com.letsgo.appletsgo.data.mapper;

import com.letsgo.appletsgo.data.entity.ActividadesEntity;
import com.letsgo.appletsgo.data.entity.AdvertisementsEntity;
import com.letsgo.appletsgo.data.entity.DateGroupEntity;
import com.letsgo.appletsgo.data.entity.DistritoEntity;
import com.letsgo.appletsgo.data.entity.ImagenesActividadesEntity;
import com.letsgo.appletsgo.data.entity.PlacesEntity;
import com.letsgo.appletsgo.data.entity.PricesEntity;
import com.letsgo.appletsgo.data.entity.TimesGroupEntity;
import com.letsgo.appletsgo.data.entity.response.ActividadesResponse;
import com.letsgo.appletsgo.data.entity.response.DetalleActividadResponse;
import com.letsgo.appletsgo.data.entity.response.DistritosResponse;
import com.letsgo.appletsgo.domain.model.entity.Actividades;
import com.letsgo.appletsgo.domain.model.entity.Advertisements;
import com.letsgo.appletsgo.domain.model.entity.DateGroup;
import com.letsgo.appletsgo.domain.model.entity.DetalleActividades;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.model.entity.ImagenesActividades;
import com.letsgo.appletsgo.domain.model.entity.Places;
import com.letsgo.appletsgo.domain.model.entity.Prices;
import com.letsgo.appletsgo.domain.model.entity.TimesGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by louislopez on 5/03/17.
 */

public class ActividadesDataMapper {
    private static String TAG = "ActividadesDataMapper";

    public List<Actividades> transFormActividadesWalkingService(ActividadesResponse actividadesResponse){
        List<Actividades> actividadesList = new ArrayList<>();
        if (actividadesResponse != null){
            Actividades actividades;
            for (ActividadesEntity actividadesEntity : actividadesResponse.getData()){
                try {
                    actividades = new Actividades();
                    actividades.setActivity(actividadesEntity.getActivity());
                    actividades.setId_activities(actividadesEntity.getId_activities());
                    actividades.setPrice(actividadesEntity.getPrice());
                    actividades.setSchedul_date(actividadesEntity.getSchedul_date());
                    actividades.setSchedul_time(actividadesEntity.getSchedul_time());
                    actividades.setType(actividadesEntity.getType());
                    actividades.setPath(actividadesEntity.getPath());
                    actividades.setAdvertisements(actividadesEntity.getAdvertisements());
                    actividadesList.add(actividades);
                }catch (Exception e){

                }
            }
        }
        return actividadesList;
    }

    public DetalleActividades transFormDetalleActividadWalkingService(DetalleActividadResponse response){
        DetalleActividades detalleActividades = null;
        if (response != null){
            try {
                detalleActividades = new DetalleActividades();
                detalleActividades.setId_activities(response.getData().getId_activities());
                detalleActividades.setActivity(response.getData().getActivity());
                detalleActividades.setTypes(response.getData().getTypes());
                detalleActividades.setSubtypes(response.getData().getSubtypes());
                detalleActividades.setLong_description(response.getData().getLong_description());
                detalleActividades.setImg(transFormImagenesWalkingService(response.getData().getImg()));
                detalleActividades.setUrl_event(response.getData().getUrl_event());
                detalleActividades.setPlaces(transFormPlacesWalkingService(response.getData().getPlaces()));
                detalleActividades.setAdvertisements(transFormAdvertisementsWalkingService(response.getData().getAdvertisements()));

            }catch (Exception e){

            }
        }

        return detalleActividades;
    }

    public List<ImagenesActividades> transFormImagenesWalkingService (List<ImagenesActividadesEntity> listImagenes){
        List<ImagenesActividades> imagenesActividadesList = new ArrayList<>();
        ImagenesActividades imagenesActividades;
        for (ImagenesActividadesEntity entity : listImagenes ){
            imagenesActividades = new ImagenesActividades();
            imagenesActividades.setId_activities_collections(entity.getId_activities_collections());
            imagenesActividades.setPath(entity.getPath());
            imagenesActividades.setWeight(entity.getWeight());
            imagenesActividadesList.add(imagenesActividades);
        }

        return imagenesActividadesList;
    }

    public List<Places> transFormPlacesWalkingService(List<PlacesEntity> entityList){
        List<Places> placesList = new ArrayList<>();
        Places places ;
        try {
            places = new Places();
            for (PlacesEntity placesEntity : entityList){
                places.setId_activities_places(placesEntity.getId_activities_places());
                places.setAddresses(placesEntity.getAddresses());
                places.setName_place(placesEntity.getName_place());
                places.setLatitude(placesEntity.getLatitude());
                places.setLongitude(placesEntity.getLongitude());
                places.setGroup_date(transFormDataGroupWalkingService(placesEntity.getGroup_date()));
                places.setPrices(transFormPriceWalkingService(placesEntity.getPrices()));
                placesList.add(places);
            }

        }catch (Exception e){

        }
        return placesList;
    }

    public List<Advertisements> transFormAdvertisementsWalkingService(List<AdvertisementsEntity> entityList){
        List<Advertisements> advertisementsList = new ArrayList<>();
        Advertisements advertisements;
        try {
            for (AdvertisementsEntity entity : entityList){
                advertisements = new Advertisements();
                advertisements.setDescription(entity.getDescription());
                advertisements.setLink(entity.getLink());
                advertisements.setPath(entity.getPath());
                advertisementsList.add(advertisements);
            }
        }catch (Exception e){

        }
        return advertisementsList;
    }

    public List<DateGroup> transFormDataGroupWalkingService(List<DateGroupEntity> entityList){
        List<DateGroup> dateGroupList = new ArrayList<>();
        DateGroup dateGroup;
        try {

            for (DateGroupEntity dateGroupEntity : entityList){
                dateGroup = new DateGroup();
                dateGroup.setSchedule_date(dateGroupEntity.getSchedule_date());
                dateGroup.setSchedule_time(transFormTimeGroupWalkingService(dateGroupEntity.getSchedule_time()));
                dateGroupList.add(dateGroup);
            }
        }catch (Exception e){

        }
        return dateGroupList;
    }

    public List<TimesGroup> transFormTimeGroupWalkingService (List<TimesGroupEntity> entityList){
        List<TimesGroup> timesGroupList = new ArrayList<>();
        TimesGroup timesGroup;
        try {
            for (TimesGroupEntity timesGroupEntity : entityList){
                timesGroup = new TimesGroup();
                timesGroup.setSchedul_time(timesGroupEntity.getSchedul_time());
                timesGroupList.add(timesGroup);
            }
        }catch (Exception e){

        }
        return timesGroupList;
    }

    public List<Prices> transFormPriceWalkingService(List<PricesEntity> entityList){
        List<Prices> pricesList = new ArrayList<>();
        Prices prices;
        try {
            prices = new Prices();
            for (PricesEntity pricesEntity : entityList){
                prices.setPrice(pricesEntity.getPrice());
                prices.setType(pricesEntity.getType());
                pricesList.add(prices);
            }
        }catch (Exception e){

        }
        return pricesList;
    }


    public List<Distrito> transFormDistritoWalkingService(DistritosResponse distritosResponse){
        List<Distrito> distritoList = new ArrayList<>();
        Distrito distrito;
        try {

            for (DistritoEntity distritoEntity : distritosResponse.getData()){
                distrito = new Distrito();
                distrito.setId_ubigeos(distritoEntity.getId_ubigeos());
                distrito.setDescription(distritoEntity.getDescription());
                distritoList.add(distrito);
            }
        }catch (Exception e){

        }

        return distritoList;

    }



}
