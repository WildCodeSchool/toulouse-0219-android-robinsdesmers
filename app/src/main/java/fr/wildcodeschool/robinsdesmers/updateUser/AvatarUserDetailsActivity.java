package fr.wildcodeschool.robinsdesmers.updateUser;

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

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserProfileActivity;
import fr.wildcodeschool.robinsdesmers.UserSingleton;
import fr.wildcodeschool.robinsdesmers.VolleySingleton;
import fr.wildcodeschool.robinsdesmers.model.User;

import static fr.wildcodeschool.robinsdesmers.inscription.AvatarChoicesActivity.avatarList;

public class AvatarUserDetailsActivity extends AppCompatActivity {

    ImageButton imBtNext, imBtPrevious;
    ImageSwitcher imageSwitcher;
    int index = 0;
    private UserSingleton userSingleton = UserSingleton.getUserInstance();
    private final Long userId = userSingleton.getUser().getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_user_details);

        imageSwitcher = findViewById(R.id.imSwitcherAvatarsEdit);

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

        final Animation avatarIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.avatar_in);
        final Animation avatarOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.avatar_out);

        index = userSingleton.getUser().getAvatar();
        imageSwitcher.setImageResource(avatarList.get(index));
        imBtPrevious = findViewById(R.id.imBtPreviousEdit);
        imBtNext = findViewById(R.id.imBtNextEdit);

        imBtPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (index > 0) {
                    index--;
                    imageSwitcher.setInAnimation(avatarIn);
                    imageSwitcher.setImageResource(avatarList.get(index));
                }
            }
        });

        imBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (index < avatarList.size() - 1) {
                    index++;
                    imageSwitcher.setInAnimation(avatarOut);
                    imageSwitcher.setImageResource(avatarList.get(index));
                }
            }
        });

        ImageButton imBtAvatarChoice = findViewById(R.id.imBtEditAvatarUser);
        imBtAvatarChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userSingleton.getUser().setAvatar(index);
                VolleySingleton.getInstance(AvatarUserDetailsActivity.this).updateUser(userId, userSingleton.getUser(), new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        Intent intent = new Intent(AvatarUserDetailsActivity.this, UserProfileActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}
