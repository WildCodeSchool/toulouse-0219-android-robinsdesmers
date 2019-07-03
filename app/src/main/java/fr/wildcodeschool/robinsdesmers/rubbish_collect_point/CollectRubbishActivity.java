package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserSingleton;
import fr.wildcodeschool.robinsdesmers.VolleySingleton;
import fr.wildcodeschool.robinsdesmers.model.RubbishItem;
import fr.wildcodeschool.robinsdesmers.model.User;

public class CollectRubbishActivity extends AppCompatActivity {

    private UserSingleton userSingleton = UserSingleton.getUserInstance();
    private final Long userId = userSingleton.getUser().getId();
    private  RubbishItem rubbishItem1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_rubbish);

        final CheckBox checkBox = findViewById(R.id.cbIsCollected);
        Button btSend = findViewById(R.id.btSendCollect);
        Intent intent = getIntent();
        final Long rubbishId = intent.getLongExtra("rubbishId", -1);
        VolleySingleton.getInstance(CollectRubbishActivity.this).getOneRubbish(rubbishId, new Consumer<RubbishItem>() {
            @Override
            public void accept(RubbishItem rubbishItem) {
                rubbishItem1 = rubbishItem;
            }
        });

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    userSingleton.getUser().setScore(userSingleton.getUser().getScore() + (rubbishItem1.getSumRubbish() * 10));
                    VolleySingleton.getInstance(CollectRubbishActivity.this).updateUser(userId, userSingleton.getUser(), new Consumer<User>() {
                        @Override
                        public void accept(User user) {

                        }
                    });
                    VolleySingleton.getInstance(CollectRubbishActivity.this).collectRubbish(rubbishId, new Consumer<RubbishItem>() {
                        @Override
                        public void accept(RubbishItem rubbishItem) {
                            Intent intent = new Intent(CollectRubbishActivity.this, MapsActivity.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    Intent intent = new Intent(CollectRubbishActivity.this, MapsActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
