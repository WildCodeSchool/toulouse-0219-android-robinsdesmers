package fr.wildcodeschool.robinsdesmers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;

import fr.wildcodeschool.robinsdesmers.model.User;

public class ScreenSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_splash);

        final SharedPreferences preferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        if (preferences.getBoolean("isLoggedIn", false)) {
            String token = preferences.getString("token", null);
            VolleySingleton.getInstance(ScreenSplashActivity.this).signByToken(token, new Consumer<User>() {
                @Override
                public void accept(User user) {
                    UserSingleton.getUserInstance().setUser(user);
                    Intent goToHome = new Intent(ScreenSplashActivity.this, MapsActivity.class);
                    startActivity(goToHome);
                    finish();
                }
            });
        } else {
            Intent intent = new Intent(ScreenSplashActivity.this, FirstPageActivity.class);
            startActivity(intent);
        }
    }
}
