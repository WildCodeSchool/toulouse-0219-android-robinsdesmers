package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RubbishInfos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubbish_infos);

        Intent intent = getIntent();
        final RubbishMarkers location = intent.getParcelableExtra("RubbishMarkers");

        CheckBox checkBoxB = findViewById(R.id.cbBouteille);
        CheckBox checkBoxC = findViewById(R.id.cbCarton);
        CheckBox checkBoxMa = findViewById(R.id.cbMetal);
        CheckBox checkBoxM = findViewById(R.id.cbMegot);
        CheckBox checkBoxP = findViewById(R.id.cbPlastique);
        CheckBox checkBoxV = findViewById(R.id.cbVerre);
        Button btSend = findViewById(R.id.btSend);

        checkBoxC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup("Carton");
            }
        });
        checkBoxB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup("Bouteille");
            }
        });
        checkBoxMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup("Métal");
            }
        });
        checkBoxM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup("Mégot");
            }
        });
        checkBoxP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup("Plastique");
            }
        });
        checkBoxV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup("Verre");
            }
        });
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RubbishInfos.this, MapsActivity.class);
                startActivity(intent);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference markersRef = database.getReference("RubbishMarkers");
                markersRef.push().setValue(location);
            }
        });
    }
}
