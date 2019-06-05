package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclingAndSecurityActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent goToHome = new Intent(RecyclingAndSecurityActivity.this, MainActivity.class);
                    startActivity(goToHome);
                    return true;
                case R.id.navigation_mission:
                    return true;
                case R.id.navigation_carte:
                    Intent goToMaps = new Intent(RecyclingAndSecurityActivity.this, MapsActivity.class);
                    startActivity(goToMaps);
                    return true;
                case R.id.navigation_info:
                    Intent goToInfo = new Intent(RecyclingAndSecurityActivity.this, RecyclingAndSecurityActivity.class);
                    startActivity(goToInfo);
                    return true;
                case R.id.navigation_profile:
                    Intent goToProfile = new Intent(RecyclingAndSecurityActivity.this, UserProfileActivity.class);
                    startActivity(goToProfile);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling_and_security);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        RecyclingInfoItem mobiusItem = new RecyclingInfoItem(R.drawable.logo_mobius, String.format(getString(R.string.mobius_title)), String.format("%s%s", getString(R.string.mobius_description_paragraph1), getString(R.string.mobius_description_paragraph2)));
        RecyclingInfoItem triMan = new RecyclingInfoItem(R.drawable.logo_triman, getString(R.string.triman_title), getString(R.string.triman_description));
        RecyclingInfoItem poubelleBarree = new RecyclingInfoItem(R.drawable.logo_poubelle_barree, getString(R.string.poubelle_barree_title), String.format("%s%s", getString(R.string.poubelle_barree_description), getString(R.string.poubelle_barree_description2)));
        RecyclingInfoItem tidyMan = new RecyclingInfoItem(R.drawable.logo_tidyman, getString(R.string.tidyman_title), getString(R.string.tidyman_description));
        RecyclingInfoItem pointVert = new RecyclingInfoItem(R.drawable.logo_point_vert, getString(R.string.point_vert_title), getString(R.string.point_vert_description));
        List<RecyclingInfoItem> recyclingInfoItems = new ArrayList<>();
        recyclingInfoItems.add(mobiusItem);
        recyclingInfoItems.add(triMan);
        recyclingInfoItems.add(poubelleBarree);
        recyclingInfoItems.add(tidyMan);
        recyclingInfoItems.add(pointVert);

        RecyclerView mRecyclerView;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager layoutManager;
        mRecyclerView = findViewById(R.id.rvRecyclingInfo);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclingInfoAdapter(recyclingInfoItems);
        mRecyclerView.setAdapter(mAdapter);
    }
}
