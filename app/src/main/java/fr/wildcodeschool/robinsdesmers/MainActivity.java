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
import fr.wildcodeschool.robinsdesmers.model.CollectPointItem;
import fr.wildcodeschool.robinsdesmers.model.RubbishItem;
import fr.wildcodeschool.robinsdesmers.model.User;

public class MainActivity extends AppCompatActivity {
    private final UserSingleton userSingleton = UserSingleton.getUserInstance();
    private final Long userId = userSingleton.getUser().getId();
    private int mProgress = 0;
    private int mProgress1 = 0;
    private int mProgress2 = 0;
    private ProgressBar progressBar;
    private ProgressBar progressBar1;
    private ProgressBar progressBar2;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
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
        progressBar = findViewById(R.id.pbNbUsers);
        progressBar1 = findViewById(R.id.pbRubbish);
        progressBar2 = findViewById(R.id.pbCollectPoint);
        textView = findViewById(R.id.tvUsers);
        textView1 = findViewById(R.id.tvRubbish);
        textView2 = findViewById(R.id.tvCollectPoint);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        /*VolleySingleton.getInstance(MainActivity.this).getOneUser(userId, new Consumer<User>() {
            @Override
            public void accept(User user) {

            }
        });*/
        VolleySingleton.getInstance(MainActivity.this).getAllUsers(new Consumer<List<User>>() {
            @Override
            public void accept(List<User> users) {
                for (User user : users) {
                    mProgress++;
                }
                textView.setText("Nombre de Robins : " + String.valueOf(mProgress));
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int progressStatus = 0;
                        while (progressStatus < mProgress) {
                            progressStatus++;

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(mProgress);
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        VolleySingleton.getInstance(MainActivity.this).getAllRubbish(new Consumer<List<RubbishItem>>() {
            @Override
            public void accept(List<RubbishItem> rubbishItems) {
                for (RubbishItem rubbish : rubbishItems) {
                    mProgress1++;
                }
                textView1.setText("Déchets déclarés : " + String.valueOf(mProgress1));
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        int progressStatus = 0;
                        while (progressStatus < mProgress1) {
                            progressStatus++;

                            handler1.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar1.setProgress(mProgress1);
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        VolleySingleton.getInstance(MainActivity.this).getAllCollectPoint(new Consumer<List<CollectPointItem>>() {
            @Override
            public void accept(List<CollectPointItem> collectPointItems) {
                for (CollectPointItem collectPointItem : collectPointItems) {
                    mProgress2++;
                }
                textView2.setText("Points de collecte : " + String.valueOf(mProgress2));
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
