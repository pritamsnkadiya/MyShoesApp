package com.example.jharwalshoes.myshoes.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jharwalshoes.myshoes.R;
import com.example.jharwalshoes.myshoes.init.SessionManager;

public class SplashActivity extends AppCompatActivity {
    public Handler handler;
    // Session Manager Class
    public SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        session = new SessionManager(getApplicationContext());
        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
             /*   Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();*/

                if (session.isLoggedIn()) {

                    if (session.getUserDetails().get("email").equalsIgnoreCase("7206301203")) {  //Pc Sir
                        Intent intent = new Intent(SplashActivity.this, AdminDashboardActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    }
                    if (session.getUserDetails().get("email").equalsIgnoreCase("8619563505")) { // Jharwal
                        Intent intent = new Intent(SplashActivity.this, AdminDashboardActivity.class);
                        startActivity(intent);
                        finish();
                        return;
                    }
              /*  if (session.getUserDetails().get("email").equalsIgnoreCase("8319139155")) { // Payare
                      Intent intent = new Intent(SplashActivity.this, AdminDashboardActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }*/
               /* if (session.getUserDetails().get("email").equalsIgnoreCase("9685830848")) { // Pc Sir
                     Intent intent = new Intent(SplashActivity.this, AdminDashboardActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }*/
                    Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 1500);
    }
}
