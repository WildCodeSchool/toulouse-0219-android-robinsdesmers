package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.model.RubbishMarkers;

public class RubbishMultiInfosActivity extends AppCompatActivity {

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
                String bouteille = etBt.getText().toString();
                location.setInfoSup(bouteille + getString(R.string.bouteilles));
            }
        });
        etMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String metal = etMa.getText().toString();
                location.setInfoSup(metal + getString(R.string.metal));
            }
        });
        etM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String megot = etM.getText().toString();
                location.setInfoSup(megot + getString(R.string.megots));
            }
        });
        etC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carton = etC.getText().toString();
                location.setInfoSup(carton + getString(R.string.cartons));
            }
        });
        etV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verre = etV.getText().toString();
                location.setInfoSup(verre + getString(R.string.dechets_en_verre));
            }
        });
        etP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plastique = etP.getText().toString();
                location.setInfoSup(plastique + getString(R.string.dechets_en_plastique));
            }
        });

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RubbishMultiInfosActivity.this, MapsActivity.class);
                startActivity(intent);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference markersRef = database.getReference("RubbishMarkers");
                String key = markersRef.push().getKey();
                location.setKey(key);
                markersRef.child(key).setValue(location);
            }
        });
    }
}
