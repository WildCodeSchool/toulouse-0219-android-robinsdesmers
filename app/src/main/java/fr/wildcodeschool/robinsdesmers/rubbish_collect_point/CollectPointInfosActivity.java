package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.model.User;
import fr.wildcodeschool.robinsdesmers.model.CollectPoint;

public class CollectPointInfosActivity extends AppCompatActivity {

    final int SCORE_COLLECT_POINT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_point_infos);

        Intent intent = getIntent();
        final CollectPoint collectPoint = intent.getParcelableExtra("CollectPoint");
        final User user = intent.getParcelableExtra("User");

        final CheckBox cbPoubelle = findViewById(R.id.cbPoubelle);
        final CheckBox cbPoubelleTri = findViewById(R.id.cbPoubelleTri);
        final CheckBox cbDechetterie = findViewById(R.id.cbDechetterie);
        final CheckBox cbBenne = findViewById(R.id.cbBenne);
        ImageButton btSend = findViewById(R.id.btSend);

        cbBenne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectPoint.setInfoSup(getString(R.string.benne_de_revalorisation));
                user.setScore(SCORE_COLLECT_POINT);
            }
        });
        cbPoubelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectPoint.setInfoSup(getString(R.string.poubelle_classique));
                user.setScore(SCORE_COLLECT_POINT);
            }
        });
        cbPoubelleTri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectPoint.setInfoSup(getString(R.string.poubelle_de_tri));
                user.setScore(SCORE_COLLECT_POINT);
            }

        });
        cbDechetterie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectPoint.setInfoSup(getString(R.string.decheterie));
                user.setScore(SCORE_COLLECT_POINT);
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
