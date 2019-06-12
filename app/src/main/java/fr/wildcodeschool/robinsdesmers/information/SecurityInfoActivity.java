package fr.wildcodeschool.robinsdesmers.information;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import fr.wildcodeschool.robinsdesmers.MainActivity;
import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserProfileActivity;

public class SecurityInfoActivity extends AppCompatActivity {

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
                    Intent goToProfile = new Intent(SecurityInfoActivity.this, UserProfileActivity.class);
                    startActivity(goToProfile);
                    return true;
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

    }
}
