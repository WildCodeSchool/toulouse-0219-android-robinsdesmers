package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.VolleySingleton;
import fr.wildcodeschool.robinsdesmers.model.RubbishItem;
import fr.wildcodeschool.robinsdesmers.model.User;

public class RubbishInfosActivity extends AppCompatActivity {

    final Integer SCORE_RUBBISH = 5;
    final Integer SCORE_RUBBISH_COLLECTED = 10;

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
        CheckBox cbMegot = findViewById(R.id.cbMegot);
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
                rubbishItem.setAtSea(false);
            }
        });

        btSurMer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btSurTerre.setSelected(false);
                btSurMer.setSelected(true);
                rubbishItem.setAtSea(true);

            }
        });

        cbBouteille.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.bouteille));
                rubbishItem.setSumRubbish(1);
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbVerre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.verre));
                rubbishItem.setSumRubbish(1);
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbAutrePlastique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.autre_plastique));
                rubbishItem.setSumRubbish(1);
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbMetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.m_tal));
                rubbishItem.setSumRubbish(1);
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbMegot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.m_got));
                rubbishItem.setSumRubbish(1);
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbCarton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.carton));
                rubbishItem.setSumRubbish(1);
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbTissus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.tissus));
                rubbishItem.setSumRubbish(1);
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbAutres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setDescription(getString(R.string.autres));
                rubbishItem.setSumRubbish(1);
                user.setScore(SCORE_RUBBISH);
            }
        });

        cbDechetRamasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setScore(SCORE_RUBBISH_COLLECTED);
                rubbishItem.setCollected(true);
            }
        });

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RubbishInfosActivity.this, MapsActivity.class);
                startActivity(intent);
                VolleySingleton.getInstance(RubbishInfosActivity.this).postRubbish(rubbishItem, user);
            }
        });
    }
}
