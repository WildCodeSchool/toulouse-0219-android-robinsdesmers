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
import fr.wildcodeschool.robinsdesmers.adapter.SecurityInfoAdapter;
import fr.wildcodeschool.robinsdesmers.model.SecurityInfoItem;

public class SecurityInfoActivity extends AppCompatActivity {
    UserSingleton userSingleton = UserSingleton.getUserInstance();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent goToHome = new Intent(SecurityInfoActivity.this, MainActivity.class);
                    startActivity(goToHome);
                    return true;
                case R.id.navigation_mission:
                    return true;
                case R.id.navigation_carte:
                    Intent goToMaps = new Intent(SecurityInfoActivity.this, MapsActivity.class);
                    startActivity(goToMaps);
                    return true;
                case R.id.navigation_info:
                    Intent goToInfo = new Intent(SecurityInfoActivity.this, InformationActivity.class);
                    startActivity(goToInfo);
                    return true;
                case R.id.navigation_profile:
                    if (userSingleton.getUser().getAvatar() == null) {
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(SecurityInfoActivity.this);
                        builder2.setTitle(R.string.merci_de);
                        builder2.setMessage(R.string.acces_visiteur_profile);
                        builder2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SecurityInfoActivity.this, FirstPageActivity.class);
                                startActivity(intent);
                            }
                        });
                        AlertDialog dialog2 = builder2.create();
                        dialog2.show();
                    } else {
                        Intent goToProfile = new Intent(SecurityInfoActivity.this, UserProfileActivity.class);
                        startActivity(goToProfile);
                    }

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_info);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        SecurityInfoItem gants = new SecurityInfoItem(getString(R.string.gants));
        SecurityInfoItem chaussure = new SecurityInfoItem(getString(R.string.chaussure));
        SecurityInfoItem objDangereux = new SecurityInfoItem(getString(R.string.objDangereux));
        SecurityInfoItem danger = new SecurityInfoItem(getString(R.string.danger));
        SecurityInfoItem circulation = new SecurityInfoItem(getString(R.string.circulation));
        SecurityInfoItem lieuPublic = new SecurityInfoItem(getString(R.string.lieuPublic));
        SecurityInfoItem enfant = new SecurityInfoItem(getString(R.string.enfant));
        SecurityInfoItem poids = new SecurityInfoItem(getString(R.string.poids));
        SecurityInfoItem mains = new SecurityInfoItem(getString(R.string.mains));
        List<SecurityInfoItem> securityInfoItems = new ArrayList<>();
        securityInfoItems.add(gants);
        securityInfoItems.add(chaussure);
        securityInfoItems.add(objDangereux);
        securityInfoItems.add(danger);
        securityInfoItems.add(circulation);
        securityInfoItems.add(lieuPublic);
        securityInfoItems.add(enfant);
        securityInfoItems.add(poids);
        securityInfoItems.add(mains);

        RecyclerView mRecyclerView;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager layoutManager;
        mRecyclerView = findViewById(R.id.rvSecurityInfo);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new SecurityInfoAdapter(securityInfoItems);
        mRecyclerView.setAdapter(mAdapter);

        SecurityInfoItem poubellePublic = new SecurityInfoItem(getString(R.string.info_jeter_dechets));
        SecurityInfoItem horaire = new SecurityInfoItem(getString(R.string.info_heure_ouverture));
        SecurityInfoItem laverMains = new SecurityInfoItem(getString(R.string.info_laver_mains));
        List<SecurityInfoItem> throwRubbishInfoItems = new ArrayList<>();
        throwRubbishInfoItems.add(poubellePublic);
        throwRubbishInfoItems.add(horaire);
        throwRubbishInfoItems.add(laverMains);

        RecyclerView mThrowRubbishRecyclerView;
        RecyclerView.Adapter mThrowRubbishAdapter;
        RecyclerView.LayoutManager layoutManagerThrowRubbish;
        mThrowRubbishRecyclerView = findViewById(R.id.rvThrowingRubbish);
        layoutManagerThrowRubbish = new LinearLayoutManager(this);
        mThrowRubbishRecyclerView.setLayoutManager(layoutManagerThrowRubbish);
        mThrowRubbishAdapter = new SecurityInfoAdapter(throwRubbishInfoItems);
        mThrowRubbishRecyclerView.setAdapter(mThrowRubbishAdapter);
    }
}
