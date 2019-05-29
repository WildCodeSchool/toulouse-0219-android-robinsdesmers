package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class CollectRubbishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_rubbish);

        Intent intent = getIntent();
        final RubbishMarkers location = intent.getParcelableExtra("RubbishMarkers");

        CheckBox cbCollect = findViewById(R.id.cbIsCollected);
        Button btSend = findViewById(R.id.btSend4);
        cbCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference markersRef = database.getReference();
                markersRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot markerSnapshot : dataSnapshot.getChildren()) {
                            RubbishMarkers locationMarker = markerSnapshot.getValue(RubbishMarkers.class);
                            String key = locationMarker.getKey();

                            markersRef.child("RubbishMarkers").child(key).child("isCollected").setValue("true");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectRubbishActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
