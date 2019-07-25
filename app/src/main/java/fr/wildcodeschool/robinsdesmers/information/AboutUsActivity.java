package fr.wildcodeschool.robinsdesmers.information;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import fr.wildcodeschool.robinsdesmers.FirstPageActivity;
import fr.wildcodeschool.robinsdesmers.MainActivity;
import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserProfileActivity;
import fr.wildcodeschool.robinsdesmers.UserSingleton;

public class AboutUsActivity extends AppCompatActivity {

    UserSingleton userSingleton = UserSingleton.getUserInstance();

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
                    if (userSingleton.getUser().getAvatar() == null) {
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(AboutUsActivity.this);
                        builder2.setTitle(R.string.merci_de);
                        builder2.setMessage(R.string.acces_visiteur_profile);
                        builder2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(AboutUsActivity.this, FirstPageActivity.class);
                                startActivity(intent);
                            }
                        });
                        AlertDialog dialog2 = builder2.create();
                        dialog2.show();
                    } else {
                        Intent goToProfile = new Intent(AboutUsActivity.this, UserProfileActivity.class);
                        startActivity(goToProfile);
                    }
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
        Intent rdmWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_rdm)));
        startActivity(rdmWebsite);
    }

    public void openFaceBook(View view) {
        Intent facebookWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.facebook_url)));
        startActivity(facebookWebsite);
    }

    public void openInstagram(View view) {
        Intent instagramWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_insta)));
        startActivity(instagramWebsite);
    }

    public void openTwitter(View view) {
        Intent twitterWebsite = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_twitter)));
        startActivity(twitterWebsite);
    }
}
