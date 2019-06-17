package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class ScreenSplashActivity extends AppCompatActivity {
    final int MILLIS = 2000;
    final int ROTATION_START = 0;
    final int ROTATION_END = 300;
    final int DURATION = 1300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_splash);

        ImageView imageView = findViewById(R.id.ivScreenImg);
        RotateAnimation rotate = new RotateAnimation(ROTATION_START, ROTATION_END);
        rotate.setDuration(DURATION);
        imageView.startAnimation(rotate);

        Thread myThread = new Thread() {

            public void run() {
                try {
                    sleep(MILLIS);
                    Intent intent = new Intent(ScreenSplashActivity.this, FirstPageActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
