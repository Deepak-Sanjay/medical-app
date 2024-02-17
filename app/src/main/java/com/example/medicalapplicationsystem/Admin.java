package com.example.medicalapplicationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void gotoemp(View view) {
        Intent intent = new Intent(this, EmergencyActivity.class);
        startActivities(new Intent[]{intent});
    }

    public void gotofak(View view) {
        Intent intent = new Intent(this, ChatbotInsert.class);
        startActivities(new Intent[]{intent});
    }

    public void goback(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivities(new Intent[]{intent});
    }
}