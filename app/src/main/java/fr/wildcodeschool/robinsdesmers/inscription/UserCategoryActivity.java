package fr.wildcodeschool.robinsdesmers.inscription;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.model.User;

public class UserCategoryActivity extends AppCompatActivity {
    Intent intent = getIntent();
    User user = intent.getParcelableExtra("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_category);

        ImageButton btSend = findViewById(R.id.imBtRegisterCategory);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCategoryActivity.this, UserDescriptionActivity.class);
                intent.putExtra("user",user);
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
                user.setCategory("Citoyen");
                btNavigator.setSelected(false);
                btEcosystem.setSelected(false);
            }
        });

        btNavigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btNavigator.setSelected(true);
                user.setCategory("Navigateur");
                btCitizen.setSelected(false);
                btEcosystem.setSelected(false);
            }
        });

        btEcosystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btEcosystem.setSelected(true);
                user.setCategory("Ecosystème");
                btCitizen.setSelected(false);
                btNavigator.setSelected(false);
            }
        });
    }
}
