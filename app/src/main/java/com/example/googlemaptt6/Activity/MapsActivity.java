package com.example.googlemaptt6.Activity;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.googlemaptt6.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    float longtitude,lattitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
//        lấy dữ liệu từ intent
        Intent intent = getIntent();
        longtitude = intent.getFloatExtra("longtitude",21);
        lattitude = intent.getFloatExtra("lattitude",105);
//      gán dữ liệu vào map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng place = new LatLng(longtitude, lattitude);
        mMap.addMarker(new MarkerOptions().position(place).title("Vị trí khách hàng"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
    }
}