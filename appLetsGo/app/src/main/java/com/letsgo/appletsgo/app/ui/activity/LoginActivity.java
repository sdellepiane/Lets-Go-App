package com.letsgo.appletsgo.app.ui.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.LogUtils;
import com.letsgo.appletsgo.data.entity.raw.LoginRaw;
import com.letsgo.appletsgo.data.store.SessionUser;
import com.letsgo.appletsgo.domain.model.entity.CompleteUser;
import com.letsgo.appletsgo.domain.model.entity.Distrito;
import com.letsgo.appletsgo.domain.model.entity.Login;
import com.letsgo.appletsgo.domain.model.entity.User;
import com.letsgo.appletsgo.presenter.LoginPresenter;
import com.letsgo.appletsgo.view.LoginView;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseAppCompat implements LoginView{
    private static final String TAG = "LoginActivity";

    private static final int REQUEST_SIGNUP = 0;
    private static final int REQUEST_LOGIN = 1;

    @BindView(R.id.iviLoginFacebook)
    ImageView iviLoginFacebook;

    CallbackManager callbackManager;

    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter();
        loginPresenter.attachedView(this);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.letsgo.appletsgo",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        initOnclickListenerViews(iviLoginFacebook);

        FacebookSdk.sdkInitialize(getApplicationContext());
        configureFacebook();



        //TODO 2017-03-29 19:30:00
        String input_date="01-08-2017";
        Date dt1 = null;
        Locale locale = new Locale ( "es" , "ES" );
        SimpleDateFormat format1=new SimpleDateFormat("dd-MM-yyyy", locale);

        try {
            dt1=format1.parse(input_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dayOfTheWeek = (String) DateFormat.format("EEEE", dt1);
        String day          = (String) DateFormat.format("dd",   dt1);
        String monthString  = (String) DateFormat.format("MMM",  dt1);
        String monthNumber  = (String) DateFormat.format("MM",   dt1);
        String year         = (String) DateFormat.format("yyyy", dt1);
        LogUtils.v(TAG, " fecha: " + dayOfTheWeek);
        LogUtils.v(TAG, " fecha: " + day);
        LogUtils.v(TAG, " fecha: " + monthString);
        LogUtils.v(TAG, " fecha: " + monthNumber);
        LogUtils.v(TAG, " fecha: " + dayOfTheWeek);
        LogUtils.v(TAG, " fecha: " + year);




    }

    public void configureFacebook() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (AccessToken.getCurrentAccessToken() != null) {
                    LogUtils.v(TAG, "configureFacebook -  onSuccess");
                    LogUtils.v(TAG, "SessionUser" + SessionUser.getSessionUser(getApplicationContext()));
                    verifyExistUser();
                    //
                    // SessionUser.saveSessionUser(this,);
                    //SessionManager.getInstance(getApplicationContext()).setUserFacebook();

                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {
                exception.printStackTrace();
                //Toast.makeText(getApplicationContext(), getResources().getString(R.string.generic_error), Toast.LENGTH_LONG).show();
                //onLoginFailed(getResources().getString(R.string.login_facebook_error));
            }
        });
    }

    public void verificationLogin(String idFacebook, String firtName, String lastName, String email){
        LoginRaw raw = new LoginRaw();
        raw.setId_facebook(idFacebook);
        raw.setFirst_name(firtName);
        raw.setLast_name(lastName);
        raw.setEmail(email);
        loginPresenter.setLogin(raw);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iviLoginFacebook:
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "user_friends", "email", "user_birthday"));

                //nextActivity(FilterFirstActivity.class, false);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK, null);
                finish();
            }
        }
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK, null);
                finish();
                //nextActivity(FilterFirstActivity.class, false);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    String f_email;
    String f_name;
    String f_first_name;
    String f_last_name;
    String f_id;
    String f_gender;
    String f_birthday;
    String idFacebook;
    private void verifyExistUser() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                JSONObject json = response.getJSONObject();
                LogUtils.v(TAG, " Datos Facebbok" + json.toString());
                LogUtils.v(TAG, " object" + object.toString());
                try {
                    if (json.has("email")) {
                        f_email = json.getString("email");
                        f_name = json.has("name") ? json.getString("name") : "";
                        f_first_name = json.has("first_name") ? json.getString("first_name") : "";
                        f_last_name = json.has("last_name") ? json.getString("last_name") : "";
                        f_id = json.has("id") ? json.getString("id") : "";
                        f_gender = json.has("gender") ? json.getString("gender") : "";
                        f_birthday = json.has("birthday") ? json.getString("birthday") : "";
                        LogUtils.v(TAG, " gender" + f_gender);
                        LogUtils.v(TAG, " f_birthday" + f_birthday);
                        /*HashMap<String, String> parametros = new HashMap();
                        parametros.put("email", f_email==null?"":f_email);
                        parametros.put("name", f_name==null?"":f_name);
                        parametros.put("facebookId", f_id==null?"":f_id);
                        parametros.put("gender", f_gender==null?"":f_gender);
                        parametros.put("birthday", f_birthday==null?"":f_birthday);
                        parametros.put("id_sessions", SessionManager.getInstance(LoginActivity.this).getAndroidId());*/

                        idFacebook = json.getString("id");


                        //nextActivity(FilterFirstActivity.class,false);

                        //TODO CONSUMIR WS REGISTRO
                        verificationLogin(idFacebook, f_first_name, f_last_name, f_email);

                    } else {
//                        onLoginFailed("no se pudo recuperar el email de facebook");/
//                          progressDialog.dismiss();
                    }
                } catch (JSONException e) {
                    //MyFunctions.ShowException(e);
                    //onLoginFailed(e.getMessage());
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "gender,id,name,first_name,last_name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void loginFacebook(Login login) {
        LogUtils.v(TAG, " loginFacebook: " + login.toString());
        User user = new User();
        user.setIdFacebook(idFacebook);
        user.setName_complete(f_name);
        user.setFirst_name(f_first_name);
        user.setLast_name(f_last_name);
        user.setName_complete(f_name);
        user.setGender(f_gender);
        user.setEmail(f_email);
        user.setIdUser(login.getId_users());
        SessionUser.saveSessionUser(getApplicationContext(), user);

        if (login.getId_ubigeos() == ""){
            LogUtils.v(TAG, " SESSION USER" +   SessionUser.getSessionUser(getApplicationContext()).toString());
            nextActivity(CompleteUserRegisterActivity.class, false);
        }else{
            nextActivity(HomeActivity.class, false);
        }
    }

    @Override
    public void completeUserRegister(CompleteUser completeUser) {

    }

    @Override
    public void distritosLima(List<Distrito> distritoList) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
