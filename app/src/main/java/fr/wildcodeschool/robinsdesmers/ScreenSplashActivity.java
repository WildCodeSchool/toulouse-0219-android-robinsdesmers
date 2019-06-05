package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import gr.net.maroulis.library.EasySplashScreen;

public class ScreenSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_splash);

        VideoView vidview = (VideoView)findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.logo;
        vidview.setVideoURI(Uri.parse(path));
        vidview.start();

        Thread myThread = new Thread() {

            public void run() {
                try {
                    sleep(3000);
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
