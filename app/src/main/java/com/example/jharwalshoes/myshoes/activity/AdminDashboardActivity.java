package com.example.jharwalshoes.myshoes.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jharwalshoes.myshoes.R;

public class AdminDashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = AdminDashboardActivity.class.getName();
    public ImageView iv_back;
    public TextView activity_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        iv_back = findViewById(R.id.iv_back);
        activity_name = findViewById(R.id.activity_name);

        iv_back.setOnClickListener(this);

        iv_back.setVisibility(View.VISIBLE);
        activity_name.setVisibility(View.VISIBLE);
        activity_name.setText("Admin Panel");
    }

    @Override
    public void onClick(View v) {

    }
}
