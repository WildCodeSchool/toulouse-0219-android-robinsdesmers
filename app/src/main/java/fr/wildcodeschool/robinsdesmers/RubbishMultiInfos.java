package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RubbishMultiInfos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubbish_multi_infos);

        Intent intent = getIntent();
        final RubbishMarkers location = intent.getParcelableExtra("RubbishMarkers");

        final EditText etBt = findViewById(R.id.etBouteille);
        final EditText etMa = findViewById(R.id.etMetal);
        final EditText etM = findViewById(R.id.etMegot);
        final EditText etP = findViewById(R.id.etPlastique);
        final EditText etC = findViewById(R.id.etCarton);
        final EditText etV = findViewById(R.id.etVerre);
        Button btSend = findViewById(R.id.btSend2);

        etBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Bouteille = etBt.getText().toString();
                location.setInfoSup(Bouteille + " Bouteilles");
            }
        });
        etMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Metal = etMa.getText().toString();
                location.setInfoSup(Metal + " Cartons");
            }
        });
        etM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Megot = etM.getText().toString();
                location.setInfoSup(Megot + " Megots");
            }
        });
        etC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carton = etC.getText().toString();
                location.setInfoSup(carton + " Cartons");
            }
        });
        etV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Verre = etV.getText().toString();
                location.setInfoSup(Verre + " Déchets en verre");
            }
        });
        etP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plastique = etP.getText().toString();
                location.setInfoSup(plastique + " Déchets en plastique");
            }
        });

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RubbishMultiInfos.this, MapsActivity.class);
                startActivity(intent);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference markersRef = database.getReference("RubbishMarkers");
                markersRef.push().setValue(location);
            }
        });
    }
}
