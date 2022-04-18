package com.example.week3exercise.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.week3exercise.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private int Calorie = 0;
    Button btnIntake = null;
    Button btnBurn = null;
    Button btnClear = null;
    EditText etIntake = null;
    EditText etBurn = null;
    TextView tvCalories = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calorie = 0;


        TextView tvInfo = findViewById(R.id.tv_info);
        Button btnLogin = findViewById(R.id.btn_login);
        Button btnLogOut = findViewById(R.id.btn_logout);
        btnIntake = findViewById(R.id.btn_intake);
        btnBurn = findViewById(R.id.btn_burn);
        btnClear = findViewById(R.id.btn_clear);
        etIntake = findViewById(R.id.et_intake);
        etBurn = findViewById(R.id.et_burn);
        tvCalories = findViewById(R.id.tv_calories);


        Intent intent = getIntent();
        boolean isLogin = intent.getBooleanExtra("isLogin", false);
        if (isLogin) {
            btnLogin.setVisibility(View.GONE);
            btnLogOut.setVisibility(View.VISIBLE);
            tvInfo.setText(getDateString() + intent.getStringExtra("username"));
        } else {
            btnLogin.setVisibility(View.VISIBLE);
            btnLogOut.setVisibility(View.GONE);
            tvInfo.setText(getDateString() + "Guest");
        }
    }


    public void intentLoginClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void intentLogOutClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("isLogin", false);
        startActivity(intent);
    }

    public String getDateString() {
        String dateString = "";

        Calendar c = Calendar.getInstance();

        int s = c.get(Calendar.HOUR_OF_DAY);
        if (s >= 6 && s < 12) {
            dateString = "Good Morning, ";
        } else if (s >= 12 && s < 18) {
            dateString = "Good Afternoon, ";
        } else if (s >= 18 && s < 22) {
            dateString = "Good Evening, ";
        } else {
            dateString = "Good Night, ";
        }
        return dateString;
    }

    public void intakeClick(View view) {
        if (!etIntake.getText().toString().equals("")) {
            Calorie += Integer.parseInt(etIntake.getText().toString());
            etIntake.setText("");
        }
        setCalorieText(Calorie);
    }

    public void burnClick(View view) {
        if(!etBurn.getText().toString().equals("")) {
            Calorie -= Integer.parseInt(etBurn.getText().toString());
            etBurn.setText("");
        }
        setCalorieText(Calorie);
    }

    public void clearClick(View view) {
        Calorie = 0;
        setCalorieText(Calorie);
    }

    public void setCalorieText(int calorie) {
        tvCalories.setText(String.valueOf(calorie));
    }

}