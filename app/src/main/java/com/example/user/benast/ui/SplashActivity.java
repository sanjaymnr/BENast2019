package com.example.user.benast.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.user.benast.R;
import com.example.user.benast.contracts.AppContract;
import com.example.user.benast.data.local.local.SharedPreferenceManager;


public class SplashActivity extends AppCompatActivity {
    SharedPreferenceManager sharedPreferenceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferenceManager = new SharedPreferenceManager(this);
        if (sharedPreferenceManager.getBoolValues(AppContract.Preferences.IS_LOGGED_IN)) {
            finish();
//            AsyncActivity.start(this);
            DatabaseActivity.start(this);
        } else {
            finish();
            LandingActivity.start(this);
        }
    }
}
