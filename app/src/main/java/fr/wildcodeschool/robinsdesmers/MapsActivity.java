package fr.wildcodeschool.robinsdesmers;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
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
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.widget.Toast;

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
import fr.wildcodeschool.robinsdesmers.model.User;
import fr.wildcodeschool.robinsdesmers.rubbish_collect_point.MarkerTypeActivity;

import static fr.wildcodeschool.robinsdesmers.inscription.AvatarChoicesActivity.avatarHeadList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_LOCATION = 4322;
    private static final float DEFAULT_ZOOM = 19.0f;
    private static final int MIN_DISTANCE = 2;
    private UserSingleton userSingleton = UserSingleton.getUserInstance();
    private GoogleMap mMap;
    private boolean mMapInit = false;
    private LocationManager mLocationManager = null;
    private FusedLocationProviderClient mFusedLocationClient;
    private Location mLocationUser = null;
    private Marker markerHead;
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                    builder.setTitle(R.string.page_en_dev);
                    builder.setMessage(R.string.mission_mensuelles_alert);
                    builder.setPositiveButton(R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
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
                    if (userSingleton.getUser().getAvatar() == null) {
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(MapsActivity.this);
                        builder2.setTitle(R.string.merci_de);
                        builder2.setMessage(R.string.acces_visiteur_profile);
                        builder2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MapsActivity.this, FirstPageActivity.class);
                                startActivity(intent);
                            }
                        });
                        AlertDialog dialog2 = builder2.create();
                        dialog2.show();
                    } else {
                        Intent goToProfile = new Intent(MapsActivity.this, UserProfileActivity.class);
                        startActivity(goToProfile);
                    }
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
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
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

    private void initLocation() {
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        setUserLocation(location);
                    }
                }
            });
            if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, MIN_DISTANCE, locationListener);
            }
            if (mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, MIN_DISTANCE, locationListener);
            }
        }
    }

    private void setUserLocation(Location location) {
        if (location != null) {
            if (userSingleton.getUser().getAvatar() != null && mMap != null) {
                /*userSingleton.getUser().setLatitude(location.getLatitude());
                userSingleton.getUser().setLongitude(location.getLongitude());
                userSingleton.getUser().setConnected(true);*/

                Integer tete = avatarHeadList.get(userSingleton.getUser().getAvatar());

                MarkerOptions markerOptions = new MarkerOptions();
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                markerOptions.position(latLng);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(tete));
                if (markerHead != null) {
                    markerHead.remove();
                }
                markerHead = mMap.addMarker(markerOptions);
            }
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            LatLng coordinate = new LatLng(lat, lng);
            mLocationUser = new Location("");
            mLocationUser.setLatitude(lat);
            mLocationUser.setLongitude(lng);

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                if (mMap != null && !mMapInit) {
                    mMapInit = true;
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinate, DEFAULT_ZOOM));
                    mMap.setMyLocationEnabled(true);
                }
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

                if (userSingleton.getUser().getAvatar() != null) {
                    MarkerOptions markerOptions = new MarkerOptions();

                    markerOptions.position(latLng);
                    markerOptions.title(getString(R.string.dechet));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.addMarker(markerOptions);

                    final RubbishItem rubbishItem = new RubbishItem("", "", 0, false, false, latLng.latitude, latLng.longitude);
                    final CollectPointItem collectPointItem = new CollectPointItem("", "", 1, latLng.latitude, latLng.longitude);

                    Intent intent = new Intent(MapsActivity.this, MarkerTypeActivity.class);
                    intent.putExtra("RubbishItem", rubbishItem);
                    intent.putExtra("CollectPointItem", collectPointItem);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MapsActivity.this);
                    builder2.setTitle(R.string.merci_de);
                    builder2.setMessage(R.string.declare_dechets_point);
                    builder2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MapsActivity.this, FirstPageActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog2 = builder2.create();
                    dialog2.show();
                }
            }
        });


        VolleySingleton.getInstance(MapsActivity.this).getAllRubbish(new Consumer<List<RubbishItem>>() {
            @Override
            public void accept(List<RubbishItem> rubbishItems) {
                for (RubbishItem rubbish : rubbishItems) {
                    final LatLng rubbishCoord = new LatLng(rubbish.getLatitude(), rubbish.getLongitude());
                    if (!rubbish.isCollected()) {
                        if (rubbish.getTitle().equals(getString(R.string.dechet_seul))) {
                            Marker marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_dechet_seul_light_green)).position(rubbishCoord).title(rubbish.getTitle()).snippet(rubbish.getDescription()));
                            marker.setTag(rubbish);
                        } else {
                            Marker marker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_amas_dechet_light_green)).position(rubbishCoord).title(rubbish.getTitle()).snippet(rubbish.getDescription()));
                            marker.setTag(rubbish);
                        }
                    }
                }
            }
        });

        VolleySingleton.getInstance(MapsActivity.this).getAllCollectPoint(new Consumer<List<CollectPointItem>>() {
            @Override
            public void accept(List<CollectPointItem> collectPointItems) {
                for (CollectPointItem collectPoint : collectPointItems) {
                    final LatLng collectPointCoord = new LatLng(collectPoint.getLatitude(), collectPoint.getLongitude());
                    Marker markerCollect = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_collect_point_light_green)).position(collectPointCoord).title(collectPoint.getTitle()).snippet(collectPoint.getDescription()).alpha(0.99f));
                    markerCollect.setTag(collectPoint);
                }
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(final Marker marker) {
                if (userSingleton.getUser().getAvatar() != null) {
                    if (marker.getAlpha() == 0.99f) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                        builder.setMessage(R.string.collectPoint_present);
                        builder.setPositiveButton(R.string.oui, null);
                        builder.setNegativeButton(R.string.non, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CollectPointItem collectPointItem = (CollectPointItem) marker.getTag();
                                VolleySingleton.getInstance(MapsActivity.this).deleteOneCollectPoint(collectPointItem.getId(), new Consumer<CollectPointItem>() {
                                    @Override
                                    public void accept(CollectPointItem collectPointItem) {
                                    }
                                });
                                startActivity(new Intent(MapsActivity.this, MapsActivity.class));
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity.this);
                        builder.setTitle(R.string.dechet_collecte);
                        builder.setPositiveButton(getString(R.string.oui), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                RubbishItem rubbishItem = (RubbishItem) marker.getTag();
                                userSingleton.getUser().setScore(userSingleton.getUser().getScore() + (rubbishItem.getSumRubbish() * 10));
                                VolleySingleton.getInstance(MapsActivity.this).updateUser(userSingleton.getUser().getId(), userSingleton.getUser(), new Consumer<User>() {
                                    @Override
                                    public void accept(User user) {
                                        Toast.makeText(MapsActivity.this, getString(R.string.merci_ramasser), Toast.LENGTH_LONG).show();
                                    }
                                });
                                VolleySingleton.getInstance(MapsActivity.this).collectRubbish(rubbishItem.getId(), new Consumer<RubbishItem>() {
                                    @Override
                                    public void accept(RubbishItem rubbishItem) {
                                        startActivity(new Intent(MapsActivity.this, MapsActivity.class));
                                    }
                                });
                            }
                        });
                        builder.setNegativeButton(getString(R.string.non), null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                } else {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MapsActivity.this);
                    builder2.setTitle(R.string.merci_de);
                    builder2.setMessage(R.string.recolter_dechets_visiteurs);
                    builder2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MapsActivity.this, FirstPageActivity.class);
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog2 = builder2.create();
                    dialog2.show();
                }
            }
        });
    }
}
