package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.model.User;
import fr.wildcodeschool.robinsdesmers.model.RubbishItem;

public class RubbishInfosActivity extends AppCompatActivity {

    final int SCORE_RUBBISH = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubbish_infos);

        Intent intent = getIntent();
        final RubbishItem rubbishItem = intent.getParcelableExtra("RubbishItem");
        final User user = intent.getParcelableExtra("User");

        final Button btSurTerre = findViewById(R.id.btTerre);
        final Button btSurMer = findViewById(R.id.btMer);

        CheckBox cbBouteille = findViewById(R.id.cbBouteille);
        CheckBox cbVerre = findViewById(R.id.cbVerre);
        CheckBox cbAutrePlastique = findViewById(R.id.cbAutrePlastique);
        CheckBox cbMetal = findViewById(R.id.cbMetal);
        CheckBox cbMegot = findViewById(R.id.cbAutres);
        CheckBox cbCarton = findViewById(R.id.cbCarton);
        CheckBox cbTissus = findViewById(R.id.cbTissus);
        CheckBox cbAutres = findViewById(R.id.cbAutres);

        CheckBox cbDechetRamasse = findViewById(R.id.cbDechetRamasse);

        ImageButton btSend = findViewById(R.id.ibSendRubbish);

        btSurTerre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btSurTerre.setSelected(true);
                btSurMer.setSelected(false);
            }
        });

        btSurMer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btSurTerre.setSelected(false);
                btSurMer.setSelected(true);
            }
        });

        cbBouteille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.bouteille));
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbVerre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.verre));
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbAutrePlastique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.autre_plastique));
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbMetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.m_tal));
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbMegot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.m_got));
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbCarton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.carton));
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbTissus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.tissus));
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbAutres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.autres));
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbDechetRamasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

       /* btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RubbishInfosActivity.this, MapsActivity.class);
                startActivity(intent);
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference markersRef = database.getReference("RubbishItem");
                String key = markersRef.push().getKey();
                rubbishItem.setKey(key);
                markersRef.child(key).setValue(rubbishItem);

                DatabaseReference userRef = database.getReference("User");
                String key2 = userRef.push().getKey();
                userRef.child(key2).setValue(user);
            }
        }); */
    }
}
