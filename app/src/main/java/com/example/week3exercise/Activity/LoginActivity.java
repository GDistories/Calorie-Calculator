package com.example.week3exercise.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.week3exercise.R;

public class LoginActivity extends AppCompatActivity {
    int Counter = 5;
    private Button Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText Name = findViewById(R.id.et_username);
        EditText Password = this.findViewById(R.id.et_password);
        Login = this.findViewById(R.id.btn_login);
        TextView Info = this.findViewById(R.id.tv_info);
        

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Name.getText().toString().equals(getResources().getString(R.string.account)) &&
                        Password.getText().toString().equals(getResources().getString(R.string.password))) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", Name.getText().toString());
                    intent.putExtra("isLogin", true);
                    startActivity(intent);
                } else {
                    Counter--;
                    Info.setText(getResources().getString(R.string.login_failed) + "\nNo of attempts remaining: " + Counter);
                    if (Counter == 0) {
                        if (mCountDownTimer != null) {
                            Login.setEnabled(false);
                            Info.setText("Your login has failed too many times. \nPlease wait 5 seconds and try again!");
                            Counter = 1;
                            mCountDownTimer.start();
                        }
                    }
                }
            }
        });
    }

    public void intentBackClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("isLogin", false);
        startActivity(intent);
    }

    private CountDownTimer mCountDownTimer = new CountDownTimer(5 * 1000, 1000) {
        @Override
        public void onTick(long l) {
            if (Login != null) {
                Login.setText((l / 1000L) + " Seconds Remaining");
            }
        }

        @Override
        public void onFinish() {
            if (Login != null) {
                Login.setEnabled(true);
                Login.setText("Login");
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        Login = null;
    }



}