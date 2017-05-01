package com.letsgo.appletsgo.presenter;

import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.mapper.CategoriesDataMapper;
import com.letsgo.appletsgo.domain.interactor.CategoriesInteractor;
import com.letsgo.appletsgo.domain.model.entity.Categories;
import com.letsgo.appletsgo.domain.model.entity.TypeCategoriesList;
import com.letsgo.appletsgo.domain.repository.CategoriesServiceRepository;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackCategories;
import com.letsgo.appletsgo.domain.repository.interactor.RequestCallBackPreferencesCategories;
import com.letsgo.appletsgo.repository.Categories.CategoriesDataRepository;
import com.letsgo.appletsgo.repository.datasource.categories.CategoriesDataStoreFactory;
import com.letsgo.appletsgo.view.CategoriesView;

import java.util.List;

/**
 * Created by louislopez on 5/03/17.
 */

public class CategoriesPresenter implements Presenter<CategoriesView>, RequestCallBackCategories, RequestCallBackPreferencesCategories {
    private static final String TAG = "ActividadesPresenter";
    private CategoriesView categoriesView;
    private CategoriesInteractor categoriesInteractor;
    private CategoriesServiceRepository categoriesServiceRepository;

    public void getCategories(){
        categoriesView.showLoading();
        categoriesInteractor.listCategoriesInteractor(this);
    }

    public void saveCategoriesToPreferences(List<Categories> categoriesList){
        categoriesView.showLoading();
        categoriesInteractor.saveCategoriesToPreferences(categoriesList, this);
    }

    @Override
    public void onRequestSuccess(Object object) {
        categoriesView.hideLoading();
        LogUtils.v(TAG, "onLogInSuccess " + object);
        TypeCategoriesList typeCategoriesList= (TypeCategoriesList) object;
        if (typeCategoriesList != null) {
            categoriesView.getCategories(typeCategoriesList);
        }
    }

    @Override
    public void onRequestFailure(Throwable e) {
        categoriesView.hideLoading();
        categoriesView.showEmptyCategories();
    }

    @Override
    public void attachedView(CategoriesView view) {
        categoriesView = view;
        categoriesServiceRepository = new CategoriesDataRepository(new CategoriesDataStoreFactory(categoriesView.getContext()), new CategoriesDataMapper());
        categoriesInteractor = new CategoriesInteractor(categoriesServiceRepository);
    }

    @Override
    public void deatchView() {
        categoriesView = null;
    }

    @Override
    public void onSaveCategoriesPreferencesSuccess(Object object) {
        categoriesView.hideLoading();

    }

    @Override
    public void onGetCategoriesPreferencesSuccess(Object object) {

    }
}
