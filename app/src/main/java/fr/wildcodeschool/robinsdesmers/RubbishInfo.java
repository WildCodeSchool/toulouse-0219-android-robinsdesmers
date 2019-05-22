package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class RubbishInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rubbish_info);

        Button buttonD = findViewById(R.id.btDéchetSeul);
        Button buttonA = findViewById(R.id.btAmas);
        Button buttonC = findViewById(R.id.btLieu);
        CheckBox checkBoxB = findViewById(R.id.cbBouteille);
        CheckBox checkBoxC = findViewById(R.id.cbCarton);
        CheckBox checkBoxMa = findViewById(R.id.cbMetal);
        CheckBox checkBoxM = findViewById(R.id.cbMegot);
        CheckBox checkBoxP = findViewById(R.id.cbPlastique);
        CheckBox checkBoxV = findViewById(R.id.cbVerre);
        Button buttonBack = findViewById(R.id.btBack);



        checkBoxB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rubbish.setRubbishType2("Bouteille seule");
            }
        });
        checkBoxC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rubbish.setRubbishType2("Carton");
            }
        });
        checkBoxMa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rubbish.setRubbishType2("Métal");
            }
        });
        checkBoxM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rubbish.setRubbishType2("Mégot");
            }
        });
        checkBoxP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rubbish.setRubbishType2("Plastique");
            }
        });
        checkBoxV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rubbish.setRubbishType2("Verre");
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rubbish.setRubbishType("Déchet seul");
            }
        });
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rubbish.setRubbishType("Amas de déchets");
            }
        });
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //rubbish.setRubbishType("Point de collecte");
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RubbishInfo.this,MapsActivity.class);
                startActivity(intent);
            }
        });

    }
}
