package fr.wildcodeschool.robinsdesmers.inscription;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.R;

public class UserCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_category);

        ImageButton btSend = findViewById(R.id.imBtRegisterCategory);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCategoryActivity.this, UserDescriptionActivity.class);
                startActivity(intent);
            }
        });
        final Button btCitizen = findViewById(R.id.btCategoryCitizen);
        final Button btNavigator = findViewById(R.id.btCategoryNavigator);
        final Button btEcosystem = findViewById(R.id.btCategoryEcosystem);

        btCitizen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btCitizen.setSelected(true);
                btNavigator.setSelected(false);
                btEcosystem.setSelected(false);
            }
        });

        btNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btNavigator.setSelected(true);
                btCitizen.setSelected(false);
                btEcosystem.setSelected(false);
            }
        });

        btEcosystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btEcosystem.setSelected(true);
                btCitizen.setSelected(false);
                btNavigator.setSelected(false);
            }
        });
    }
}
