package fr.wildcodeschool.robinsdesmers.rubbish_collect_point;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.model.CollectPoint;

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
                    TextView tvDate = findViewById(R.id.tvDateCollectPoint);
                    TextView tvName = findViewById(R.id.tvNameCollectPoint);
                    TextView tvDescription = findViewById(R.id.tvDescCollectPoint);
                    tvDate.setText(date);
                    tvName.setText(name);
                    tvDescription.setText(description);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
