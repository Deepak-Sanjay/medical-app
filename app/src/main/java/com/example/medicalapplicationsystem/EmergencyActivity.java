package com.example.medicalapplicationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmergencyActivity extends AppCompatActivity {
    EditText phoneNumberEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency2);
        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber);
        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberEditText.getText().toString();

                // Validate input if needed
                if (phoneNumber.length() == 10) {
                    DatabaseHelper dbHelper = new DatabaseHelper(EmergencyActivity.this);
                    if (dbHelper.isEmergencyContactExists()) {
                        // If emergency contact exists, update it
                        if (dbHelper.updateEmergencyContact(phoneNumber)) {
                            Toast.makeText(EmergencyActivity.this, "Emergency contact updated!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EmergencyActivity.this, "Failed to update emergency contact!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // If emergency contact doesn't exist, insert it
                        if (dbHelper.addEmergencyContact(phoneNumber) != -1) {
                            Toast.makeText(EmergencyActivity.this, "Emergency contact added!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EmergencyActivity.this, "Failed to add emergency contact!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(EmergencyActivity.this, " Contact Must be 10 digit", Toast.LENGTH_SHORT).show();

                }
            }

        });

    }

    public void goback(View view) {
        onBackPressed();
    }
}