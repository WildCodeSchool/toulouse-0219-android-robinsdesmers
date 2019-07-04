package fr.wildcodeschool.robinsdesmers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

                final TextView tvScore = findViewById(R.id.tvScoreVolley);
                tvScore.setText(String.format(getString(R.string.cent_cinquante), userSingleton.getUser().getScore()));

                progressBar = findViewById(R.id.progressBarScore);
                final TextView gradeUser = findViewById(R.id.tvGradeVolley);
                gradeUser.setText(getString(R.string.plancton));
                progressBar.setProgress(userSingleton.getUser().getScore());
                if (userSingleton.getUser().getScore() > 150) {
                    progressBar.setMax(450);
                    gradeUser.setText(getString(R.string.corail));
                    tvScore.setText(String.format(getString(R.string.quatre_cent_cinquante), userSingleton.getUser().getScore()));
                    progressBar.setProgress(userSingleton.getUser().getScore());
                }
                if (userSingleton.getUser().getScore() > 450) {
                    progressBar.setMax(1000);
                    gradeUser.setText(getString(R.string.crevette));
                    tvScore.setText(String.format(getString(R.string.mille), userSingleton.getUser().getScore()));
                    progressBar.setProgress(userSingleton.getUser().getScore());
                }
                if (userSingleton.getUser().getScore() > 1000) {
                    progressBar.setMax(2000);
                    gradeUser.setText(getString(R.string.oursin));
                    tvScore.setText(String.format(getString(R.string.deux_mille), userSingleton.getUser().getScore()));
                    progressBar.setProgress(userSingleton.getUser().getScore());
                }
                if (userSingleton.getUser().getScore() > 2000) {
                    progressBar.setMax(4000);
                    gradeUser.setText(getString(R.string.crabe));
                    tvScore.setText(String.format(getString(R.string.quatre_mille), userSingleton.getUser().getScore()));
                    progressBar.setProgress(userSingleton.getUser().getScore());
                }
                if (userSingleton.getUser().getScore() > 4000) {
                    progressBar.setMax(6000);
                    gradeUser.setText(getString(R.string.globe));
                    tvScore.setText(String.format(getString(R.string.six_mille), userSingleton.getUser().getScore()));
                    progressBar.setProgress(userSingleton.getUser().getScore());
                }
                if (userSingleton.getUser().getScore() > 6000) {
                    progressBar.setMax(8000);
                    gradeUser.setText(getString(R.string.hermite));
                    tvScore.setText(String.format(getString(R.string.huit_mille), userSingleton.getUser().getScore()));
                    progressBar.setProgress(userSingleton.getUser().getScore());
                }
                if (userSingleton.getUser().getScore() > 8000) {
                    progressBar.setMax(10000);
                    gradeUser.setText(getString(R.string.poulpe));
                    tvScore.setText(String.format(getString(R.string.dix_mille), userSingleton.getUser().getScore()));
                    progressBar.setProgress(userSingleton.getUser().getScore());
                }
                if (userSingleton.getUser().getScore() > 10000) {
                    progressBar.setMax(12000);
                    gradeUser.setText(getString(R.string.robin));
                    tvScore.setText(String.format(getString(R.string.douze_mille), userSingleton.getUser().getScore()));
                    progressBar.setProgress(userSingleton.getUser().getScore());
                }
                if (userSingleton.getUser().getScore() > 12000) {
                    progressBar.setMax(14000);
                    gradeUser.setText(getString(R.string.tortue));
                    tvScore.setText(String.format(getString(R.string.quatorze_mille), userSingleton.getUser().getScore()));
                    progressBar.setProgress(userSingleton.getUser().getScore());
                }
                if (userSingleton.getUser().getScore() > 14000) {
                    progressBar.setMax(16000);
                    gradeUser.setText(getString(R.string.requin));
                    tvScore.setText(String.format(getString(R.string.seize_mille), userSingleton.getUser().getScore()));
                    progressBar.setProgress(userSingleton.getUser().getScore());
                }
                if (userSingleton.getUser().getScore() > 16000) {
                    progressBar.setMax(18000);
                    gradeUser.setText(getString(R.string.baleine));
                    tvScore.setText(String.format(getString(R.string.dix_huit_mille), userSingleton.getUser().getScore()));
                    progressBar.setProgress(userSingleton.getUser().getScore());
                }
                if (userSingleton.getUser().getScore() > 18000) {
                    progressBar.setMax(20000);
                    gradeUser.setText(getString(R.string.plique));
                    tvScore.setText(String.format(getString(R.string.vingt_mille), userSingleton.getUser().getScore()));
                    progressBar.setProgress(userSingleton.getUser().getScore());
                }
                if (userSingleton.getUser().getScore() > 20000) {
                    progressBar.setMax(30000);
                    gradeUser.setText(getString(R.string.trepide));
                    tvScore.setText(String.format(getString(R.string.trente_mille), userSingleton.getUser().getScore()));
                    progressBar.setProgress(userSingleton.getUser().getScore());
                }
            }
        });

        FloatingActionButton floatBtSetting = findViewById(R.id.floatBtProfile);
        floatBtSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfileActivity.this, PersonalDetailsActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton floatBtLogout = findViewById(R.id.floatingBtLogout);
        floatBtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(UserProfileActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}
