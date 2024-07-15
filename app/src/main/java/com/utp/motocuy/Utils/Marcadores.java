package com.utp.motocuy.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.utp.motocuy.R;

public class Marcadores {
    GoogleMap nMap;
    Context context;

    public Marcadores(GoogleMap nMap, Context context) {
        this.nMap = nMap;
        this.context = context;
    }
    public void addMarkersDefault(){
        uno(-11.983581,-77.011833, "uno");
        dos(-11.973545,-77.0056679, "dos");

    }

    public void uno(Double latitud, Double longitud, String titulo){
        LatLng punto = new LatLng(latitud, longitud);
        int heigth = 140;
        int width = 165;
        BitmapDrawable uno = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cuy_png);
        Bitmap unos = uno.getBitmap();
        Bitmap uns = Bitmap.createScaledBitmap(unos,width,heigth,false);
        nMap.addMarker(new MarkerOptions()
                .position(punto)
                .title(titulo).snippet("uno")
                .icon(BitmapDescriptorFactory.fromBitmap(uns))
        );

    }
    public void dos(Double latitud, Double longitud, String titulo){
        LatLng punto = new LatLng(latitud, longitud);
        int heigth = 140;
        int width = 165;
        BitmapDrawable uno = (BitmapDrawable) context.getResources().getDrawable(R.drawable.cuy_png);
        Bitmap unos = uno.getBitmap();
        Bitmap uns = Bitmap.createScaledBitmap(unos,width,heigth,false);
        nMap.addMarker(new MarkerOptions()
                .position(punto)
                .title(titulo).snippet("uno")
                .icon(BitmapDescriptorFactory.fromBitmap(uns))
        );

    }

}
