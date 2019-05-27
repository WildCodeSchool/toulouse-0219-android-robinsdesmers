package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ImageButton imBtSignIn = findViewById(R.id.imBtSign);
        imBtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHome = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(goToHome);
            }
        });
        /* TODO : link for forgot password
        TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);
        */
    }
}
