package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.VolleySingleton;
import fr.wildcodeschool.robinsdesmers.model.CollectPointItem;
import fr.wildcodeschool.robinsdesmers.model.User;

public class CollectPointInfosActivity extends AppCompatActivity {

    final int SCORE_COLLECT_POINT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_point_infos);

        Intent intent = getIntent();
        final CollectPointItem collectPointItem = intent.getParcelableExtra("CollectPointItem");
        final User user = intent.getParcelableExtra("User");

        final CheckBox cbPoubelle = findViewById(R.id.cbPoubelle);
        final CheckBox cbPoubelleTri = findViewById(R.id.cbPoubelleTri);
        final CheckBox cbDechetterie = findViewById(R.id.cbDechetterie);
        final CheckBox cbBenne = findViewById(R.id.cbBenne);

        final CheckBox cbClassique = findViewById(R.id.cbClassique);
        final CheckBox cbMegotPdc = findViewById(R.id.cbMegotPdc);
        final CheckBox cbRecyclable = findViewById(R.id.cbRecyclable);
        final CheckBox cbCanette = findViewById(R.id.cbCanette);
        final CheckBox cbVerre = findViewById(R.id.cbVerre);
        final CheckBox cbPlastique = findViewById(R.id.cbPlastique);
        final CheckBox cbAutres = findViewById(R.id.cbAutres);

        ImageButton btSend = findViewById(R.id.btSend);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cbBenne.isChecked()) {
                    collectPointItem.setTitle(collectPointItem.getTitle() + getString(R.string.benne_de_revalorisation));
                    user.setScore(user.getScore() + SCORE_COLLECT_POINT);
                }

                if (cbPoubelle.isChecked()) {
                    collectPointItem.setTitle(collectPointItem.getTitle() + getString(R.string.poubelle_classique));
                    user.setScore(user.getScore() + SCORE_COLLECT_POINT);
                }

                if (cbPoubelleTri.isChecked()) {
                    collectPointItem.setTitle(collectPointItem.getTitle() + getString(R.string.benne_de_revalorisation));
                    user.setScore(user.getScore() + SCORE_COLLECT_POINT);
                }

                if (cbDechetterie.isChecked()) {
                    collectPointItem.setTitle(collectPointItem.getTitle() + getString(R.string.benne_de_revalorisation));
                    user.setScore(user.getScore() + SCORE_COLLECT_POINT);
                }

                if (cbClassique.isChecked()) {
                    collectPointItem.setDescription(collectPointItem.getDescription() + getString(R.string.classique));
                }

                if (cbMegotPdc.isChecked()) {
                    collectPointItem.setDescription(collectPointItem.getDescription() + getString(R.string.m_got));
                }

                if (cbRecyclable.isChecked()) {
                    collectPointItem.setDescription(collectPointItem.getDescription() + getString(R.string.recyclable));
                }

                if (cbCanette.isChecked()) {
                    collectPointItem.setDescription(collectPointItem.getDescription() + getString(R.string.canettes));
                }

                if (cbVerre.isChecked()) {
                    collectPointItem.setDescription(collectPointItem.getDescription() + getString(R.string.verre));
                }

                if (cbPlastique.isChecked()) {
                    collectPointItem.setDescription(collectPointItem.getDescription() + getString(R.string.plastique));
                }

                if (cbAutres.isChecked()) {
                    collectPointItem.setDescription(collectPointItem.getDescription() + getString(R.string.autres));
                }

                Intent intent = new Intent(CollectPointInfosActivity.this, MapsActivity.class);
                startActivity(intent);
                VolleySingleton.getInstance(CollectPointInfosActivity.this).postCollectPoint(collectPointItem, user);
            }
        });
    }
}
