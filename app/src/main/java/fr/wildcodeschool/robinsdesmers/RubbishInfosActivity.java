package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RubbishInfosActivity extends AppCompatActivity {

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
        Button btSend = findViewById(R.id.btSend4);

        checkBoxC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup(getString(R.string.carton));
            }
        });
        checkBoxB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup(getString(R.string.bouteille));
            }
        });
        checkBoxMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup(getString(R.string.metal));
            }
        });
        checkBoxM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup(getString(R.string.megot));
            }
        });
        checkBoxP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup(getString(R.string.plastique));
            }
        });
        checkBoxV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup(getString(R.string.verre));
            }
        });
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RubbishInfosActivity.this, MapsActivity.class);
                startActivity(intent);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference markersRef = database.getReference("RubbishMarkers");
                markersRef.push().setValue(location);
            }
        });
    }
}
