package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CollectPointInfos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_point_infos);

        Intent intent = getIntent();
        final RubbishMarkers location = intent.getParcelableExtra("RubbishMarkers");

        CheckBox checkBoxP = findViewById(R.id.cbPoubelle);
        CheckBox checkBoxPT = findViewById(R.id.cbPoubelleTri);
        CheckBox checkBoxD = findViewById(R.id.cbDecheterie);
        CheckBox checkBoxB = findViewById(R.id.cbBenne);
        Button btSend = findViewById(R.id.btSend3);

        checkBoxB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup("Benne de revalorisation");
            }
        });
        checkBoxP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup("Poubelle Classique");
            }
        });
        checkBoxPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup("Poubelle de Tri");
            }
        });
        checkBoxD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup("Décheterie");
            }
        });
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectPointInfos.this,MapsActivity.class);
                startActivity(intent);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference markersRef = database.getReference("RubbishMarkers");
                markersRef.push().setValue(location);
            }
        });
    }
}
