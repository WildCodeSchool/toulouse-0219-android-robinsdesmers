package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import fr.wildcodeschool.robinsdesmers.information.InformationActivity;
import fr.wildcodeschool.robinsdesmers.model.CollectPointItem;
import fr.wildcodeschool.robinsdesmers.model.RubbishItem;
import fr.wildcodeschool.robinsdesmers.model.User;

public class MainActivity extends AppCompatActivity {
    private final UserSingleton userSingleton = UserSingleton.getUserInstance();
    private final Long userId = userSingleton.getUser().getId();
    private int mProgress = 0;
    private int mProgress2 = 0;
    private int mProgressRubbish = 0;
    private ProgressBar progressBar2;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private Handler handler = new Handler();
    private Handler handler1 = new Handler();
    private Handler handler2 = new Handler();

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
        progressBar2 = findViewById(R.id.pbCollectPoint);
        textView = findViewById(R.id.tvUsers);
        textView1 = findViewById(R.id.tvRubbish);
        textView2 = findViewById(R.id.tvCollectPoint);
        textView3 = findViewById(R.id.tvNbCollectPoint);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        VolleySingleton.getInstance(MainActivity.this).getAllUsers(new Consumer<List<User>>() {
            @Override
            public void accept(List<User> users) {
                for (User user : users) {
                    mProgress = users.size();
                }
                textView.setText(getString(R.string.robins_main) + " " + String.valueOf(mProgress));
            }
        });

        VolleySingleton.getInstance(MainActivity.this).getAllRubbish(new Consumer<List<RubbishItem>>() {
            @Override
            public void accept(List<RubbishItem> rubbishItems) {
                for (RubbishItem rubbish : rubbishItems) {
                    mProgressRubbish = rubbishItems.size();
                    textView3.setText(String.valueOf(mProgressRubbish));
                    textView1.setText(getString(R.string.dechets_declares));
                }
            }
        });

        VolleySingleton.getInstance(MainActivity.this).getAllCollectPoint(new Consumer<List<CollectPointItem>>() {
            @Override
            public void accept(List<CollectPointItem> collectPointItems) {
                for (CollectPointItem collectPointItem : collectPointItems) {
                    mProgress2 = collectPointItems.size();
                }
                textView2.setText(getString(R.string.pointcollecte_main) + "\n" + String.valueOf(mProgress2));
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        int progressStatus = 0;
                        while (progressStatus < mProgress2) {
                            progressStatus++;

                            handler2.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar2.setProgress(mProgress2);
                                }
                            });
                        }
                    }
                }).start();
            }
        });

    }
}
