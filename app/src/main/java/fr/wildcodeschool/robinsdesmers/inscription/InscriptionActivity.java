package fr.wildcodeschool.robinsdesmers.inscription;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.model.User;

public class InscriptionActivity extends AppCompatActivity {
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        ImageButton imBtRegister = findViewById(R.id.imBtRegister);

        imBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* EditText nameRegister = findViewById(R.id.nameRegister);
                EditText email = findViewById(R.id.mailRegister);
                EditText password = findViewById(R.id.passwordRegister);
                user.setFirstName(nameRegister.getText().toString());
                user.setEmail(email.getText().toString());
                user.setPassword(password.getText().toString());*/
                Intent goToHome = new Intent(InscriptionActivity.this, UserDetailsActivity.class);
                //goToHome.putExtra("user",user);
                startActivity(goToHome);
            }
        });
    }
}
