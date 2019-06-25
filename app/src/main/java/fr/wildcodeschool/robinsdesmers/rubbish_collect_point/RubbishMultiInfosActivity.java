package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.model.RubbishItem;
import fr.wildcodeschool.robinsdesmers.model.User;

public class RubbishMultiInfosActivity extends AppCompatActivity {

    final int SCORE_MULTI_RUBBISH = 10;
    final int SCORE_MULTI_RUBBISH_SIMPLE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubbish_multi_infos);

        Intent intent = getIntent();
        final RubbishItem location = intent.getParcelableExtra("RubbishItem");
        final User user = intent.getParcelableExtra("User");

        final Button btSurTerre = findViewById(R.id.btSurTerre);
        final Button btSurMer = findViewById(R.id.btSurMer);

        final EditText etBt = findViewById(R.id.etBouteille);
        final EditText etMa = findViewById(R.id.etMetal);
        final EditText etM = findViewById(R.id.etMegot);
        final EditText etP = findViewById(R.id.etPlastique);
        final EditText etC = findViewById(R.id.etCarton);
        final EditText etV = findViewById(R.id.etVerre);

        CheckBox cbDechetRamasse = findViewById(R.id.cbDechetsRamasses);

        ImageButton ibSendMutliRubbish = findViewById(R.id.ibSend);

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

        etBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bouteille = etBt.getText().toString();
                if (Integer.parseInt(bouteille) > 5) {
                    user.setScore(SCORE_MULTI_RUBBISH);
                } else {
                    user.setScore(SCORE_MULTI_RUBBISH_SIMPLE);
                }
                location.setDescription(bouteille + getString(R.string.bouteilles));
            }
        });
        etMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String metal = etMa.getText().toString();
                if (Integer.parseInt(metal) > 5) {
                    user.setScore(SCORE_MULTI_RUBBISH);
                } else {
                    user.setScore(SCORE_MULTI_RUBBISH_SIMPLE);
                }
                location.setDescription(metal + getString(R.string.metaux));
            }
        });
        etM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String megot = etM.getText().toString();
                if (Integer.parseInt(megot) > 5) {
                    user.setScore(SCORE_MULTI_RUBBISH);
                } else {
                    user.setScore(SCORE_MULTI_RUBBISH_SIMPLE);
                }
                location.setDescription(megot + getString(R.string.m_gots));
            }
        });
        etC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String carton = etC.getText().toString();
                if (Integer.parseInt(carton) > 5) {
                    user.setScore(SCORE_MULTI_RUBBISH);
                } else {
                    user.setScore(SCORE_MULTI_RUBBISH_SIMPLE);
                }
                location.setDescription(carton + getString(R.string.cartons));
            }
        });
        etV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verre = etV.getText().toString();
                if (Integer.parseInt(verre) > 5) {
                    user.setScore(SCORE_MULTI_RUBBISH);
                } else {
                    user.setScore(SCORE_MULTI_RUBBISH_SIMPLE);
                }
                location.setDescription(verre + getString(R.string.dechets_en_verre));
            }
        });
        etP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String plastique = etP.getText().toString();
                if (Integer.parseInt(plastique) > 5) {
                    user.setScore(SCORE_MULTI_RUBBISH);
                } else {
                    user.setScore(SCORE_MULTI_RUBBISH_SIMPLE);
                }

                location.setDescription(plastique + getString(R.string.dechets_en_plastique));
            }
        });

    }
}
