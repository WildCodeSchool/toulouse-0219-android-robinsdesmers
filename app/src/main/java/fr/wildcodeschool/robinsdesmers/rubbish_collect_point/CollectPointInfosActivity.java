package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserSingleton;
import fr.wildcodeschool.robinsdesmers.VolleySingleton;
import fr.wildcodeschool.robinsdesmers.model.CollectPointItem;
import fr.wildcodeschool.robinsdesmers.model.RubbishItem;
import fr.wildcodeschool.robinsdesmers.model.User;

public class CollectPointInfosActivity extends AppCompatActivity {

    final int SCORE_COLLECT_POINT = 10;
    private UserSingleton userSingleton = UserSingleton.getUserInstance();
    private final Long userId = userSingleton.getUser().getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_point_infos);

        Intent intent = getIntent();
        final CollectPointItem collectPointItem = intent.getParcelableExtra("CollectPointItem");

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
                    userSingleton.getUser().setScore(userSingleton.getUser().getScore() + SCORE_COLLECT_POINT);
                    VolleySingleton.getInstance(CollectPointInfosActivity.this).updateUserScore(userId, new Consumer<User>() {
                        @Override
                        public void accept(User user) {

                        }
                    });

                }

                if (cbPoubelle.isChecked()) {
                    collectPointItem.setTitle(collectPointItem.getTitle() + getString(R.string.poubelle_classique));
                    userSingleton.getUser().setScore(userSingleton.getUser().getScore() + SCORE_COLLECT_POINT);
                    VolleySingleton.getInstance(CollectPointInfosActivity.this).updateUserScore(userId, new Consumer<User>() {
                        @Override
                        public void accept(User user) {

                        }
                    });
                }

                if (cbPoubelleTri.isChecked()) {
                    collectPointItem.setTitle(collectPointItem.getTitle() + getString(R.string.benne_de_revalorisation));
                    userSingleton.getUser().setScore(userSingleton.getUser().getScore() + SCORE_COLLECT_POINT);
                    VolleySingleton.getInstance(CollectPointInfosActivity.this).updateUserScore(userId, new Consumer<User>() {
                        @Override
                        public void accept(User user) {

                        }
                    });
                }

                if (cbDechetterie.isChecked()) {
                    collectPointItem.setTitle(collectPointItem.getTitle() + getString(R.string.benne_de_revalorisation));
                    userSingleton.getUser().setScore(userSingleton.getUser().getScore() + SCORE_COLLECT_POINT);
                    VolleySingleton.getInstance(CollectPointInfosActivity.this).updateUserScore(userId, new Consumer<User>() {
                        @Override
                        public void accept(User user) {

                        }
                    });
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

                if (collectPointItem.getTitle().isEmpty() || collectPointItem.getDescription().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CollectPointInfosActivity.this);
                    builder.setTitle(R.string.merci_de);
                    builder.setMessage(R.string.remplir);
                    builder.setPositiveButton(R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Intent intent = new Intent(CollectPointInfosActivity.this, MapsActivity.class);
                    startActivity(intent);
                    VolleySingleton.getInstance(CollectPointInfosActivity.this).postCollectPoint(collectPointItem, userSingleton.getUser(), new Consumer<CollectPointItem>() {
                        @Override
                        public void accept(CollectPointItem collectPointItem) {

                        }
                    });
                }
            }
        });
    }
}
