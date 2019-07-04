package fr.wildcodeschool.robinsdesmers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import fr.wildcodeschool.robinsdesmers.model.User;

public class ScreenSplashActivity extends AppCompatActivity {
    final int MILLIS = 2000;
    final int ROTATION_START = 0;
    final int ROTATION_END = 300;
    final int DURATION = 1300;
    private User mUser = null;
    private boolean loading = true;
    private boolean animation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_splash);

        ImageView imageView = findViewById(R.id.ivScreenImg);
        RotateAnimation rotate = new RotateAnimation(ROTATION_START, ROTATION_END);
        rotate.setDuration(DURATION);
        imageView.startAnimation(rotate);

        Thread myThread = new Thread() {

            public void run() {
                try {
                    sleep(MILLIS);
                    animation = false;
                    if (!loading) {
                        if (mUser != null) {
                            Intent goToHome = new Intent(ScreenSplashActivity.this, MapsActivity.class);
                            startActivity(goToHome);
                        } else {
                            Intent intent = new Intent(ScreenSplashActivity.this, FirstPageActivity.class);
                            startActivity(intent);
                        }
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();

        final SharedPreferences preferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        if (preferences.getBoolean("isLoggedIn", false)) {
            String token = preferences.getString("token", null);
            VolleySingleton.getInstance(ScreenSplashActivity.this).signByToken(token, new Consumer<User>() {
                @Override
                public void accept(User user) {
                    UserSingleton.getUserInstance().setUser(user);
                    mUser = user;
                    loading = false;
                    if (!animation) {
                        Intent goToHome = new Intent(ScreenSplashActivity.this, MapsActivity.class);
                        startActivity(goToHome);
                        finish();
                    }
                }
            });
        } else {
            loading = false;
        }
    }
}
