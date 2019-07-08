package fr.wildcodeschool.robinsdesmers.updateUser;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.util.Consumer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import fr.wildcodeschool.robinsdesmers.R;
import fr.wildcodeschool.robinsdesmers.UserProfileActivity;
import fr.wildcodeschool.robinsdesmers.UserSingleton;
import fr.wildcodeschool.robinsdesmers.VolleySingleton;
import fr.wildcodeschool.robinsdesmers.adapter.ListDepartmentAdapter;
import fr.wildcodeschool.robinsdesmers.model.Department;
import fr.wildcodeschool.robinsdesmers.model.User;

public class LessPersoDetailsActivity extends AppCompatActivity {

    private UserSingleton userSingleton = UserSingleton.getUserInstance();
    private final Long userId = userSingleton.getUser().getId();
    private String gender;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_less_perso_details);

                mDisplayDate = findViewById(R.id.dateOfBirthEdit);
                mDisplayDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar cal = Calendar.getInstance();
                        int year = Integer.parseInt(userSingleton.getUser().getDateOfBirth().substring(6, 10));
                        int month = Integer.parseInt(userSingleton.getUser().getDateOfBirth().substring(3, 5)) - 1;
                        int day = Integer.parseInt(userSingleton.getUser().getDateOfBirth().substring(0, 2));

                        DatePickerDialog dialog = new DatePickerDialog(
                                LessPersoDetailsActivity.this,
                                android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener,
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
                userSingleton.getUser().setDateOfBirth(format.format(date));
            }
        };

        RadioButton rbBtFemme = findViewById(R.id.radioBtFemaleEdit);
        RadioButton rbBtMale = findViewById(R.id.radioBtMaleEdit);

        if (userSingleton.getUser().getGender().equals("Femme")) {
            rbBtFemme.setChecked(true);
        } else {
            rbBtMale.setChecked(true);
        }

        final ArrayList<Department> departments = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(LessPersoDetailsActivity.this);
        String url = "https://geo.api.gouv.fr/departements?fields=nom";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject detailsDep = response.getJSONObject(i);
                                String name = detailsDep.getString("nom");
                                String code = detailsDep.getString("code");
                                Department department = new Department(name, code);
                                departments.add(department);
                            }
                            final RecyclerView rvListDepartments = findViewById(R.id.rvListDepartmentsEdit);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LessPersoDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
                            rvListDepartments.setLayoutManager(layoutManager);
                            final ListDepartmentAdapter adapter = new ListDepartmentAdapter(departments);
                            rvListDepartments.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Afficher l'erreur
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);

        ImageButton btSendEdit = findViewById(R.id.imBtEditLessPerso);
        btSendEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleySingleton.getInstance(LessPersoDetailsActivity.this).updateUser(userId, userSingleton.getUser(), new Consumer<User>() {
                    @Override
                    public void accept(User user) {
                        Intent intent = new Intent(LessPersoDetailsActivity.this, UserProfileActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void buttonchecked(final View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioBtFemaleEdit:
                if (checked)
                    gender = getString(R.string.femme);
                userSingleton.getUser().setGender(gender);
                break;
            case R.id.radioBtMaleEdit:
                if (checked)
                    gender = getString(R.string.homme);
                userSingleton.getUser().setGender(gender);
                break;
        }
    }
}
