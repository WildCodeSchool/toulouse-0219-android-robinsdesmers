package fr.wildcodeschool.robinsdesmers.inscription;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserSingleton;
import fr.wildcodeschool.robinsdesmers.model.User;

public class InscriptionActivity extends AppCompatActivity {

    private UserSingleton userSingleton = UserSingleton.getUserInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        ImageButton imBtRegister = findViewById(R.id.imBtRegister);

        imBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameRegister = findViewById(R.id.etNameRegister);
                EditText firstNameRegister = findViewById(R.id.etFirstNameRegister);
                EditText email = findViewById(R.id.etMailRegister);
                EditText password = findViewById(R.id.etPasswordRegister);
                userSingleton.getUser().setFirstName(firstNameRegister.getText().toString());
                userSingleton.getUser().setLastName(nameRegister.getText().toString());
                userSingleton.getUser().setEmail(email.getText().toString());
                userSingleton.getUser().setPassword(password.getText().toString());

                if (userSingleton.getUser().getLastName().isEmpty() || userSingleton.getUser().getFirstName().isEmpty()
                        || userSingleton.getUser().getEmail().isEmpty() || userSingleton.getUser().getPassword().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(InscriptionActivity.this);
                    builder.setTitle(R.string.merci_de);
                    builder.setMessage(R.string.remplir);
                    builder.setPositiveButton(R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Intent goToHome = new Intent(InscriptionActivity.this, UserDetailsActivity.class);
                    startActivity(goToHome);
                }
            }
        });
    }
}
