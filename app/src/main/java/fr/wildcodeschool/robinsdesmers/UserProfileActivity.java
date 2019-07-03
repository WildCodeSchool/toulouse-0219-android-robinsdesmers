package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import fr.wildcodeschool.robinsdesmers.information.InformationActivity;
import fr.wildcodeschool.robinsdesmers.model.User;
import fr.wildcodeschool.robinsdesmers.updateUser.PersonalDetailsActivity;

public class UserProfileActivity extends AppCompatActivity {

    private UserSingleton userSingleton = UserSingleton.getUserInstance();
    private final Long userId = userSingleton.getUser().getId();
    private ProgressBar progressBar;
    private Handler handler = new Handler();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_home:
                    Intent goToHome = new Intent(UserProfileActivity.this, MainActivity.class);
                    startActivity(goToHome);
                    return true;
                case R.id.navigation_mission:
                    return true;
                case R.id.navigation_carte:
                    Intent goToMaps = new Intent(UserProfileActivity.this, MapsActivity.class);
                    startActivity(goToMaps);
                    return true;
                case R.id.navigation_info:
                    Intent goToInfo = new Intent(UserProfileActivity.this, InformationActivity.class);
                    startActivity(goToInfo);
                    return true;
                case R.id.navigation_profile:
                    Intent goToProfile = new Intent(UserProfileActivity.this, UserProfileActivity.class);
                    startActivity(goToProfile);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        VolleySingleton.getInstance(UserProfileActivity.this).getOneUser(userId, new Consumer<User>() {
            @Override
            public void accept(User user) {

                if (!(userSingleton.getUser().getPseudo().isEmpty())) {
                    TextView pseudoUser = findViewById(R.id.tvNameProfile);
                    pseudoUser.setText(userSingleton.getUser().getPseudo());
                } else {
                    TextView firstName = findViewById(R.id.tvNameProfile);
                    firstName.setText(userSingleton.getUser().getFirstName());
                }

                if (!(userSingleton.getUser().getDescription().isEmpty())) {
                    TextView description = findViewById(R.id.tvDescripitonProfile);
                    description.setText(userSingleton.getUser().getDescription());
                }

                ImageView imageViewAvatar = findViewById(R.id.ivAvatarProfile);
                imageViewAvatar.setImageResource(userSingleton.getUser().getAvatar());

                TextView categoryUser = findViewById(R.id.tvCategoryProfile);
                categoryUser.setText(userSingleton.getUser().getCategory());

                TextView departmentUser = findViewById(R.id.tvDepartmentProfile);
                departmentUser.setText(userSingleton.getUser().getDepartment());

                TextView tvScore = findViewById(R.id.tvScoreVolley);
                tvScore.setText(String.valueOf(user.getScore()));
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

        FloatingActionButton floatBtSetting = findViewById(R.id.floatBtProfile);
        floatBtSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, PersonalDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}
