package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
//TODO change button's color when selected
       /* final Button bt = findViewById(R.id.tvCategoryNavigateur);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.setBackgroundResource(R.drawable.button_categoory_selected);
                bt.setBackgroundResource(R.drawable.button_categoory_selected);
            }
        });*/


    }
}
