package fr.wildcodeschool.robinsdesmers;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Consumer;
import android.view.MenuItem;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import fr.wildcodeschool.robinsdesmers.information.InformationActivity;
import fr.wildcodeschool.robinsdesmers.model.CollectPointItem;
import fr.wildcodeschool.robinsdesmers.model.RubbishItem;
import fr.wildcodeschool.robinsdesmers.rubbish_collect_point.CollectPointDescriptionActivity;
import fr.wildcodeschool.robinsdesmers.rubbish_collect_point.CollectRubbishActivity;
import fr.wildcodeschool.robinsdesmers.rubbish_collect_point.MarkerTypeActivity;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_LOCATION = 4322;
    private static final float DEFAULT_ZOOM = 16.0f;
    private static final int MIN_DISTANCE = 2;
    private UserSingleton userSingleton = UserSingleton.getUserInstance();
    private GoogleMap mMap;
    private boolean mMapInit = false;
    private LocationManager mLocationManager = null;
    private Location mLocationUser = null;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent goToHome = new Intent(MapsActivity.this, MainActivity.class);
                    startActivity(goToHome);
                    return true;
                case R.id.navigation_mission:
                    return true;
                case R.id.navigation_carte:
                    Intent goToMaps = new Intent(MapsActivity.this, MapsActivity.class);
                    startActivity(goToMaps);
                    return true;
                case R.id.navigation_info:
                    Intent goToInfo = new Intent(MapsActivity.this, InformationActivity.class);
                    startActivity(goToInfo);
                    return true;
                case R.id.navigation_profile:
                    Intent goToProfile = new Intent(MapsActivity.this, UserProfileActivity.class);
                    startActivity(goToProfile);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        checkPermission();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(MapsActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            }
        } else {
            initLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initLocation();
                } else {

                }
                return;
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void initLocation() {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                setUserLocation(location);
                userSingleton.getUser().setLatitude(location.getLatitude());
                userSingleton.getUser().setLongitude(location.getLongitude());
                userSingleton.getUser().setConnected(true);
            }
        });
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                setUserLocation(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, MIN_DISTANCE, locationListener);
    }

    @SuppressLint("MissingPermission")
    private void setUserLocation(Location location) {
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            LatLng coordinate = new LatLng(lat, lng);
            mLocationUser = new Location("");
            mLocationUser.setLatitude(lat);
            mLocationUser.setLongitude(lng);

            if (mMap != null && !mMapInit) {
                mMapInit = true;
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, DEFAULT_ZOOM));
                mMap.setMyLocationEnabled(true);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        initLocation();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
        } else {
            mMap.setMyLocationEnabled(true);
            setUserLocation(mLocationUser);
        }
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                MarkerOptions markerOptions = new MarkerOptions();

                markerOptions.position(latLng);
                markerOptions.title(getString(R.string.dechet));
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.addMarker(markerOptions);

                final RubbishItem rubbishItem = new RubbishItem("", "", 0, false, false, latLng.latitude, latLng.longitude);
                final CollectPointItem collectPointItem = new CollectPointItem("", "", 1, latLng.latitude, latLng.longitude);

                Intent intent = new Intent(MapsActivity.this, MarkerTypeActivity.class);
                intent.putExtra("RubbishItem", rubbishItem);
                intent.putExtra("CollectPointItem", collectPointItem);
                startActivity(intent);
            }
        });

        VolleySingleton.getInstance(MapsActivity.this).getAllRubbish(new Consumer<List<RubbishItem>>() {
            @Override
            public void accept(List<RubbishItem> rubbishItems) {
                for (RubbishItem rubbish : rubbishItems) {
                    final LatLng rubbishCoord = new LatLng(rubbish.getLatitude(), rubbish.getLongitude());
                    if (!rubbish.isCollected()) {
                        Marker marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.dechet)).position(rubbishCoord).title(rubbish.getTitle()).snippet(rubbish.getDescription()));
                        marker.setTag(rubbish);
                    }
                }
            }
        });

        VolleySingleton.getInstance(MapsActivity.this).getAllCollectPoint(new Consumer<List<CollectPointItem>>() {
            @Override
            public void accept(List<CollectPointItem> collectPointItems) {
                for (CollectPointItem collectPoint : collectPointItems) {
                    final LatLng collectPointCoord = new LatLng(collectPoint.getLatitude(), collectPoint.getLongitude());
                    Marker markerCollect = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.pointcollecte)).position(collectPointCoord).title(collectPoint.getTitle()).snippet(collectPoint.getDescription()).alpha(0.99f));
                    markerCollect.setTag(collectPoint);
                }
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.getAlpha() == 0.99f) {
                    CollectPointItem collectPointItem = (CollectPointItem) marker.getTag();
                    Intent intent1 = new Intent(MapsActivity.this, CollectPointDescriptionActivity.class);
                    intent1.putExtra("collectPointId", collectPointItem.getId());
                    startActivity(intent1);
                } else {
                    RubbishItem rubbishItem = (RubbishItem) marker.getTag();
                    Intent intent = new Intent(MapsActivity.this, CollectRubbishActivity.class);
                    intent.putExtra("rubbishId", rubbishItem.getId());
                    startActivity(intent);
                }
            }
        });
    }
}
