package fr.wildcodeschool.robinsdesmers.information;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import fr.wildcodeschool.robinsdesmers.FirstPageActivity;
import fr.wildcodeschool.robinsdesmers.MainActivity;
import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserProfileActivity;
import fr.wildcodeschool.robinsdesmers.UserSingleton;
import fr.wildcodeschool.robinsdesmers.adapter.RecyclingInfoAdapter;
import fr.wildcodeschool.robinsdesmers.model.RecyclingInfoItem;

public class RecyclingInfoActivity extends AppCompatActivity {
    UserSingleton userSingleton = UserSingleton.getUserInstance();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent goToHome = new Intent(RecyclingInfoActivity.this, MainActivity.class);
                    startActivity(goToHome);
                    return true;
                case R.id.navigation_mission:
                    return true;
                case R.id.navigation_carte:
                    Intent goToMaps = new Intent(RecyclingInfoActivity.this, MapsActivity.class);
                    startActivity(goToMaps);
                    return true;
                case R.id.navigation_info:
                    Intent goToInfo = new Intent(RecyclingInfoActivity.this, InformationActivity.class);
                    startActivity(goToInfo);
                    return true;
                case R.id.navigation_profile:
                    if (userSingleton.getUser().getAvatar() == null) {
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(RecyclingInfoActivity.this);
                        builder2.setTitle(R.string.merci_de);
                        builder2.setMessage(R.string.acces_visiteur_profile);
                        builder2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(RecyclingInfoActivity.this, FirstPageActivity.class);
                                startActivity(intent);
                            }
                        });
                        AlertDialog dialog2 = builder2.create();
                        dialog2.show();
                    } else {
                        Intent goToProfile = new Intent(RecyclingInfoActivity.this, UserProfileActivity.class);
                        startActivity(goToProfile);
                    }

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling_info);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        RecyclingInfoItem mobiusItem = new RecyclingInfoItem(R.drawable.logo_ruban_mobius, String.format(getString(R.string.mobius_title)), String.format("%s%s", getString(R.string.mobius_description_paragraph1), getString(R.string.mobius_description_paragraph2)));
        RecyclingInfoItem triMan = new RecyclingInfoItem(R.drawable.logo_tri_man, getString(R.string.triman_title), getString(R.string.triman_description));
        RecyclingInfoItem poubelleBarree = new RecyclingInfoItem(R.drawable.logo_poubelle_barree, getString(R.string.poubelle_barree_title), String.format("%s%s", getString(R.string.poubelle_barree_description), getString(R.string.poubelle_barree_description2)));
        RecyclingInfoItem tidyMan = new RecyclingInfoItem(R.drawable.logo_tidy_man, getString(R.string.tidyman_title), getString(R.string.tidyman_description));
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
