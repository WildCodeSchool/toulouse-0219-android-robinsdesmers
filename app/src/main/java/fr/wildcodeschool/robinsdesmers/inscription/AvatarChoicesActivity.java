package fr.wildcodeschool.robinsdesmers.inscription;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.Arrays;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserSingleton;
import fr.wildcodeschool.robinsdesmers.VolleySingleton;
import fr.wildcodeschool.robinsdesmers.model.User;

public class AvatarChoicesActivity extends AppCompatActivity {
    ImageButton imBtNext, imBtPrevious;
    ImageSwitcher imageSwitcher;
    public static ArrayList<Integer> avatarList = new ArrayList<>(Arrays.asList(R.drawable.persohero, R.drawable.persoheroine, R.drawable.persomarin, R.drawable.perso_fille_bonnet,R.drawable.perso_garcon_meche));
    public static ArrayList<Integer> avatarHeadList = new ArrayList<>(Arrays.asList(R.drawable.tete_hero, R.drawable.tete_heroine, R.drawable.tete_marin, R.drawable.tete_fille_bonnet_map,R.drawable.tete_garcon_meche_map));
    int index = 0;
    private UserSingleton userSingleton = UserSingleton.getUserInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choices_avatar);

        imageSwitcher = findViewById(R.id.imSwitcherAvatars);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });

        final Animation avatar_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.avatar_in);
        final Animation avatar_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.avatar_out);

        imageSwitcher.setImageResource(avatarList.get(index));
        imBtPrevious = findViewById(R.id.imBtPrevious);
        imBtNext = findViewById(R.id.imBtNext);

        imBtPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (index > 0) {
                    index--;
                    imageSwitcher.setInAnimation(avatar_in);
                    imageSwitcher.setImageResource(avatarList.get(index));
                }
            }
        });

        imBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (index < avatarList.size() - 1) {
                    index++;
                    imageSwitcher.setInAnimation(avatar_out);
                    imageSwitcher.setImageResource(avatarList.get(index));
                }
            }
        });
        ImageButton imBtAvatarChoice = findViewById(R.id.imBtAvatarChoice);

        imBtAvatarChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSingleton.getUser().setAvatar(index);
                VolleySingleton.getInstance(AvatarChoicesActivity.this).postUser(userSingleton.getUser(), new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        UserSingleton.getUserInstance().setUser(user);
                        Intent goToHome = new Intent(AvatarChoicesActivity.this, MapsActivity.class);
                        startActivity(goToHome);
                    }
                });
            }
        });
    }
}
