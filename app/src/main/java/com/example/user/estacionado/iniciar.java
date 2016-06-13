package com.example.user.estacionado;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

public class iniciar extends AppCompatActivity {
    LatLng posicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iniciar_layout);
        //verificar si el gps esta activado
        //verificar si existe posicion
        Intent i;
        //prueba
        posicion = new LatLng(-38.716667, -62.266667);
        if (posicion != null)
        {
            i = new Intent (this,Main2Activity.class);
        }
        else
        {
            i = new Intent(this,MainActivity.class);
        }
        startActivity(i);


    }

}
