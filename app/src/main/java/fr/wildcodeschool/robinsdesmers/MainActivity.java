package fr.wildcodeschool.robinsdesmers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.util.Consumer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import fr.wildcodeschool.robinsdesmers.information.InformationActivity;
import fr.wildcodeschool.robinsdesmers.model.Stats;


public class MainActivity extends AppCompatActivity {
    UserSingleton userSingleton = UserSingleton.getUserInstance();
    private TextView tvUsers;
    private TextView tvCollectPoint;
    private TextView tvRubbish;
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(R.string.page_en_dev);
                    builder.setMessage(R.string.mission_mensuelles_alert);
                    builder.setPositiveButton(R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
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
                    if (userSingleton.getUser().getAvatar() == null) {
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                        builder2.setTitle(R.string.merci_de);
                        builder2.setMessage(R.string.acces_visiteur_profile);
                        builder2.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, FirstPageActivity.class);
                                startActivity(intent);
                            }
                        });
                        AlertDialog dialog2 = builder2.create();
                        dialog2.show();
                    } else {
                        Intent goToProfile = new Intent(MainActivity.this, UserProfileActivity.class);
                        startActivity(goToProfile);
                    }
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvUsers = findViewById(R.id.tvNbrRobins);
        tvCollectPoint = findViewById(R.id.tvNbrePointCollecte);
        tvRubbish = findViewById(R.id.tvNbrDechetsCollectes);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        VolleySingleton.getInstance(MainActivity.this).getStats(new Consumer<Stats>() {
            @Override
            public void accept(final Stats stats) {
                tvUsers.setText(String.valueOf(stats.getNbUsers()));
                tvRubbish.setText(String.valueOf(stats.getNbRubbishes()));
                tvCollectPoint.setText(String.valueOf(stats.getNbCollectPoints()));
            }
        });
    }
}
