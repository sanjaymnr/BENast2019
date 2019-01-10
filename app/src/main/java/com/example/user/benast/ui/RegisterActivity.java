package com.example.user.benast.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.user.benast.R;
import com.example.user.benast.adapter.SpinnerAdapter;
import com.example.user.benast.helpers.ViewUtils;

import java.util.ArrayList;
import java.util.Arrays;


public class RegisterActivity extends AppCompatActivity {
    EditText etFirstName;
    EditText etLastName;
    EditText etPassword;
    EditText etEmail;
    ImageButton btnShowHidePassword;
    Context context;
    Spinner spinnerChooseSalary;

    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etFirstName = findViewById(R.id.et_firstname);
        etLastName = findViewById(R.id.et_lastname);
        etPassword = findViewById(R.id.et_sign_up_password);
        etEmail = findViewById(R.id.et_email);
        ViewUtils.setupUI(findViewById(R.id.activity_register), this);
        btnShowHidePassword = findViewById(R.id.btn_show_hide_register_password);
        btnShowHidePassword.setImageResource(R.drawable.ic_eye);
        spinnerChooseSalary = findViewById(R.id.spinner_salary);

        context = getApplicationContext();

        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String email = etEmail.getText().toString().trim();


        SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean("boolValue", true); // Storing boolean - true/false
        editor.putString("firstname", firstName); // Storing string
        editor.putString("lastname", lastName); // Storing string
        editor.putString("password", password); // Storing string
        editor.putString("email", email); // Storing String
        editor.apply(); // commit changes

        String firstname = pref.getString("firstname", ""); // getting String
        String lastname = pref.getString("lastname", ""); // getting String
        String password1 = pref.getString("password", ""); // getting String
        int email1 = pref.getInt("email", 0); // getting Integer

        Toast.makeText(this, "name: " + firstname + "value" + email1, Toast.LENGTH_SHORT).show();

        btnShowHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getTransformationMethod() == PasswordTransformationMethod.getInstance()) {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    btnShowHidePassword.setImageResource(R.drawable.ic_eye_slash);
                } else if (etPassword.getTransformationMethod() == HideReturnsTransformationMethod.getInstance()) {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    btnShowHidePassword.setImageResource(R.drawable.ic_eye);
                }
            }
        });

        ArrayList<String> salaryTypes = new ArrayList<>(Arrays.asList("All salaries", "Annual", "Hourly"));
        final SpinnerAdapter salaryAdapter = new SpinnerAdapter(context, R.layout.item_spinner, salaryTypes);
        salaryAdapter.setDropDownViewResource(R.layout.item_spinner);
        spinnerChooseSalary.setAdapter(salaryAdapter);
    }
}
