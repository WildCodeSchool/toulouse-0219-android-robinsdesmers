package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import fr.wildcodeschool.robinsdesmers.information.InformationActivity;
import fr.wildcodeschool.robinsdesmers.model.User;

public class MainActivity extends AppCompatActivity {
    private static final UserSingleton userSingleton = UserSingleton.getUserInstance();
    private ProgressBar progressBar;
    private Handler handler = new Handler();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent goToHome = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(goToHome);
                    return true;
                case R.id.navigation_mission:
                    return true;
                case R.id.navigation_carte:
                    Intent goToMaps = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(goToMaps);
                    return true;
                case R.id.navigation_info:
                    Intent goToInfo = new Intent(MainActivity.this, InformationActivity.class);
                    startActivity(goToInfo);
                    return true;
                case R.id.navigation_profile:
                    Intent goToProfile = new Intent(MainActivity.this, UserProfileActivity.class);
                    startActivity(goToProfile);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        VolleySingleton.getInstance(MainActivity.this).getAllUsers(new Consumer<List<User>>() {
            @Override
            public void accept(List<User> users) {
                for (User user : users) {
                    TextView tvScore = findViewById(R.id.tvUserScore);
                    tvScore.setText(String.valueOf(user.getScore()));
                }
            }
        });

        progressBar = findViewById(R.id.progressBarScore);

        new Thread(new Runnable() {
            @Override
            public void run() {
                int progressStatus = 0;
                final int mprogress = userSingleton.getUser().getScore();
                while (progressStatus < mprogress) {
                    progressStatus++;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(mprogress);
                        }
                    });
                }
            }
        }).start();

    }
}
