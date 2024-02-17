package com.example.medicalapplicationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChatbotInsert extends AppCompatActivity {
    EditText questionEditText, answerEditText;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot_insert);
        dbHelper = new DatabaseHelper(this);

        questionEditText = findViewById(R.id.editTextQuestion);
        answerEditText = findViewById(R.id.editTextAnswer);

        Button addButton = findViewById(R.id.buttonSave);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = questionEditText.getText().toString();
                String answer = answerEditText.getText().toString();

                // Validate input if needed
                if(question.length()==00||answer.length()==00){
                    Toast.makeText(ChatbotInsert.this, "Enter All Fields!", Toast.LENGTH_SHORT).show();

                }
                else{
                    long result = dbHelper.addChatbotEntry(question, answer);
                    if (result != -1) {
                        Toast.makeText(ChatbotInsert.this, "Entry added successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ChatbotInsert.this, "Failed to add entry!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    public void goback(View view) {
        onBackPressed();
    }
}