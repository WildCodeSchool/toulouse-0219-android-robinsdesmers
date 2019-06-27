package fr.wildcodeschool.robinsdesmers.inscription;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import fr.wildcodeschool.robinsdesmers.UserSingleton;
import fr.wildcodeschool.robinsdesmers.adapter.ListDepartmentAdapter;
import fr.wildcodeschool.robinsdesmers.model.Department;


public class UserDetailsActivity extends AppCompatActivity {

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private String gender;
    private UserSingleton userSingleton = UserSingleton.getUserInstance();

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
                userSingleton.getUser().setDateOfBirth(format.format(date));
            }
        };
        final ArrayList<Department> departments = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(UserDetailsActivity.this);
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
                            final RecyclerView rvListDepartments = findViewById(R.id.rvListDepartments);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UserDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
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

        ImageButton btSend = findViewById(R.id.imBtRegister2);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userSingleton.getUser().getGender().isEmpty() || userSingleton.getUser().getDateOfBirth().isEmpty()
                        || userSingleton.getUser().getDepartment().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserDetailsActivity.this);
                    builder.setTitle(R.string.merci_de);
                    builder.setMessage(R.string.remplir);
                    builder.setPositiveButton(R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Intent intent = new Intent(UserDetailsActivity.this, UserCategoryActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void buttonchecked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioBtFemale:
                if (checked)
                    gender = getString(R.string.femme);
                userSingleton.getUser().setGender(gender);
                break;
            case R.id.radioBtMale:
                if (checked)
                    gender = getString(R.string.homme);
                userSingleton.getUser().setGender(gender);
                break;
        }
    }
}
