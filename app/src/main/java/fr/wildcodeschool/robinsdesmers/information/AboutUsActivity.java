package fr.wildcodeschool.robinsdesmers.information;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import fr.wildcodeschool.robinsdesmers.MainActivity;
import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserProfileActivity;

public class AboutUsActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent goToHome = new Intent(AboutUsActivity.this, MainActivity.class);
                    startActivity(goToHome);
                    return true;
                case R.id.navigation_mission:
                    return true;
                case R.id.navigation_carte:
                    Intent goToMaps = new Intent(AboutUsActivity.this, MapsActivity.class);
                    startActivity(goToMaps);
                    return true;
                case R.id.navigation_info:
                    Intent goToInfo = new Intent(AboutUsActivity.this, InformationActivity.class);
                    startActivity(goToInfo);
                    return true;
                case R.id.navigation_profile:
                    Intent goToProfile = new Intent(AboutUsActivity.this, UserProfileActivity.class);
                    startActivity(goToProfile);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void openRdm(View view) {
        Intent rdmWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.robinsdesmers.fr"));
        startActivity(rdmWebsite);
    }

    public void openFaceBook(View view) {
        Intent facebookWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/groups/robinsdesmers/"));
        startActivity(facebookWebsite);
    }

    public void openInstagram(View view) {
        Intent instagramWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/robinsdesmers/"));
        startActivity(instagramWebsite);
    }

    public void openTwitter(View view) {
        Intent twitterWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/RobinsDesmers"));
        startActivity(twitterWebsite);
    }
}
