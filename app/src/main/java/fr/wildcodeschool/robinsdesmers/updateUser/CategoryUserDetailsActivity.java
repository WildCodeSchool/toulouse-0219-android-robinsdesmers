package fr.wildcodeschool.robinsdesmers.updateUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserProfileActivity;
import fr.wildcodeschool.robinsdesmers.UserSingleton;
import fr.wildcodeschool.robinsdesmers.VolleySingleton;
import fr.wildcodeschool.robinsdesmers.model.User;

public class CategoryUserDetailsActivity extends AppCompatActivity {

    private UserSingleton userSingleton = UserSingleton.getUserInstance();
    private final Long userId = userSingleton.getUser().getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_user_details);

        ImageButton btSend = findViewById(R.id.imBtRegisterCategoryEdit);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VolleySingleton.getInstance(CategoryUserDetailsActivity.this).updateUser(userId, userSingleton.getUser(), new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        Intent intent = new Intent(CategoryUserDetailsActivity.this, UserProfileActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
        final Button btCitizen = findViewById(R.id.btCategoryCitizenEdit);
        final Button btNavigator = findViewById(R.id.btCategoryNavigatorEdit);
        final Button btEcosystem = findViewById(R.id.btCategoryEcosystemEdit);

        if (userSingleton.getUser().getCategory().equals(getString(R.string.citoyen))) {
            btCitizen.setSelected(true);
        }

        if (userSingleton.getUser().getCategory().equals(getString(R.string.navigateur))) {
            btNavigator.setSelected(true);
        }

        if (userSingleton.getUser().getCategory().equals(getString(R.string.ecosysteme))) {
            btEcosystem.setSelected(true);
        }

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
