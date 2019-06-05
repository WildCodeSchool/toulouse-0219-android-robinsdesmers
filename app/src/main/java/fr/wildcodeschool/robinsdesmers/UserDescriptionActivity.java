package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class UserDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_descripiton);

        ImageButton btSend = findViewById(R.id.imBtRegisterDesc);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDescriptionActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
