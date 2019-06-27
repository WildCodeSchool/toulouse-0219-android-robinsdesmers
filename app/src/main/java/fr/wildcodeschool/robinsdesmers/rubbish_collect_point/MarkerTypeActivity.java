package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserSingleton;
import fr.wildcodeschool.robinsdesmers.model.CollectPointItem;
import fr.wildcodeschool.robinsdesmers.model.RubbishItem;

public class MarkerTypeActivity extends AppCompatActivity {
    final static int MARKER_POINT = 10;
    private UserSingleton userSingleton = UserSingleton.getUserInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_type);

        Button btOneRubbish = findViewById(R.id.btDéchetSeul);
        Button btMultiRubbish = findViewById(R.id.btAmas);
        Button btCollectPoint = findViewById(R.id.btLieu);
        Button btBackToMap = findViewById(R.id.btBack);

        Intent intent = getIntent();
        final CollectPointItem collectPointItem = intent.getParcelableExtra("CollectPointItem");
        final RubbishItem rubbishItem = intent.getParcelableExtra("RubbishItem");

        btOneRubbish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setTitle(getString(R.string.dechet_seul));
                Intent intent = new Intent(MarkerTypeActivity.this, RubbishInfosActivity.class);
                intent.putExtra("RubbishItem", rubbishItem);
                startActivity(intent);
            }
        });
        btMultiRubbish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setTitle(getString(R.string.amas_de_dechets));
                Intent intent = new Intent(MarkerTypeActivity.this, RubbishMultiInfosActivity.class);
                intent.putExtra("RubbishItem", rubbishItem);
                startActivity(intent);
            }
        });
        btCollectPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarkerTypeActivity.this, CollectPointInfosActivity.class);
                intent.putExtra("CollectPointItem", collectPointItem);
                startActivity(intent);
            }
        });
        btBackToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarkerTypeActivity.this, MapsActivity.class);

                userSingleton.getUser().setScore(MARKER_POINT);
                startActivity(intent);
            }
        });
    }
}
