package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.User;
import fr.wildcodeschool.robinsdesmers.model.RubbishMarkers;

public class RubbishInfosActivity extends AppCompatActivity {

    final int SCORE_RUBBISH = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubbish_infos);

        Intent intent = getIntent();
        final RubbishMarkers location = intent.getParcelableExtra("RubbishMarkers");
        final User user = intent.getParcelableExtra("User");

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
                user.setScore(SCORE_RUBBISH);
            }
        });
        checkBoxB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup(getString(R.string.bouteille));
                user.setScore(SCORE_RUBBISH);
            }
        });
        checkBoxMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup(getString(R.string.metal));
                user.setScore(SCORE_RUBBISH);
            }
        });
        checkBoxM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup(getString(R.string.megot));
                user.setScore(SCORE_RUBBISH);
            }
        });
        checkBoxP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup(getString(R.string.plastique));
                user.setScore(SCORE_RUBBISH);
            }
        });
        checkBoxV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location.setInfoSup(getString(R.string.verre));
                user.setScore(SCORE_RUBBISH);
            }
        });

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RubbishInfosActivity.this, MapsActivity.class);
                startActivity(intent);
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference markersRef = database.getReference("RubbishMarkers");
                String key = markersRef.push().getKey();
                location.setKey(key);
                markersRef.child(key).setValue(location);

                DatabaseReference userRef = database.getReference("User");
                String key2 = userRef.push().getKey();
                userRef.child(key2).setValue(user);
            }
        });
    }
}
