package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MarkerType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_type);

        Button buttonD = findViewById(R.id.btDéchetSeul);
        Button buttonA = findViewById(R.id.btAmas);
        Button buttonC = findViewById(R.id.btLieu);
        Button buttonBack = findViewById(R.id.btBack);

        Intent intent = getIntent();
        final RubbishMarkers location = intent.getParcelableExtra("RubbishMarkers");

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoRubbish("Déchet seul");
                Intent intent = new Intent(MarkerType.this, RubbishInfos.class);
                intent.putExtra("RubbishMarkers", location);
                startActivity(intent);
            }
        });
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoRubbish("Amas de déchets");
                Intent intent = new Intent(MarkerType.this, RubbishMultiInfos.class);
                intent.putExtra("RubbishMarkers", location);
                startActivity(intent);
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoRubbish("Point de collecte");
                Intent intent = new Intent(MarkerType.this, CollectPointInfos.class);
                intent.putExtra("RubbishMarkers", location);
                startActivity(intent);
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarkerType.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
