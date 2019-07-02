package fr.wildcodeschool.robinsdesmers.updateUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserProfileActivity;
import fr.wildcodeschool.robinsdesmers.UserSingleton;
import fr.wildcodeschool.robinsdesmers.VolleySingleton;
import fr.wildcodeschool.robinsdesmers.model.User;

public class UserPersoDetailsActivity extends AppCompatActivity {

    private UserSingleton userSingleton = UserSingleton.getUserInstance();
    private final Long userId = userSingleton.getUser().getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_perso_details);
        final EditText etLastName = findViewById(R.id.etLastName);
        final EditText etFirstName = findViewById(R.id.etFirstName);
        final EditText etEmail = findViewById(R.id.etEmail);
        final EditText etPassword = findViewById(R.id.etPassword);
        final EditText etPseudo = findViewById(R.id.etPseudo);
        final EditText etDescription = findViewById(R.id.etDescription);
        VolleySingleton.getInstance(UserPersoDetailsActivity.this).getOneUser(userId, new Consumer<User>() {
            @Override
            public void accept(User user) {
                etLastName.setText(userSingleton.getUser().getLastName());
                etFirstName.setText(userSingleton.getUser().getFirstName());
                etEmail.setText(userSingleton.getUser().getEmail());
                etPassword.setText(userSingleton.getUser().getPassword());
                etPseudo.setText(userSingleton.getUser().getPseudo());
                etDescription.setText(userSingleton.getUser().getDescription());
            }
        });

        ImageButton btSendEdit = findViewById(R.id.imBtEditLessPerso);
        btSendEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String lastNameStr = etLastName.getText().toString();
                final String firstNameStr = etFirstName.getText().toString();
                final String emailStr = etEmail.getText().toString();
                final String passwordStr = etPassword.getText().toString();
                final String pseudoStr = etPseudo.getText().toString();
                final String descriptionStr = etDescription.getText().toString();
                userSingleton.getUser().setLastName(lastNameStr);
                userSingleton.getUser().setFirstName(firstNameStr);
                userSingleton.getUser().setEmail(emailStr);
                userSingleton.getUser().setPassword(passwordStr);
                userSingleton.getUser().setPseudo(pseudoStr);
                userSingleton.getUser().setDescription(descriptionStr);

                VolleySingleton.getInstance(UserPersoDetailsActivity.this).updateUser(userId, userSingleton.getUser(), new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        Intent intent = new Intent(UserPersoDetailsActivity.this, UserProfileActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
