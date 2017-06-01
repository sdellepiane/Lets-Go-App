package com.letsgo.appletsgo.app.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.component.CustomTextView;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.DialogUtil;
import com.letsgo.appletsgo.domain.model.entity.Categories;
import com.letsgo.appletsgo.domain.model.entity.CategoriesToPreferences;
import com.letsgo.appletsgo.domain.model.entity.Subcategories;
import com.letsgo.appletsgo.domain.model.entity.TypeCategoriesList;
import com.letsgo.appletsgo.presenter.CategoriesPresenter;
import com.letsgo.appletsgo.view.CategoriesView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterOptionActivity extends BaseAppCompat implements CategoriesView, DialogUtil.OnDialogListener {
    private static final String TAG = "FilterFirstActivity";

    @BindView(R.id.iviBack) ImageView iviBack;
    @BindView(R.id.llaCategories) LinearLayout llaCategories;
    @BindView(R.id.tviGeneral) TextView tviGeneral;
    @BindView(R.id.tviAdultos) TextView tviAdultos;
    @BindView(R.id.tviNinos) TextView tviNinos;
    @BindView(R.id.tviTerceraEdad) TextView tviTerceraEdad;
    @BindView(R.id.vieLoading) View vieLoading;

    private boolean statusGeneral = true;
    private boolean statusAdultos = false;
    private boolean statusNinos = false;
    private boolean statusTerceraEdad = false;
    private CategoriesPresenter categoriesPresenter;
    private LinearLayout linearLayout;
    private LinearLayout subLinearLayout;
    private LinearLayout subcontentLinearLayout;
    private boolean sublinearLayoutAdded = false;
    private int positionCategory = 0;
    private List<Categories> categoriesToSendList;
    private List<Subcategories> subcategoriesToSendList;
    private DialogUtil dialogUtil;
    private Dialog dialog;
    private TypeCategoriesList typeCategoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_option);
        ButterKnife.bind(this);

        initPresenter();
        categoriesPresenter.getCategories();
        subcategoriesToSendList = new ArrayList<>();
        dialogUtil = new DialogUtil(this);

        initOnclickListenerViews(iviBack);
    }

    private void initPresenter(){
        categoriesPresenter = new CategoriesPresenter();
        categoriesPresenter.attachedView(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void getCategories(TypeCategoriesList typeCategoriesList) {
        this.typeCategoriesList = typeCategoriesList;
        categoriesPresenter.getCategoriesFromPreferences();
    }

    @Override
    public void showLoading() {
        vieLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        vieLoading.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyCategories() {

    }

    @Override
    public void showMessageError(String message) {

    }

    @Override
    public void saveCategoriesToPreferencesSuccess() {
        nextActivity(HomeActivity.class, false);
    }

    @Override
    public void getCategoriesFromPreferences(CategoriesToPreferences categoriesToPreferences) {
        categoriesToSendList = categoriesToPreferences.getCategoriesList();
        switch(categoriesToPreferences.getPublicType()){
            case 1:
                tviGeneral.setTextColor(getResources().getColor(R.color.white));
                tviGeneral.setBackgroundResource(R.drawable.type_public_on);
                break;
            case 2:
                tviAdultos.setTextColor(getResources().getColor(R.color.white));
                tviAdultos.setBackgroundResource(R.drawable.type_public_on);
                break;
            case 3:
                tviNinos.setTextColor(getResources().getColor(R.color.white));
                tviNinos.setBackgroundResource(R.drawable.type_public_on);
                break;
            case 4:
                tviTerceraEdad.setTextColor(getResources().getColor(R.color.white));
                tviTerceraEdad.setBackgroundResource(R.drawable.type_public_on);
                break;
        }
        fillCategoriesFromPreferences();
    }

    private void fillCategoriesFromPreferences(){
        List<Categories> categoriesList = typeCategoriesList.getCategoriesList();
        List<Categories> categoriesListFromPreferences = categoriesToSendList;
        for(Categories categories : categoriesList){
            for(Categories categoriesPrefs : categoriesListFromPreferences){
                if(categories.getId_activities_types() == categoriesPrefs.getId_activities_types()){
                    categories.setSelected(true);
                }
            }
        }

        TypeCategoriesList typeCategoriesList = new TypeCategoriesList();
        typeCategoriesList.setCategoriesList(categoriesList);
        setCategoriesToScreen(typeCategoriesList);
    }

    @Override
    public void onAccept(int index) {
        switch(index){
            case 0:
                dialog.dismiss();
                break;
        }
    }

    @Override
    public void onCancel(int index) {
        switch(index){
            case 0:
                dialog.dismiss();
                break;
        }
    }

    private void setCategoriesToScreen(TypeCategoriesList typeCategoriesList){
        final List<Categories> categoriesList = typeCategoriesList.getCategoriesList();
        initLinearLayout();
        for(int i = 0; i< categoriesList.size(); i++){
            final Categories categories = categoriesList.get(i);
            if(i > 0 && i % 3 == 0){
                llaCategories.addView(linearLayout);
                initLinearLayout();
            }
            final CustomTextView tviCategory = new CustomTextView(this);
            tviCategory.setText(categories.getDescription());
            if(categories.isSelected()){
                tviCategory.setBackgroundResource(R.drawable.type_public_on);
                tviCategory.setTextColor(getResources().getColor(R.color.white));
            } else{
                tviCategory.setBackgroundResource(R.drawable.type_public_off);
                tviCategory.setTextColor(getResources().getColor(R.color.secondary_text));
            }
            tviCategory.setGravity(Gravity.CENTER);
            int textview_category_left_right;
            int textview_category_top_bottom;
            int margin_textview_category_left;
            int margin_textview_category_top;
            int margin_textview_category_right;
            final int padding_textview_subcategory_left_right;
            final int padding_textview_subcategory_top_bottom;

            float density = getResources().getDisplayMetrics().density;
            if(density == 1){
                textview_category_left_right = 20;
                textview_category_top_bottom = 15;
                margin_textview_category_left = 2;
                margin_textview_category_top = 2;
                margin_textview_category_right = 2;
                padding_textview_subcategory_left_right = 15;
                padding_textview_subcategory_top_bottom = 10;
            } else if(density == 1.5){
                textview_category_left_right = 22;
                textview_category_top_bottom = 17;
                margin_textview_category_left = 2;
                margin_textview_category_top = 2;
                margin_textview_category_right = 2;
                padding_textview_subcategory_left_right = 17;
                padding_textview_subcategory_top_bottom = 12;
            } else if(density == 2){
                textview_category_left_right = 24;
                textview_category_top_bottom = 19;
                margin_textview_category_left = 2;
                margin_textview_category_top = 2;
                margin_textview_category_right = 2;
                padding_textview_subcategory_left_right = 19;
                padding_textview_subcategory_top_bottom = 14;
            } else if(density == 2.5){
                textview_category_left_right = 26;
                textview_category_top_bottom = 21;
                margin_textview_category_left = 4;
                margin_textview_category_top = 4;
                margin_textview_category_right = 4;
                padding_textview_subcategory_left_right = 21;
                padding_textview_subcategory_top_bottom = 16;
            } else if(density == 3){
                textview_category_left_right = 28;
                textview_category_top_bottom = 23;
                margin_textview_category_left = 6;
                margin_textview_category_top = 6;
                margin_textview_category_right = 6;
                padding_textview_subcategory_left_right = 23;
                padding_textview_subcategory_top_bottom = 18;
            } else {
                textview_category_left_right = 30;
                textview_category_top_bottom = 25;
                margin_textview_category_left = 8;
                margin_textview_category_top = 8;
                margin_textview_category_right = 8;
                padding_textview_subcategory_left_right = 25;
                padding_textview_subcategory_top_bottom = 20;
            }
            tviCategory.setPadding(
                    textview_category_left_right,
                    textview_category_top_bottom,
                    textview_category_left_right,
                    textview_category_top_bottom);
            tviCategory.setTextSize(16);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(
                    margin_textview_category_left,
                    margin_textview_category_top,
                    margin_textview_category_right,
                    0);
            tviCategory.setLayoutParams(layoutParams);
            linearLayout.addView(tviCategory);
            if(i == categoriesList.size() - 1){
                llaCategories.addView(linearLayout);
            }
            final int finalI = i;
            tviCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final List<Subcategories> subcategoriesList = categories.getSubcategoriesList();
                    if(categories.isSelected()){
                        categories.setSelected(false);
                        showPublicTypeChange(false, tviCategory);
                        sublinearLayoutAdded = false;
                        if(subLinearLayout != null && subLinearLayout.getChildCount() > 0){
                            llaCategories.removeViewAt(positionCategory + 1);
                            subLinearLayout.removeAllViews();
                            subcontentLinearLayout.removeAllViews();
                        }
                        for(Subcategories subcategories : subcategoriesList){
                            subcategories.setSelected(false);
                        }
                        for(int i = 0; i<categoriesToSendList.size(); i++){
                            Categories categorieToDelete = categoriesToSendList.get(i);
                            if(categorieToDelete.getId_activities_types() == categories.getId_activities_types()){
                                categoriesToSendList.remove(i);
                                break;
                            }
                        }
                    } else {
                        subcategoriesToSendList = new ArrayList<Subcategories>();
                        Categories categoriesToSend = new Categories();
                        categoriesToSend.setId_activities_types(categories.getId_activities_types());
                        categoriesToSend.setDescription(categories.getDescription());
                        categoriesToSend.setSelected(true);
                        categoriesToSendList.add(categoriesToSend);
                        categories.setSelected(true);
                        showPublicTypeChange(true, tviCategory);
                        if (sublinearLayoutAdded) {
                            subLinearLayout.removeAllViews();
                            llaCategories.removeViewAt(positionCategory + 1);
                        }
                        if (subcategoriesList.size() > 0) {
                            Subcategories subcategoriesToSend = new Subcategories();
                            subcategoriesToSend.setId_activities_subtypes(subcategoriesList.get(0).getId_activities_subtypes());
                            subcategoriesToSend.setSelected(true);
                            subcategoriesToSend.setDescription(subcategoriesList.get(0).getDescription());
                            subcategoriesToSendList.add(subcategoriesToSend);
                            categoriesToSendList.get(categoriesToSendList.size() - 1).setSubcategoriesList(subcategoriesToSendList);
                            initSublinearLayout();
                            subLinearLayout.setBackgroundResource(R.drawable.bg_sub_categoria_filtro);
                            initSubcontentLinearLayout();
                            for (int j = 0; j < subcategoriesList.size(); j++) {
                                final Subcategories subcategories = subcategoriesList.get(j);
                                if (j > 0 && j % 4 == 0) {
                                    subLinearLayout.addView(subcontentLinearLayout);
                                    initSubcontentLinearLayout();
                                }
                                final CustomTextView tviSubcategory = new CustomTextView(FilterOptionActivity.this);
                                tviSubcategory.setText(subcategories.getDescription());
                                if(j == 0){
                                    showSubcategoryChange(true, tviSubcategory);
                                } else {
                                    showSubcategoryChange(false, tviSubcategory);
                                }
                                tviSubcategory.setGravity(Gravity.CENTER);
                                tviSubcategory.setPadding(
                                        padding_textview_subcategory_left_right,
                                        padding_textview_subcategory_top_bottom,
                                        padding_textview_subcategory_left_right,
                                        padding_textview_subcategory_top_bottom);
                                tviSubcategory.setTextSize(16);
                                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                layoutParams.setMargins(
                                        getResources().getDimensionPixelSize(R.dimen.margin_textview_subcategory_left),
                                        getResources().getDimensionPixelSize(R.dimen.margin_textview_subcategory_top),
                                        getResources().getDimensionPixelSize(R.dimen.margin_textview_subcategory_right),
                                        getResources().getDimensionPixelSize(R.dimen.margin_textview_subcategory_bottom));
                                tviSubcategory.setLayoutParams(layoutParams);
                                subcontentLinearLayout.addView(tviSubcategory);
                                if (j == subcategoriesList.size() - 1) {
                                    subLinearLayout.addView(subcontentLinearLayout);
                                }
                                tviSubcategory.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        subcategoriesToSendList = categoriesToSendList.get(categoriesToSendList.size() - 1).getSubcategoriesList();
                                        if(subcategories.isSelected()){
                                            if(!subcategories.getDescription().toLowerCase().equals("todos")){
                                                showSubcategoryChange(false, tviSubcategory);
                                                subcategories.setSelected(false);
                                                for(int i = 1; i<subcategoriesToSendList.size(); i++){
                                                    Subcategories subcategoriesToDelete = subcategoriesToSendList.get(i);
                                                    if(subcategoriesToDelete.getId_activities_subtypes() == subcategories.getId_activities_subtypes()){
                                                        subcategoriesToSendList.remove(i);
                                                        break;
                                                    }
                                                }
                                            }
                                        } else{
                                            if(subcategories.getDescription().toLowerCase().equals("todos")){
                                                for(Subcategories subcategoriesToUnselect : subcategoriesList){
                                                    if(!subcategoriesToUnselect.getDescription().toLowerCase().equals("todos")){
                                                        subcategoriesToUnselect.setSelected(false);
                                                    }
                                                }
                                                for(int i = 0; i<subLinearLayout.getChildCount(); i++){
                                                    LinearLayout llaSubContent = (LinearLayout) subLinearLayout.getChildAt(i);
                                                    for(int j = 0; j<llaSubContent.getChildCount(); j++){
                                                        TextView tviSubcontent = (TextView) llaSubContent.getChildAt(j);
                                                        if(i == 0 && j > 0){
                                                            showSubcategoryChange(false, tviSubcontent);
                                                        } else{
                                                            showSubcategoryChange(false, tviSubcontent);
                                                        }
                                                    }
                                                }
                                                subcategoriesToSendList = new ArrayList<Subcategories>();
                                                Subcategories subcategoriesToSend = new Subcategories();
                                                subcategoriesToSend.setId_activities_subtypes(subcategories.getId_activities_subtypes());
                                                subcategoriesToSend.setDescription(subcategories.getDescription());
                                                subcategoriesToSend.setSelected(true);
                                                subcategoriesToSendList.add(subcategoriesToSend);
                                                categoriesToSendList.get(categoriesToSendList.size() - 1).setSubcategoriesList(subcategoriesToSendList);
                                                subcategories.setSelected(true);
                                                showSubcategoryChange(true, tviSubcategory);
                                            } else{
                                                for(Subcategories subcategoriesToUnselect : subcategoriesList){
                                                    if(subcategoriesToUnselect.getDescription().toLowerCase().equals("todos")){
                                                        subcategoriesToUnselect.setSelected(false);
                                                        break;
                                                    }
                                                }
                                                for(int i = 0; i<subLinearLayout.getChildCount(); i++){
                                                    LinearLayout llaSubContent = (LinearLayout) subLinearLayout.getChildAt(i);
                                                    for(int j = 0; j<llaSubContent.getChildCount(); j++){
                                                        TextView tviSubcontent = (TextView) llaSubContent.getChildAt(j);
                                                        if(i == 0 && j == 0){
                                                            showSubcategoryChange(false, tviSubcontent);
                                                        }
                                                    }
                                                }
                                                if(subcategoriesToSendList.get(0).getDescription().toLowerCase().equals("todos"))
                                                    subcategoriesToSendList.remove(0);
                                                Subcategories subcategoriesToSend = new Subcategories();
                                                subcategoriesToSend.setId_activities_subtypes(subcategories.getId_activities_subtypes());
                                                subcategoriesToSend.setDescription(subcategories.getDescription());
                                                subcategoriesToSend.setSelected(true);
                                                subcategoriesToSendList.add(subcategoriesToSend);
                                                showSubcategoryChange(true, tviSubcategory);
                                                subcategories.setSelected(true);
                                            }
                                        }
                                    }
                                });
                            }
                            positionCategory = (int) ((finalI) / 3);
                            llaCategories.addView(subLinearLayout, (positionCategory + 1));
                            sublinearLayoutAdded = true;
                        } else{
                            sublinearLayoutAdded = false;
                        }
                    }
                }
            });
        }
    }

    private void initLinearLayout(){
        linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER);
    }

    private void initSublinearLayout(){
        subLinearLayout = new LinearLayout(this);
        subLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        subLinearLayout.setOrientation(LinearLayout.VERTICAL);
        subLinearLayout.setGravity(Gravity.CENTER);
        subLinearLayout.setPadding(
                getResources().getDimensionPixelSize(R.dimen.padding_sublinearlayout_left),
                getResources().getDimensionPixelSize(R.dimen.padding_sublinearlayout_top),
                getResources().getDimensionPixelSize(R.dimen.padding_sublinearlayout_right),
                getResources().getDimensionPixelSize(R.dimen.padding_sublinearlayout_bottom));
    }

    private void initSubcontentLinearLayout(){
        subcontentLinearLayout = new LinearLayout(this);
        subcontentLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        subcontentLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        subcontentLinearLayout.setGravity(Gravity.CENTER);
    }

    @OnClick(R.id.tviGeneral)
    public void generalPublic(){
        selectOptionPublicType(true, false, false, false);
    }

    @OnClick(R.id.tviAdultos)
    public void adultsPublic(){
        selectOptionPublicType(false, true, false, false);
    }

    @OnClick(R.id.tviNinos)
    public void childrenPublic(){
        selectOptionPublicType(false, false, true, false);
    }

    @OnClick(R.id.tviTerceraEdad)
    public void oldPublic(){
        selectOptionPublicType(false, false, false, true);
    }

    @OnClick(R.id.btn_comenzar)
    public void beginApp(){
        if(categoriesToSendList.size() < 2){
            dialog = dialogUtil.createCustomDialog("LetsGo", getString(R.string.message_nedd_more_categories), this, 0, true, false);
            dialog.show();
        } else{
            int public_type = 0;
            if(statusGeneral) public_type = 1;
            else if(statusAdultos) public_type = 2;
            else if(statusNinos) public_type = 3;
            else if(statusTerceraEdad) public_type = 4;
            CategoriesToPreferences categoriesToPreferences = new CategoriesToPreferences();
            categoriesToPreferences.setPublicType(public_type);
            categoriesToPreferences.setCategoriesList(categoriesToSendList);
            categoriesPresenter.saveCategoriesToPreferences(categoriesToPreferences);
        }
    }

    private void selectOptionPublicType(boolean statusGeneral, boolean statusAdultos, boolean statusNinos, boolean statusTerceraEdad){
        this.statusGeneral = statusGeneral;
        this.statusAdultos = statusAdultos;
        this.statusNinos = statusNinos;
        this.statusTerceraEdad = statusTerceraEdad;
        showPublicTypeChange(statusGeneral, tviGeneral);
        showPublicTypeChange(statusAdultos, tviAdultos);
        showPublicTypeChange(statusNinos, tviNinos);
        showPublicTypeChange(statusTerceraEdad, tviTerceraEdad);
    }

    public boolean showPublicTypeChange(boolean status, TextView tviCategory){
        boolean newStatus;
        if (status == true) {
            newStatus = false;
            tviCategory.setTextColor(getResources().getColor(R.color.white));
            tviCategory.setBackgroundResource(R.drawable.type_public_on);
        } else{
            newStatus = true;
            tviCategory.setTextColor(getResources().getColor(R.color.secondary_text));
            tviCategory.setBackgroundResource(R.drawable.type_public_off);
        }
        return newStatus;
    }

    public boolean showSubcategoryChange(boolean status, TextView tviSubcategory){
        boolean newStatus;
        if (status == false) {
            newStatus = true;
            tviSubcategory.setTextColor(getResources().getColor(R.color.white));
            tviSubcategory.setBackgroundResource(R.drawable.type_sub_category_off);
        } else{
            newStatus = false;
            tviSubcategory.setTextColor(getResources().getColor(R.color.color_text_on));
            tviSubcategory.setBackgroundResource(R.drawable.type_public_off);
        }
        return newStatus;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iviBack:
                onBackPressed();
                break;
        }
    }
}
