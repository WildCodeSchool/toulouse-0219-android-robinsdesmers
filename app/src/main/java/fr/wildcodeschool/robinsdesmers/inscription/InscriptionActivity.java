package fr.wildcodeschool.robinsdesmers.inscription;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.model.User;

public class InscriptionActivity extends AppCompatActivity {
    private User user = new User(6L,"","","","","","","","","","",2,4);

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
                user.setLastName(nameRegister.getText().toString());
                user.setFirstName(firstNameRegister.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());
                if(user.getLastName().isEmpty() || user.getFirstName().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(InscriptionActivity.this);
                    builder.setTitle(R.string.merci_de);
                    builder.setMessage(R.string.remplir);
                    builder.setPositiveButton(R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Intent goToHome = new Intent(InscriptionActivity.this, UserDetailsActivity.class);
                    goToHome.putExtra("user", user);
                    startActivity(goToHome);
                }
            }
        });
    }
}
