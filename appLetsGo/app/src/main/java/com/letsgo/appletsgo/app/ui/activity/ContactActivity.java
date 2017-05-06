package com.letsgo.appletsgo.app.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.app.ui.core.BaseAppCompat;
import com.letsgo.appletsgo.app.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactActivity extends BaseAppCompat {
    @BindView(R.id.iviBack)
    ImageView iviBack;

    @BindView(R.id.rlaWhatsApp)
    RelativeLayout rlaWhatsApp;
    @BindView(R.id.rlaCall)
    RelativeLayout rlaCall;
    @BindView(R.id.rlaSendEmail)
    RelativeLayout rlaSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.iviBack)
    public void iviBack() {
        onBackPressed();
    }

    @OnClick(R.id.rlaWhatsApp)
    public void sharedWhatsApp() {
        try {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hola, que tal.");
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } catch (Exception e) {
            snackBarError("No se encuentra la aplicacion", rlaWhatsApp);
            LogUtils.v("Error", " no tiene whatsapps");
        }

    }

    @OnClick(R.id.rlaCall)
    public void call() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "999 999 999"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            LogUtils.v("Error", " no tiene permiso");
            if (ActivityCompat.shouldShowRequestPermissionRationale(ContactActivity.this,
                    Manifest.permission.CALL_PHONE)) {
                ActivityCompat.requestPermissions(ContactActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 10);
            }
            return;
        }
        startActivity(intent);
    }
    @OnClick(R.id.rlaSendEmail)
    public void sendEmail(){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","contacto@letfo.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hola equipo Lets Go");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        startActivity(Intent.createChooser(emailIntent, "Enviar correo electrónico"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + "999 999 999"));
                    if (ActivityCompat.checkSelfPermission(ContactActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        startActivity(callIntent);
                    }
                } else {
                    snackBarError("Usted Rechazo el permiso", rlaCall);
                    return;
                }
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
