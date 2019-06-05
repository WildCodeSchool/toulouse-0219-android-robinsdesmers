package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoRubbish(getString(R.string.dechet_seul));
                Intent intent = new Intent(MarkerTypeActivity.this, RubbishInfosActivity.class);
                intent.putExtra("RubbishMarkers", location);
                startActivity(intent);
            }
        });
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoRubbish(getString(R.string.amas_de_dechets));
                Intent intent = new Intent(MarkerTypeActivity.this, RubbishMultiInfosActivity.class);
                intent.putExtra("RubbishMarkers", location);
                startActivity(intent);
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectPoint.setInfoCollectPoint(getString(R.string.point_de_collecte));
                Intent intent = new Intent(MarkerTypeActivity.this, CollectPointInfosActivity.class);
                intent.putExtra("CollectPoint", collectPoint);
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
