package fr.wildcodeschool.robinsdesmers.information;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import fr.wildcodeschool.robinsdesmers.MainActivity;
import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserProfileActivity;

public class AboutUsActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_INVITE = 5076;

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

        findViewById(R.id.btInviteFriends).setOnClickListener(this);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, String.format(getString(R.string.on_connection_failed2), connectionResult));
        showMessage(getString(R.string.erreur_google));
    }

    private void onInviteClicked() {

        Intent intent = new AppInviteInvitation.IntentBuilder(getString(R.string.invite_tes_amis))
                .setMessage(getString(R.string.rejoindre_la_communaute))
                .setDeepLink(Uri.parse(getString(R.string.url_oessit)))
                .setCustomImage(Uri.parse(getString(R.string.url_logo_google)))
                .setCallToActionText(getString(R.string.installer))
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, String.format(getString(R.string.onActivityResult), requestCode, resultCode));

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                for (String id : ids) {
                    Log.d(TAG, String.format(getString(R.string.invitation_envoyées), id));
                }
            } else {
                showMessage(getString(R.string.envoi_echoué));
            }
        }
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btInviteFriends:
                onInviteClicked();
                break;
        }
    }
}
