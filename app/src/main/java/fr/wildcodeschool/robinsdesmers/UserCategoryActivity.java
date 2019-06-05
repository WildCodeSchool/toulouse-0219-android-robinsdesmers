package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class UserCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_category);

        ImageButton btSend = findViewById(R.id.imBtRegisterCatgory);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserCategoryActivity.this, UserDescripitonActivity.class);
                startActivity(intent);
            }
        });
        final Button btCitoyen = findViewById(R.id.btCategoryCitoyen);
        final Button btNavigateur = findViewById(R.id.btCategoryNavigateur);
        final Button btEcosystem = findViewById(R.id.btCategoryEcosystem);

        btCitoyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btCitoyen.setSelected(true);
                btNavigateur.setSelected(false);
                btEcosystem.setSelected(false);
            }
        });

        btNavigateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btNavigateur.setSelected(true);
                btCitoyen.setSelected(false);
                btEcosystem.setSelected(false);
            }
        });

        btEcosystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btEcosystem.setSelected(true);
                btCitoyen.setSelected(false);
                btNavigateur.setSelected(false);
            }
        });
    }
}
