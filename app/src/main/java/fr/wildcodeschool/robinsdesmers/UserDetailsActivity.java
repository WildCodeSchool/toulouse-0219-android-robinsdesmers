package fr.wildcodeschool.robinsdesmers;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class UserDetailsActivity extends AppCompatActivity {

    private static final String TAG = "SecondInscripiton";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        mDisplayDate = findViewById(R.id.dateOfBirth);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        UserDetailsActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "OnDataSet: date " + dayOfMonth + "/" + month + "/" + year);
                String date = dayOfMonth + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        ArrayList<Departments> departments = new ArrayList<>();
        Departments hauteGaronne = new Departments("Haute-Garonne", 31);
        Departments correze = new Departments("Corrèze", 19);
        Departments charente = new Departments("Charente-Maritime", 17);
        Departments gers = new Departments("Gers", 32);
        Departments hauteSavoie = new Departments("Haute Savoie", 85);
        Departments vendee = new Departments("Vendée", 31);
        Departments vaucluse = new Departments("Vaucluse", 84);
        departments.add(hauteGaronne);
        departments.add(correze);
        departments.add(charente);
        departments.add(gers);
        departments.add(hauteSavoie);
        departments.add(vendee);
        departments.add(vaucluse);

        RecyclerView rvListDepartments = findViewById(R.id.rvListDepartments);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvListDepartments.setLayoutManager(layoutManager);
        final ListDepartmentAdapter adapter = new ListDepartmentAdapter(departments);
        rvListDepartments.setAdapter(adapter);
    }
}
