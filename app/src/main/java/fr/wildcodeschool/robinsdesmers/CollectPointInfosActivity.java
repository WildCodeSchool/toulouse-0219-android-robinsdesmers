package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CollectPointInfosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_point_infos);

        Intent intent = getIntent();
        final CollectPoint collectPoint = intent.getParcelableExtra("CollectPoint");
        final User user = intent.getParcelableExtra("User");

        CheckBox checkBoxP = findViewById(R.id.cbPoubelle);
        CheckBox checkBoxPT = findViewById(R.id.cbPoubelleTri);
        CheckBox checkBoxD = findViewById(R.id.cbDecheterie);
        CheckBox checkBoxB = findViewById(R.id.cbBenne);
        Button btSend = findViewById(R.id.btSend3);

        checkBoxB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectPoint.setInfoSup(getString(R.string.benne_de_revalorisation));
                user.setCompteur(10);

            }
        });
        checkBoxP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectPoint.setInfoSup(getString(R.string.poubelle_classique));
                user.setCompteur(10);
            }
        });
        checkBoxPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectPoint.setInfoSup(getString(R.string.poubelle_de_tri));
                user.setCompteur(10);
            }

        });
        checkBoxD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectPoint.setInfoSup(getString(R.string.decheterie));
                user.setCompteur(10);
            }
        });
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectPointInfosActivity.this, MapsActivity.class);
                startActivity(intent);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference markersRef = database.getReference("CollectPoint");
                String key = markersRef.push().getKey();
                collectPoint.setKey(key);
                markersRef.child(key).setValue(collectPoint);

                DatabaseReference userRef = database.getReference("User");
                String key2 = userRef.push().getKey();
                userRef.child(key2).setValue(user);
            }
        });
    }
}
