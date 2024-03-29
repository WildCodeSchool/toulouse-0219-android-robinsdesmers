package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserSingleton;
import fr.wildcodeschool.robinsdesmers.VolleySingleton;
import fr.wildcodeschool.robinsdesmers.model.RubbishItem;
import fr.wildcodeschool.robinsdesmers.model.User;

public class RubbishInfosActivity extends AppCompatActivity {

    private static final Integer SCORE_RUBBISH = 5;
    private static final Integer SCORE_RUBBISH_COLLECTED = 10;
    private RubbishItem rubbishItem;
    private UserSingleton userSingleton = UserSingleton.getUserInstance();
    private final Long userId = userSingleton.getUser().getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubbish_infos);

        Intent intent = getIntent();
        rubbishItem = intent.getParcelableExtra("RubbishItem");

        final Button btSurTerre = findViewById(R.id.btTerre);
        final Button btSurMer = findViewById(R.id.btMer);

        final CheckBox cbDechetRamasse = findViewById(R.id.cbDechetRamasse);

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

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cbDechetRamasse.isChecked()) {
                    rubbishItem.setCollected(true);
                    userSingleton.getUser().setScore(userSingleton.getUser().getScore() + SCORE_RUBBISH_COLLECTED);
                    Toast.makeText(RubbishInfosActivity.this, getString(R.string.merci_ramasser), Toast.LENGTH_LONG).show();
                }
                if (!btSurTerre.isSelected() && !btSurMer.isSelected() || rubbishItem.getSumRubbish() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RubbishInfosActivity.this);
                    builder.setTitle(R.string.merci_de);
                    builder.setMessage(R.string.remplir);
                    builder.setPositiveButton(R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    userSingleton.getUser().setScore(userSingleton.getUser().getScore() + SCORE_RUBBISH);
                    VolleySingleton.getInstance(RubbishInfosActivity.this).updateUser(userId, userSingleton.getUser(), new Consumer<User>() {
                        @Override
                        public void accept(User user) {
                            Intent intent = new Intent(RubbishInfosActivity.this, MapsActivity.class);
                            startActivity(intent);
                        }
                    });
                    VolleySingleton.getInstance(RubbishInfosActivity.this).postRubbish(rubbishItem, userSingleton.getUser(), new Consumer<RubbishItem>() {
                        @Override
                        public void accept(RubbishItem rubbishItem) {

                        }
                    });
                }

            }
        });
    }

    public void checked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rbBouteille:
                if (checked)
                    rubbishItem.setDescription(getString(R.string.bouteille));
                rubbishItem.setSumRubbish(1);
                break;
            case R.id.rbVerre:
                if (checked)
                    rubbishItem.setDescription(getString(R.string.verre));
                rubbishItem.setSumRubbish(1);
                break;
            case R.id.rbPlastique:
                if (checked)
                    rubbishItem.setDescription(getString(R.string.autre_plastique));
                rubbishItem.setSumRubbish(1);
                break;
            case R.id.rbMetal:
                if (checked)
                    rubbishItem.setDescription(getString(R.string.m_tal));
                rubbishItem.setSumRubbish(1);
                break;
            case R.id.rbMegot:
                if (checked)
                    rubbishItem.setDescription(getString(R.string.m_got));
                rubbishItem.setSumRubbish(1);
                break;
            case R.id.rbCarton:
                if (checked)
                    rubbishItem.setDescription(getString(R.string.carton));
                rubbishItem.setSumRubbish(1);
                break;
            case R.id.rbTissus:
                if (checked)
                    rubbishItem.setDescription(getString(R.string.tissus));
                rubbishItem.setSumRubbish(1);
                break;
            case R.id.rbAutre:
                if (checked)
                    rubbishItem.setDescription(getString(R.string.autres));
                rubbishItem.setSumRubbish(1);
                break;
        }
    }
}
