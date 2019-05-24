package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        ImageButton imBtRegister = findViewById(R.id.ibRegisterPrincipal);

        imBtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegister = new Intent(PrincipalActivity.this, InscriptionActivity.class);
                startActivity(goToRegister);
            }
        });

        ImageButton imBtSignIn = findViewById(R.id.ibSignPrincipal);

        imBtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignIn = new Intent(PrincipalActivity.this, SignInActivity.class);
                startActivity(goToSignIn);
            }
        });

        ImageButton imBtVisitor = findViewById(R.id.ibVisitorPrincipal);

        imBtVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(PrincipalActivity.this, MapsActivity.class);
                startActivity(goToHome);
            }
        });
    }
}
