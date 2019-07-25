package fr.wildcodeschool.robinsdesmers.inscription;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserSingleton;

public class UserCategoryActivity extends AppCompatActivity {

    private UserSingleton userSingleton = UserSingleton.getUserInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_category);

        ImageButton btSend = findViewById(R.id.imBtRegisterCategoryEdit);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userSingleton.getUser().getCategory() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserCategoryActivity.this);
                    builder.setTitle(R.string.merci_de);
                    builder.setMessage(R.string.remplir);
                    builder.setPositiveButton(R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Intent intent = new Intent(UserCategoryActivity.this, UserDescriptionActivity.class);
                    startActivity(intent);
                }
            }
        });
        final Button btCitizen = findViewById(R.id.btCategoryCitizenEdit);
        final Button btNavigator = findViewById(R.id.btCategoryNavigatorEdit);
        final Button btEcosystem = findViewById(R.id.btCategoryEcosystemEdit);

        btCitizen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btCitizen.setSelected(true);
                userSingleton.getUser().setCategory(getString(R.string.citoyen));
                btNavigator.setSelected(false);
                btEcosystem.setSelected(false);
            }
        });

        btNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btNavigator.setSelected(true);
                userSingleton.getUser().setCategory(getString(R.string.navigateur));
                btCitizen.setSelected(false);
                btEcosystem.setSelected(false);
            }
        });

        btEcosystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btEcosystem.setSelected(true);
                userSingleton.getUser().setCategory(getString(R.string.ecosysteme));
                btCitizen.setSelected(false);
                btNavigator.setSelected(false);
            }
        });
    }
}
