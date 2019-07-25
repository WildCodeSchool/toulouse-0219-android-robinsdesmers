package fr.wildcodeschool.robinsdesmers.inscription;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserSingleton;

public class InscriptionActivity extends AppCompatActivity {

    private static final int MIN_PASSWORD_SIZE = 8;
    private UserSingleton userSingleton = UserSingleton.getUserInstance();

    private static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        ImageButton imBtRegister = findViewById(R.id.imBtEditLessPerso);

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
                HashCode hashCode = Hashing.sha256().hashString(password.getText().toString(), Charset.defaultCharset());
                userSingleton.getUser().setPassword(hashCode.toString());
                if (password.getText().toString().length() < MIN_PASSWORD_SIZE) {
                    Toast.makeText(InscriptionActivity.this, getString(R.string.mot_passe_incorrect), Toast.LENGTH_LONG).show();
                } else {
                    if (!isValidEmail(email.getText().toString().trim())) {
                        Toast.makeText(InscriptionActivity.this, getString(R.string.email_incorrect), Toast.LENGTH_LONG).show();
                    }
                        if (userSingleton.getUser().getLastName().isEmpty() || userSingleton.getUser().getFirstName().isEmpty()) {
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
                }
        });
    }
}

