package fr.wildcodeschool.robinsdesmers.information;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import fr.wildcodeschool.robinsdesmers.MainActivity;
import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserProfileActivity;

public class InformationActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent goToHome = new Intent(InformationActivity.this, MainActivity.class);
                    startActivity(goToHome);
                    return true;
                case R.id.navigation_mission:
                    AlertDialog.Builder builder = new AlertDialog.Builder(InformationActivity.this);
                    builder.setTitle(R.string.page_en_dev);
                    builder.setMessage(R.string.mission_mensuelles_alert);
                    builder.setPositiveButton(R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return true;
                case R.id.navigation_carte:
                    Intent goToMaps = new Intent(InformationActivity.this, MapsActivity.class);
                    startActivity(goToMaps);
                    return true;
                case R.id.navigation_info:
                    Intent goToInfo = new Intent(InformationActivity.this, InformationActivity.class);
                    startActivity(goToInfo);
                    return true;
                case R.id.navigation_profile:
                    Intent goToProfile = new Intent(InformationActivity.this, UserProfileActivity.class);
                    startActivity(goToProfile);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Button btRecycle = findViewById(R.id.btRecyclingInfo);
        Button btSecurity = findViewById(R.id.btSecurityInfo);
        Button btAboutUs = findViewById(R.id.btAboutUs);

        btRecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRecyclingInfo = new Intent(InformationActivity.this, RecyclingInfoActivity.class);
                startActivity(goToRecyclingInfo);
            }
        });

        btSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSecurityInfo = new Intent(InformationActivity.this, SecurityInfoActivity.class);
                startActivity(goToSecurityInfo);
            }
        });

        btAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAboutUs = new Intent(InformationActivity.this, AboutUsActivity.class);
                startActivity(goToAboutUs);
            }
        });
    }
}
