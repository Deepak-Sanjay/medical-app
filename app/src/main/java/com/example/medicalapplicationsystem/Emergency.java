package com.example.medicalapplicationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Emergency extends AppCompatActivity {

    Button ambubutton;
    Button policebutton;
    Button smsbutton,empbutton;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        ambubutton = findViewById(R.id.ambubutton);
        policebutton = findViewById(R.id.policebutton);
        smsbutton = findViewById(R.id.smsbutton);
        empbutton = findViewById(R.id.empbutton);

        empbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                String emergencyPhoneNumber = dbHelper.getEmergencyPhoneNumber();
                Toast.makeText(Emergency.this,"Calling Emergency PhoneNumber",Toast.LENGTH_SHORT).show();
                if (emergencyPhoneNumber != null) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+emergencyPhoneNumber));
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Emergency.this,"No Emergency Phone Number Saved",Toast.LENGTH_SHORT).show();

                }

            }
        }
        );
        ambubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Toast.makeText(Emergency.this,"Calling Emergency PhoneNumber",Toast.LENGTH_SHORT).show();



                    String number = "108";
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+number));
                    startActivity(intent);


            }
        }
        );

        policebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Emergency.this,"Calling POLICE 100",Toast.LENGTH_SHORT).show();
                String number = "100";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+number));
                startActivity(intent);
            }
        });

        smsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Emergency.this,"SENDING SMS WITH LOCATION",Toast.LENGTH_SHORT).show();
                String sms="Google Live Location";
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("sms:"+sms));
                startActivity(intent);
            }
        });
    }
}