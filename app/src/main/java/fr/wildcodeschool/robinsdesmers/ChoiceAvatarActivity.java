package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class AvatarChoiceActivity extends AppCompatActivity {

    ImageButton imBtNext, imBtPrevious;
    ImageSwitcher imageSwitcher;
    Integer[] images = {R.drawable.ic_essai_avatar, R.drawable.icon_cat_v1, R.drawable.icon_cat_v2, R.drawable.icon_cat_v3};
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_avatar);

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

        imageSwitcher.setImageResource(images[i]);

        imBtPrevious = findViewById(R.id.imBtPrevious);
        imBtNext = findViewById(R.id.imBtNext);

        imBtPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i > 0) {
                    i--;
                    imageSwitcher.setInAnimation(avatar_in);
                    imageSwitcher.setImageResource(images[i]);
                }
            }

        });

        imBtNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (i < images.length - 1) {
                    i++;
                    imageSwitcher.setInAnimation(avatar_out);
                    imageSwitcher.setImageResource(images[i]);
                }
            }
        });
        ImageButton imBtAvatarChoice = findViewById(R.id.imBtAvatarChoice);

        imBtAvatarChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMaps = new Intent(AvatarChoiceActivity.this, MapsActivity.class);
                startActivity(goToMaps);
            }
        });
    }
}
