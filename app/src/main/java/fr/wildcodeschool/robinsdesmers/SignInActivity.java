package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

import fr.wildcodeschool.robinsdesmers.model.User;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ImageButton imBtSignIn = findViewById(R.id.imBtSign);
        imBtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText email = findViewById(R.id.etEmailSignIn);
                EditText password = findViewById(R.id.etPasswordSignIn);
                final String emailStr = email.getText().toString();
                final String passwordStr = password.getText().toString();
                VolleySingleton.getInstance(SignInActivity.this).getAllUsers(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) {
                        for (User user : users) {
                            if (emailStr.equals(user.getEmail()) && passwordStr.equals(user.getPassword())) {
                                VolleySingleton.getInstance(SignInActivity.this).getOneUser(user.getId(), new Consumer<User>() {
                                    @Override
                                    public void accept(User user) {
                                        UserSingleton.getUserInstance().setUser(user);
                                        Intent goToHome = new Intent(SignInActivity.this, MapsActivity.class);
                                        startActivity(goToHome);
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });
        /* TODO : link for forgot password
        TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);
        */
    }
}
