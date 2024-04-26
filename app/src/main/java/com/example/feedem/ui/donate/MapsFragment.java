package com.example.feedem.ui.donate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.feedem.ModelClasses.Donation_Data;
import com.example.feedem.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback {
    GoogleMap Mmap;
    Marker marker;
    Donation_Data adddata;
    static  Boolean destroyindicater = false;

    double Longitude = 0 , Latitude = 0 ;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        setMarker(location.getLatitude() , location.getLongitude());
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {
        Mmap = map;
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setOnMyLocationButtonClickListener(this);
        map.setOnMyLocationClickListener(this);

        }


    private void setMarker(double latitude, double longitude) {
        Mmap.clear();
        marker = Mmap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .draggable(true)
                .icon(BitmapDescriptorFactory.defaultMarker()));
        destroyindicater = true;
    }

    @Override
    public void onDestroy()
    {
        if (destroyindicater == true ) {
            Log.d("latitude", String.valueOf(Latitude));
            Log.d("Longitude ", String.valueOf(Longitude));
            Latitude = marker.getPosition().latitude;
            Longitude = marker.getPosition().longitude;
            adddata.setLatitude(Latitude);
            adddata.setLongitude(Longitude);
            Log.d("latitude", String.valueOf(Latitude));
            Log.d("Longitude ", String.valueOf(Longitude));
            destroyindicater = false;
        }
        super.onDestroy();
    }

}