package com.example.user.estacionado;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fragmentos.botones;

public class Main2Activity extends AppCompatActivity implements botones.OnFragmentInteractionListener, OnMapReadyCallback {
    private GoogleMap mMap;
     String locationProvider;
    private GoogleApiClient mGoogleApiClient;

    private Fragment F;
    private MyService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fragmento_actividad2);



        //mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        //bindeo al servicio ya creado por act1
        Intent s = new Intent(this,MyService.class);
        bindService(s,mConnection, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get
            // LocalService instance
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            mService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {

        }
    };

    @Override public void mostrarPosicion() {

        if (mService != null) {
            LatLng latlong = mService.mostrar();
            mMap.addMarker(new MarkerOptions().position(latlong).title("Tu auto"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlong));


            //finalizamos el servicio
            mService.onDestroy();

        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Posición"));
 //       mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
