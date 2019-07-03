package fr.wildcodeschool.robinsdesmers;

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

    public static final String PREFS_NAME = "MyPrefsFile";
    private SharedPreferences mPrefs;
    private EditText email, password;
    private String emailStr, passwordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ImageButton imBtSignIn = findViewById(R.id.imBtSign);
        imBtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = findViewById(R.id.etEmailSignIn);
                password = findViewById(R.id.etPasswordSignIn);
                emailStr = email.getText().toString();
                passwordStr = password.getText().toString();

                HashCode hashCode = Hashing.sha256().hashString(passwordStr, Charset.defaultCharset());
                final String passwordHash = hashCode.toString();
                final CheckBox mcheckboxRemember= findViewById(R.id.chbRemember);

                mPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                SharedPreferences sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                if(sp.contains("pref_mail")) {
                    String s = sp.getString("pref_mail", "");
                    email.setText(s.toString());
                }

                if(sp.contains("pref_password")) {
                    String p = sp.getString("pref_password", "");
                    password.setText(p.toString());
                }

                if(sp.contains("pref_check")) {
                    Boolean b = sp.getBoolean("pref_check", false);
                    mcheckboxRemember.setChecked(b);
                }

                if(mcheckboxRemember.isChecked()) {
                    final Boolean booleanCheked = mcheckboxRemember.isChecked();
                    SharedPreferences.Editor editor = mPrefs.edit();
                    editor.putString("pref_mail", emailStr);
                    editor.putString("pref_password", passwordStr);
                    editor.putBoolean("pref_check", booleanCheked);
                    editor.apply();
                } else {
                    mPrefs.edit().clear().apply();
                }

                VolleySingleton.getInstance(SignInActivity.this).getAllUsers(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) {
                        for (User user : users) {
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

        /* TODO : link for forgot password
        TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);
        */
    }
}
