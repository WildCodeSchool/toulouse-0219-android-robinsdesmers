package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fr.wildcodeschool.robinsdesmers.MapsActivity;
import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.model.CollectPointItem;
import fr.wildcodeschool.robinsdesmers.model.RubbishItem;
import fr.wildcodeschool.robinsdesmers.model.User;

public class MarkerTypeActivity extends AppCompatActivity {

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
        final User user = new User(1l, "Seb", "Dubois", "", "", "male", "14/06/83", "31 - Haute-Garonne", "Citoyen", "Labuse", "Un bon belge une fois", R.drawable.ic_essai_avatar, 0);

        btOneRubbish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setTitle(getString(R.string.dechet_seul));
                Intent intent = new Intent(MarkerTypeActivity.this, RubbishInfosActivity.class);
                intent.putExtra("RubbishItem", rubbishItem);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });
        btMultiRubbish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rubbishItem.setTitle(getString(R.string.amas_de_dechets));
                Intent intent = new Intent(MarkerTypeActivity.this, RubbishMultiInfosActivity.class);
                intent.putExtra("RubbishItem", rubbishItem);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });
        btCollectPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarkerTypeActivity.this, CollectPointInfosActivity.class);
                intent.putExtra("CollectPointItem", collectPointItem);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        });
        btBackToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarkerTypeActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
