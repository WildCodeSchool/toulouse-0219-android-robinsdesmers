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

import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;

import fr.wildcodeschool.robinsdesmers.MainActivity;
import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserProfileActivity;

public class AboutUsActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_INVITE = 101;
    private GoogleApiClient mGoogleApiClient;

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

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(AppInvite.API)
                .enableAutoManage(this, this)
                .build();

        boolean autoLaunchDeepLink = true;
        AppInvite.AppInviteApi.getInvitation(mGoogleApiClient, this, autoLaunchDeepLink)
                .setResultCallback(
                        new ResultCallback<AppInviteInvitationResult>() {
                            @Override
                            public void onResult(AppInviteInvitationResult result) {
                                Log.d(TAG, "getInvitation:onResult:" + result.getStatus());
                            }
                        });
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        showMessage("Google Play services error");
    }

    private void onInviteClicked() {

        /*Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=fr.wildcodeschool.metro");
        startActivity(Intent.createChooser(sharingIntent, "How do you want to share?"));*/

        Intent intent = new AppInviteInvitation.IntentBuilder("Invite tes amis à rejoindre la communauté")
                .setMessage("Salut! J'ai découvert cette super communauté et je t'invite à la rejoindre")
                .setDeepLink(Uri.parse("https://play.google.com/store/apps/details?id=fr.wildcodeschool.metro"))
                .setCustomImage(Uri.parse("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"))
                .setCallToActionText("Installer")
                .build();
        startActivityForResult(intent, REQUEST_INVITE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

        if (requestCode == REQUEST_INVITE) {
            if (resultCode == RESULT_OK) {
                String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                Log.d(TAG, ids + " invitations envoyées");
            } else {
                showMessage("L'envoi des invitations a échoué");
            }
        }
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btInviteFriends:
                onInviteClicked();
                break;
        }
    }
}
