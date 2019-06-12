package fr.wildcodeschool.robinsdesmers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CollectRubbishActivity extends AppCompatActivity {

    final User user = new User(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_rubbish);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference markersRef = database.getReference("RubbishMarkers");
        markersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot markerSnapshot : dataSnapshot.getChildren()) {
                    RubbishMarkers locationMarker = markerSnapshot.getValue(RubbishMarkers.class);
                    final String key = locationMarker.getKey();
                    final CheckBox cbCollect = findViewById(R.id.cbIsCollected);
                    cbCollect.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference markersRef = database.getReference("RubbishMarkers").child(key).child("isCollected");
                            markersRef.setValue(true);

                            user.setCompteur(10);
                            DatabaseReference userRef = database.getReference("User");
                            String key2 = userRef.push().getKey();
                            userRef.child(key2).setValue(user);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        Button btSend = findViewById(R.id.btSendCollect);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectRubbishActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }
}
