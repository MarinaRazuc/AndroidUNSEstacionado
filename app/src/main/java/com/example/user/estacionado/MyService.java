package com.example.user.estacionado;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;


public class MyService extends Service {

    private final IBinder mBinder = new LocalBinder();
    private LocationManager locationManager;
    private String locationProvider;
    private LatLng latlong;


    public class LocalBinder extends Binder {
        MyService getService() {
            // Return this instance of LocalService so clients can call public
            // methods
            return MyService.this;
        }
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Log.d("prueba", "onCreate(), MyService.java, Servicio creado...");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Log.d("prueba", "onStartCommand(), MyService.java, Servicio iniciado...");
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                // makeUseOfNewLocation(location);
                latlong=new LatLng(location.getLatitude(),location.getLongitude());
                Log.d("prueba", "onLocationChanged: "+latlong.toString());
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {
              //
               Log.i("prueba", "onStatusChanged: "+status);
            }

            public void onProviderEnabled(String provider) {
                Log.i("MAPA", "onProviderEnabled: ");
            }

            public void onProviderDisabled(String provider) {
                Log.i("MAPA", "onProviderDisabled: ");
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        locationProvider = LocationManager.GPS_PROVIDER;
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0.1f, locationListener);
        locationManager.requestSingleUpdate(locationProvider,locationListener,null);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("prueba", "onDestroy(), MyService.java, Servicio destruido...");
    }

    public LatLng mostrar(){
        if (latlong  != null){
            Log.d("prueba", "mostrar(), MyService.java " + latlong.toString()+" ");
            return latlong;
        }
        else{
            Log.d("prueba","mostrar(), MyService.java, NULO");
            return new LatLng(0,0);
        }
    }
}
