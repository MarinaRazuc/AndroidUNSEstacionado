package com.example.user.estacionado;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.LocationManager;
import android.os.Bundle;
import android.content.SharedPreferences;


import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

public class iniciar extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
   // public static final String BORRADOR = "BORRADOR" ;

    public static final String ubicacionLatitud = "latKEY";
    public static final String ubicacionLongitud = "longKEY";
    SharedPreferences sharedpreferences;
    //SharedPreferences sharedpreferences2;

    LatLng posicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Prueba", "onCreate, iniciar.java");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_layout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
    }
    private void verificacion () {
        //verificar si el gps esta activado
        //verificar si existe posicion
        //prueba

        Log.i("prueba", "verificacion, iniciar.java");
        double latitud;
        double longitud;
        latitud = Double.parseDouble(sharedpreferences.getString(ubicacionLatitud,"0"));
        longitud = Double.parseDouble(sharedpreferences.getString(ubicacionLongitud, "0"));

        Log.d("prueba", "valor shared latitud  (iniciar.java)" + latitud);
        Log.d("prueba", "valor shared longitud (iniciar.java) " + longitud);
        if (((latitud == 0 && longitud==0) )) //|| borrar== 1)) //no existe una ubicacion previa
        {
            Log.i("prueba", "verificacion,NO EXISTE una ubicacion IF, iniciar.java");
            Intent i;
            i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
        else //existe ubicacion
        {
            Log.i("prueba", "verificacion, EXISTE una ubicacion ELSE, iniciar.java");
            Intent i;
            i = new Intent (this,Main2Activity.class);
            i.putExtra("latitud",latitud);
            i.putExtra("longitud",longitud);
            Log.d("prueba", "INICIAR inicia A2 con latitud (iniciar.java)" + latitud);
            Log.d("prueba", "INICIAR inicia A2 con longitud (iniciar.java)" + longitud);
            startActivity(i);

        }
 }

    @Override
    protected void onRestart() {
        Log.d("prueba", "onRestart, iniciar.java");
        verificacion();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.d("prueba", "onResume, iniciar.java");
        verificacion();
        super.onResume();
    }

}
