package com.example.medicalapplicationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText nameEditText, emailEditText, passwordEditText, mobileEditText;
    TextView signtext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameEditText = findViewById(R.id.editTextName);
        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        mobileEditText = findViewById(R.id.editTextMobile);
        signtext = findViewById(R.id.signtext);
        signtext.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivities(new Intent[]{intent});
        });
        Button registerButton = findViewById(R.id.loginbutton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String mobile = mobileEditText.getText().toString();

                // Validate inputs if needed

                DatabaseHelper dbHelper = new DatabaseHelper(Register.this);
                long rowId = dbHelper.addUser(name, email, password, mobile);
                if (rowId != -1) {
                    Toast.makeText(Register.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivities(new Intent[]{intent});
                } else {
                    Toast.makeText(Register.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                    // Handle failure
                }
            }
        });
    }
}