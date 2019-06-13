package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.User;
import fr.wildcodeschool.robinsdesmers.model.CollectPoint;
import fr.wildcodeschool.robinsdesmers.model.RubbishMarkers;

public class MarkerTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_type);

        Button buttonD = findViewById(R.id.btDéchetSeul);
        Button buttonA = findViewById(R.id.btAmas);
        Button buttonC = findViewById(R.id.btLieu);
        Button buttonBack = findViewById(R.id.btBack);

        Intent intent = getIntent();
        final CollectPoint collectPoint = intent.getParcelableExtra("CollectPoint");
        final RubbishMarkers location = intent.getParcelableExtra("RubbishMarkers");
        final User user = new User(0);

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoRubbish(getString(R.string.dechet_seul));
                Intent intent = new Intent(MarkerTypeActivity.this, RubbishInfosActivity.class);
                intent.putExtra("RubbishMarkers", location);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoRubbish(getString(R.string.amas_de_dechets));
                Intent intent = new Intent(MarkerTypeActivity.this, RubbishMultiInfosActivity.class);
                intent.putExtra("RubbishMarkers", location);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectPoint.setInfoCollectPoint(getString(R.string.point_de_collecte));
                Intent intent = new Intent(MarkerTypeActivity.this, CollectPointInfosActivity.class);
                intent.putExtra("CollectPoint", collectPoint);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarkerTypeActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
