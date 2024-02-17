package com.example.medicalapplicationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Firstaid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstaid);

        CardView snakebite = findViewById(R.id.snakebitecard);
        snakebite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                opensnakebite();
            }
        });
    }

            private void opensnakebite() {
                Intent intent = new Intent(this, snakebite.class);
                startActivities(new Intent[]
                        {
                                intent
                        });

            }


}