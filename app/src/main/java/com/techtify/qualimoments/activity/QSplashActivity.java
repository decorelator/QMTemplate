package com.techtify.qualimoments.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.techtify.qualimoments.R;

public class QSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qsplash);

        startActivity(new Intent(this, QLoginActivity.class));
        finish();
    }
}
