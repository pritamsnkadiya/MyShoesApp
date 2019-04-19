package com.example.jharwalshoes.myshoes.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jharwalshoes.myshoes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    public Button new_registration;
    public EditText name, mobile_no;
    public DatabaseReference rootRef, demoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();
        //database reference pointing to demo node
        demoRef = rootRef.child("users");

        new_registration = findViewById(R.id.new_registration);
        name = findViewById(R.id.name);
        mobile_no = findViewById(R.id.mobile_no);

        new_registration.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_registration:

                long userCount = System.currentTimeMillis();
                String identifier = "Users" + userCount;

                String mobile = mobile_no.getText().toString().trim();
                String user_name = name.getText().toString().trim();

                if (mobile.isEmpty() || mobile.length() < 10) {
                    mobile_no.setError("Enter a valid mobile");
                    mobile_no.requestFocus();
                    return;
                }
                if (user_name.isEmpty()) {
                    name.setError("Enter User Name");
                    name.requestFocus();
                    return;
                }
                //create user push creates a unique id in database
             /*   demoRef.child("user_name").setValue(user_name);
                demoRef.child("mobile_no").setValue(mobile);*/

                Map<String, String> user_data = new HashMap<>();
                user_data.put("user_name", user_name);
                user_data.put("mobile", mobile);

                Map<String, Map<String, String>> users = new HashMap<>();
                users.put(identifier, user_data);

                demoRef.setValue(users);

                Intent intent = new Intent(SignUpActivity.this, VerifyPhoneActivity.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
                finish();
                break;
        }
    }
}
