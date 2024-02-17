package com.example.medicalapplicationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_new);
    }

    public void gotoemp(View view) {
        Intent intent = new Intent(this,Emergency.class);
        startActivities(new Intent[]{intent} );
    }

    public void gotofak(View view) {
        Intent intent = new Intent(this, Firstaid.class);
        startActivity(intent);
    }

    public void gotonews(View view) {
        Intent intent = new Intent(this, News.class);
        startActivity(intent);
    }

    public void gotochatbot(View view) {
        Intent intent = new Intent(this, ChatbotActivity.class);
        startActivity(intent);
    }
}