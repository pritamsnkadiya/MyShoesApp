package com.example.jharwalshoes.myshoes.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.jharwalshoes.myshoes.BuildConfig;
import com.example.jharwalshoes.myshoes.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public LinearLayout new_registration;
    public Button login;
    public EditText mobile_no;
    private static String TAG = LoginActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (!checkPermissions()) {
            requestPermissions();
        }

        new_registration = findViewById(R.id.new_registration);
        login = findViewById(R.id.login);
        mobile_no = findViewById(R.id.mobile_no);

        new_registration.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_registration:
                Intent new_registration = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(new_registration);
                finish();
                break;

            case R.id.login:
                String mobile = mobile_no.getText().toString().trim();
                if (mobile.isEmpty() || mobile.length() < 10) {
                    mobile_no.setError("Enter a valid mobile");
                    mobile_no.requestFocus();
                    return;
                }

                if (mobile.equalsIgnoreCase("7206301203")) {
                    Intent login = new Intent(LoginActivity.this, VerifyAdminPhoneActivity.class);
                    login.putExtra("mobile", mobile);
                    startActivity(login);
                    return;
                }
                if (mobile.equalsIgnoreCase("8619563505")) {
                    Intent login = new Intent(LoginActivity.this, VerifyAdminPhoneActivity.class);
                    login.putExtra("mobile", mobile);
                    startActivity(login);
                    return;
                }
              /*  if (mobile.equalsIgnoreCase("8319139155")) {
                    Intent login = new Intent(LoginActivity.this, VerifyAdminPhoneActivity.class);
                    login.putExtra("mobile", mobile);
                    startActivity(login);
                    return;
                }*/
               /* if (mobile.equalsIgnoreCase("9685830848")) {
                    Intent login = new Intent(LoginActivity.this, VerifyAdminPhoneActivity.class);
                    login.putExtra("mobile", mobile);
                    startActivity(login);
                    return;
                }*/
                Intent login = new Intent(LoginActivity.this, VerifyPhoneActivity.class);
                login.putExtra("mobile", mobile);
                startActivity(login);
                finish();
                break;
        }
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.CAMERA);
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");
            Snackbar.make(
                    findViewById(R.id.activity_login),
                    "Camera permission is needed for core functionality",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            ActivityCompat.requestPermissions(LoginActivity.this,
                                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_SMS},
                                    100);
                        }
                    })
                    .show();
        } else {
            Log.i(TAG, "Requesting permission");
            ActivityCompat.requestPermissions(LoginActivity.this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_SMS},
                    100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        Log.i(TAG, "onRequestPermissionResult");
        if (requestCode == 100) {
            if (grantResults.length <= 0) {
                Log.i(TAG, "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                Snackbar.make(
                        findViewById(R.id.activity_login),
                        "Camera permission is needed for core functionality",
                        Snackbar.LENGTH_INDEFINITE)
                        .setAction("Setting", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // Build intent that displays the App settings screen.
                                Intent intent = new Intent();
                                intent.setAction(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package",
                                        BuildConfig.APPLICATION_ID, null);
                                intent.setData(uri);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        }
    }
}
