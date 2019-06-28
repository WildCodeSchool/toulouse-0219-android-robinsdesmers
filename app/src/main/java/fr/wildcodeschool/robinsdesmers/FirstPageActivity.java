package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.inscription.InscriptionActivity;

public class FirstPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        ImageButton imBtRegister = findViewById(R.id.ibRegisterPrincipal);

        imBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegister = new Intent(FirstPageActivity.this, InscriptionActivity.class);
                startActivity(goToRegister);
            }
        });

        ImageButton imBtSignIn = findViewById(R.id.ibSignPrincipal);

        imBtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignIn = new Intent(FirstPageActivity.this, SignInActivity.class);
                startActivity(goToSignIn);
            }
        });

        ImageButton imBtVisitor = findViewById(R.id.ibVisitorPrincipal);

        imBtVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(FirstPageActivity.this, MapsActivity.class);
                startActivity(goToHome);
            }
        });
    }
}
