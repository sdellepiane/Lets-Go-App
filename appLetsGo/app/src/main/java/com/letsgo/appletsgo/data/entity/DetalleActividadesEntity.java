package com.letsgo.appletsgo.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by louislopez on 5/03/17.
 {
 "status": 1,
 "msg": "OK",
 "data": [
 {
 "id_activities": "76",
 "activity": "The Joshua Tree 30º Aniversario: Tributo a U2 en vivo",
 "types": "Conciertos",
 "subtypes": "Tributo",
 "long_description": "Fue en Marzo de 1987 cuando el mundo pudo conocer el que sería el álbum definitivo de la banda irlandesa U2, el disco que marcaría un antes y un después en su historia y en la de muchos alrededor del globo.\r\n\r\nEl cuarteto conformado por Bono, The Edge, Adam Clayton y Larry Mullen Jr. ya estaba situado entre los grandes nombres del rock de los ochentas, y sus anteriores esfuerzos discográficos habían ayudado a renovar el sonido guitarrero del post punk gracias a la magia de la tecnología, en pleno desarrollo durante esos años. Ese fue el sonido, junto con la inspiración del blues, folk y gospel adquirida durante sus giras a Norteamérica, el que impregnaron en los temas que serían lanzados bajo el nombre de \"The Joshua Tree\".\r\n\r\nClásicos instantáneos como \"Where The Streets Have No Name\", \"I Still Haven't Found What I'm Looking For\", \"Bullet The Blue Sky\" y el megahit \"With Or Without You\" catapultaron a U2 hacia la cima del rock mundial, cuyos limites ni los músicos implicados ni la crítica especializada pudieron vislumbrar. Es por este motivo, a treinta años de su lanzamiento, que celebraremos este hito en la historia del rock junto a Glammers en nuestro escenario estelar, con un show como sólo Sargento Pimienta puede ofrecer. ",
 "public": "Adultos",
 "logo_organizer": null,
 "web_organizer": "xx",
 "business_name": "ORGANIZADOR GENERAL",
 "face_organizer": "xx",
 "places": [
 {
 "id_activities_places": "61",
 "name_place": "Sargento Pimienta",
 "addresses": "Av. Francisco Bolognesi 757",
 "group_date": [],
 "prices": []
 }
 ],
 "img": [
 {
 "id_activities_collections": "54",
 "path": "http://imaginaccion.net/cms/App/Assets/images/activities/201703050627140.jpg",
 "weight": "1"
 }
 ]
 }
 ],
 "data_error": []
 }
 */

public class DetalleActividadesEntity implements Serializable {
    private String id_activities;
    private String activity;
    private String types;
    private String subtypes;
    private String long_description;
    //private String public;
    private String logo_organizer;
    private String web_organizer;
    private String business_name;
    private String face_organizer;
    private List<PlacesEntity> places ;
    private List<AdvertisementsEntity> advertisements;
    /* TODO
    "group_date": []
    "prices": []
    */
    private List<ImagenesActividadesEntity> img ;

    public String getId_activities() {
        return id_activities;
    }

    public void setId_activities(String id_activities) {
        this.id_activities = id_activities;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(String subtypes) {
        this.subtypes = subtypes;
    }

    public String getLong_description() {
        return long_description;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    public String getLogo_organizer() {
        return logo_organizer;
    }

    public void setLogo_organizer(String logo_organizer) {
        this.logo_organizer = logo_organizer;
    }

    public String getWeb_organizer() {
        return web_organizer;
    }

    public void setWeb_organizer(String web_organizer) {
        this.web_organizer = web_organizer;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getFace_organizer() {
        return face_organizer;
    }

    public void setFace_organizer(String face_organizer) {
        this.face_organizer = face_organizer;
    }

    public List<PlacesEntity> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlacesEntity> places) {
        this.places = places;
    }

    public List<ImagenesActividadesEntity> getImg() {
        return img;
    }

    public void setImg(List<ImagenesActividadesEntity> img) {
        this.img = img;
    }

    public List<AdvertisementsEntity> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<AdvertisementsEntity> advertisements) {
        this.advertisements = advertisements;
    }

    @Override
    public String toString() {
        return "DetalleActividadesEntity{" +
                "id_activities='" + id_activities + '\'' +
                ", activity='" + activity + '\'' +
                ", types='" + types + '\'' +
                ", subtypes='" + subtypes + '\'' +
                ", long_description='" + long_description + '\'' +
                ", logo_organizer='" + logo_organizer + '\'' +
                ", web_organizer='" + web_organizer + '\'' +
                ", business_name='" + business_name + '\'' +
                ", face_organizer='" + face_organizer + '\'' +
                ", places=" + places +
                ", advertisements=" + advertisements +
                ", img=" + img +
                '}';
    }
}
