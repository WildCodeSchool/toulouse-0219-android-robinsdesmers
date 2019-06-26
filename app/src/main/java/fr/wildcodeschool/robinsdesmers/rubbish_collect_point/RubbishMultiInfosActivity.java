package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.VolleySingleton;
import fr.wildcodeschool.robinsdesmers.model.RubbishItem;
import fr.wildcodeschool.robinsdesmers.model.User;

public class RubbishMultiInfosActivity extends AppCompatActivity {

    final Integer SCORE_COLLECTED = 10;
    final Integer SCORE_DECLARED = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubbish_multi_infos);

        Intent intent = getIntent();
        final RubbishItem rubbishItem = intent.getParcelableExtra("RubbishItem");
        final User user = intent.getParcelableExtra("User");

        final Button btSurTerre = findViewById(R.id.btSurTerre);
        final Button btSurMer = findViewById(R.id.btSurMer);

        final EditText etBtlPlastique = findViewById(R.id.etBouteille);
        final EditText etMetal = findViewById(R.id.etMetal);
        final EditText etMegot = findViewById(R.id.etMegot);
        final EditText etPlastique = findViewById(R.id.etPlastique);
        final EditText etCarton = findViewById(R.id.etCarton);
        final EditText etVerre = findViewById(R.id.etVerre);
        final EditText etTissus = findViewById(R.id.etTissus);
        final EditText etAutre = findViewById(R.id.etAutres);

        final CheckBox cbDechetRamasse = findViewById(R.id.cbDechetsRamasses);

        ImageButton ibSendMutliRubbish = findViewById(R.id.ibSend);

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

        ibSendMutliRubbish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String strBtlPlastique = etBtlPlastique.getText().toString();
                if(!strBtlPlastique.isEmpty()) {
                    rubbishItem.setDescription(rubbishItem.getDescription() + strBtlPlastique + " " + getString(R.string.bouteilles_plastique));
                    Integer nbBtlPlastique = Integer.parseInt(strBtlPlastique);
                    rubbishItem.setSumRubbish(rubbishItem.getSumRubbish() + nbBtlPlastique);
                    user.setScore(user.getScore() + (nbBtlPlastique * SCORE_DECLARED));
                }

                final String strMetaux = etMetal.getText().toString();
                if(!strMetaux.isEmpty()) {
                    rubbishItem.setDescription(rubbishItem.getDescription() + strMetaux + " " + getString(R.string.metaux));
                    Integer nbMetaux = Integer.parseInt(strMetaux);
                    rubbishItem.setSumRubbish(rubbishItem.getSumRubbish() + nbMetaux);
                    user.setScore(user.getScore() + (nbMetaux * SCORE_DECLARED));
                }

                final String strMegots = etMegot.getText().toString();
                if(!strMegots.isEmpty()) {
                    rubbishItem.setDescription(rubbishItem.getDescription() + strMegots + " " + getString(R.string.m_gots));
                    Integer nbMegots = Integer.parseInt(strMegots);
                    rubbishItem.setSumRubbish(rubbishItem.getSumRubbish() + nbMegots);
                    user.setScore(user.getScore() + (nbMegots * SCORE_DECLARED));
                }

                final String strPlastique = etPlastique.getText().toString();
                if(!strPlastique.isEmpty()) {
                    rubbishItem.setDescription(rubbishItem.getDescription() + strPlastique + " " + getString(R.string.autres_plastique));
                    Integer nbPlastiques = Integer.parseInt(strPlastique);
                    rubbishItem.setSumRubbish(rubbishItem.getSumRubbish() + nbPlastiques);
                    user.setScore(user.getScore() + (nbPlastiques * SCORE_DECLARED));
                }

                final String strCartons = etCarton.getText().toString();
                if(!strCartons.isEmpty()) {
                    rubbishItem.setDescription(rubbishItem.getDescription() + strCartons + " " + getString(R.string.cartons));
                    Integer nbCartons = Integer.parseInt(strCartons);
                    rubbishItem.setSumRubbish(rubbishItem.getSumRubbish() + nbCartons);
                    user.setScore(user.getScore() + (nbCartons * SCORE_DECLARED));

                }

                final String strVerres = etVerre.getText().toString();
                if(!strVerres.isEmpty()) {
                    rubbishItem.setDescription(rubbishItem.getDescription() + strVerres + " " + getString(R.string.verres));
                    Integer nbVerres = Integer.parseInt(strVerres);
                    rubbishItem.setSumRubbish(rubbishItem.getSumRubbish() + nbVerres);
                    user.setScore(user.getScore() + (nbVerres * SCORE_DECLARED));
                }

                final String strTissus = etTissus.getText().toString();
                if(!strTissus.isEmpty()) {
                    rubbishItem.setDescription(rubbishItem.getDescription() + strTissus + " " + getString(R.string.tissus));
                    Integer nbrTissus = Integer.parseInt(strTissus);
                    rubbishItem.setSumRubbish(rubbishItem.getSumRubbish() + nbrTissus);
                    user.setScore(user.getScore() + (nbrTissus * SCORE_DECLARED));
                }

                final String strAutres = etAutre.getText().toString();
                if(!strAutres.isEmpty()) {
                    rubbishItem.setDescription(rubbishItem.getDescription() + strAutres + " " + getString(R.string.autres));
                    Integer nbrAutres = Integer.parseInt(strAutres);
                    rubbishItem.setSumRubbish(rubbishItem.getSumRubbish() + nbrAutres);
                    user.setScore(user.getScore() + (nbrAutres * SCORE_DECLARED));
                }

                if (cbDechetRamasse.isChecked()) {
                    rubbishItem.setCollected(true);
                    user.setScore(user.getScore() + (rubbishItem.getSumRubbish() * SCORE_COLLECTED));
                }

                Intent intent = new Intent(RubbishMultiInfosActivity.this, MapsActivity.class);
                startActivity(intent);
                VolleySingleton.getInstance(RubbishMultiInfosActivity.this).postRubbish(rubbishItem, user);
            }
        });
    }
}
