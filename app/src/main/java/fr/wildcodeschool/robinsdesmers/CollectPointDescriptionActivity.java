package fr.wildcodeschool.robinsdesmers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CollectPointDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collect_point_description);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference markersRef = database.getReference("CollectPoint");
        markersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot markerSnapshot : dataSnapshot.getChildren()) {
                    CollectPoint collectPoint = markerSnapshot.getValue(CollectPoint.class);
                    final String date = collectPoint.getDate();
                    final String name = collectPoint.getInfoCollectPoint();
                    final String description = collectPoint.getInfoSup();
                    TextView tvDate = findViewById(R.id.tvCollect1);
                    TextView tvName = findViewById(R.id.tvCollect2);
                    TextView tvDescription = findViewById(R.id.tvCollect3);
                    tvDate.append(date);
                    tvName.append(name);
                    tvDescription.append(description);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
