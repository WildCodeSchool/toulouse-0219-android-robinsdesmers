package fr.wildcodeschool.robinsdesmers.inscription;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.model.User;

public class UserDescriptionActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_descripiton);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("user");

        final EditText pseudo = findViewById(R.id.etPseudo);
        final EditText description = findViewById(R.id.etDescription);

        ImageButton btSend = findViewById(R.id.imBtRegisterDesc);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pseudoStr = pseudo.getText().toString();
                String descriptionStr = description.getText().toString();
                user.setPseudo(pseudoStr);
                user.setDescription(descriptionStr);

                Intent intent = new Intent(UserDescriptionActivity.this, AvatarChoicesActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}
