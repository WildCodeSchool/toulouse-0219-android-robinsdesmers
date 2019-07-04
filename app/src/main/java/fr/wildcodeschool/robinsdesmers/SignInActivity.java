package fr.wildcodeschool.robinsdesmers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;
import java.util.List;

import fr.wildcodeschool.robinsdesmers.model.User;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final SharedPreferences sharePreference = SignInActivity.this.getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharePreference.edit();
        final EditText email = findViewById(R.id.etEmailSignIn);
        final EditText password = findViewById(R.id.etPasswordSignIn);
        final CheckBox checkBox = findViewById(R.id.chbRemember);

        ImageButton imBtSignIn = findViewById(R.id.imBtSign);
        imBtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String emailStr = email.getText().toString();
                final String passwordStr = password.getText().toString();


                HashCode hashCode = Hashing.sha256().hashString(passwordStr, Charset.defaultCharset());
                final String passwordHash = hashCode.toString();

                VolleySingleton.getInstance(SignInActivity.this).getAllUsers(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) {
                        for (User user : users) {

                            if (checkBox.isChecked()) {
                                editor.putString("saveEmail", emailStr);
                                editor.putString("savePassword", passwordStr);
                                editor.putBoolean("checked", true);
                                editor.putBoolean("isLoggedIn", true);
                                editor.putLong("userId", user.getId());
                                editor.apply();
                            } else {
                                editor.clear();
                                editor.commit();
                            }

                            if (emailStr.equals(user.getEmail()) && passwordHash.equals(user.getPassword())) {
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
        email.setText(sharePreference.getString("saveEmail", null));
        password.setText(sharePreference.getString("savePassword", null));
        checkBox.setChecked(sharePreference.getBoolean("checked", false));
    }
}
