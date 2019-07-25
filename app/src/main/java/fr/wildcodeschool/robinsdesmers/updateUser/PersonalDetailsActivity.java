package fr.wildcodeschool.robinsdesmers.updateUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;

public class PersonalDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        Button btPersoDetails = findViewById(R.id.btPersoDetails);
        Button btLessPersoDetails = findViewById(R.id.btLessPersoDetails);
        Button btAvatar = findViewById(R.id.btAvatar);
        Button btCategory = findViewById(R.id.btCategory);
        Button btBackToMaps = findViewById(R.id.btBackMaps);

        btPersoDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalDetailsActivity.this, UserPersoDetailsActivity.class);
                startActivity(intent);
            }
        });

        btLessPersoDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalDetailsActivity.this, LessPersoDetailsActivity.class);
                startActivity(intent);
            }
        });

        btAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalDetailsActivity.this, AvatarUserDetailsActivity.class);
                startActivity(intent);
            }
        });

        btCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalDetailsActivity.this, CategoryUserDetailsActivity.class);
                startActivity(intent);
            }
        });

        btBackToMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalDetailsActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
