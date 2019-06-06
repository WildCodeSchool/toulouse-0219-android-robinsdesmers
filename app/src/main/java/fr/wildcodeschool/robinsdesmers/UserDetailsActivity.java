package fr.wildcodeschool.robinsdesmers;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UserDetailsActivity extends AppCompatActivity {

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
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
                Date date = calendar.getTime();
                mDisplayDate.setText(format.format(date));
            }
        };

        ArrayList<Department> departments = new ArrayList<>();
        Department hauteGaronne = new Department("Haute-Garonne", 31);
        Department correze = new Department("Corrèze", 19);
        Department charente = new Department("Charente-Maritime", 17);
        Department gers = new Department("Gers", 32);
        Department hauteSavoie = new Department("Haute Savoie", 85);
        Department vendee = new Department("Vendée", 31);
        Department vaucluse = new Department("Vaucluse", 84);
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

        ImageButton btSend = findViewById(R.id.imBtRegister2);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetailsActivity.this, UserCategoryActivity.class);
                startActivity(intent);
            }
        });
    }
}
