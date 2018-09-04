package com.techtify.qualimoments.frag;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.techtify.qualimoments.R;
import com.techtify.qualimoments.adapters.MapMenuAdapter;
import com.techtify.qualimoments.model.MapMenuItem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFrag extends BaseFrag {


    private static final int PERMISSION_REQUEST = 100;
    private static final String TAG = "HomeFrag";
    private GoogleMap googleMap;

    public HomeFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      /*  view.findViewById(R.id.map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), MapsActivity.class));
            }
        });*/

        GridView mapMenu = view.findViewById(R.id.mapMenu);
        ArrayList<MapMenuItem> menu = new ArrayList<>();

        menu.add(new MapMenuItem(R.drawable.ic_restourants,"Restaurant",0));
        menu.add(new MapMenuItem(R.drawable.ic_cafees,"Cafee shop",1));
        menu.add(new MapMenuItem(R.drawable.ic_hotels,"Hotels",2));
        menu.add(new MapMenuItem(R.drawable.ic_car_rentals,"Cars",3));
        menu.add(new MapMenuItem(R.drawable.ic_tourist_places,"Tourism",4));
        menu.add(new MapMenuItem(R.drawable.ic_other_places,"Other",5));

        MapMenuAdapter mapMenuAdapter = new MapMenuAdapter(getActivity(),menu);
        mapMenu.setAdapter(mapMenuAdapter);
        SlidingUpPanelLayout sliding_layout = view.findViewById(R.id.sliding_layout);
        //sliding_layout.setOverlayed(true);
        MapView mMapView = view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST);
                    //return;
                } else {
                    googleMap.setMyLocationEnabled(true);
                    findMe();
                }
                try {
                    // Customise the styling of the base map using a JSON object defined
                    // in a raw resource file.
                    boolean success = googleMap.setMapStyle(
                            MapStyleOptions.loadRawResourceStyle(
                                    getActivity(), R.raw.map_style));

                    if (!success) {
                        Log.e(TAG, "Style parsing failed.");
                    }
                } catch (Resources.NotFoundException e) {
                    Log.e(TAG, "Can't find style. Error: ", e);
                }

            }
        });

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                googleMap.setMyLocationEnabled(true);

            } else {

                //TODO permission not given
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void findMe() {
        googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

            @Override
            public void onMyLocationChange(Location arg0) {
                // TODO Auto-generated method stub

                CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(arg0.getLatitude(), arg0.getLongitude())).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                //googleMap.addMarker(new MarkerOptions().position().title("It's Me!"));
            }
        });
    }
}
