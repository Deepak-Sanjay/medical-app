package com.example.medicalapplicationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChatbotActivity extends AppCompatActivity {
    private EditText editTextQuestion;
    private RecyclerView recyclerViewChat;
    private ChatbotAdapter adapter;
    private List<ChatMessage> chatMessages;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        editTextQuestion = findViewById(R.id.editTextQuestion);
        recyclerViewChat = findViewById(R.id.recyclerViewChat);
        recyclerViewChat.setLayoutManager(new LinearLayoutManager(this));

        chatMessages = new ArrayList<>();
        adapter = new ChatbotAdapter(chatMessages);
        recyclerViewChat.setAdapter(adapter);

        dbHelper = new DatabaseHelper(this);
    }

    public void goback(View view) {
        onBackPressed();
    }
    public void submitQuestion(View view) {
        String question = editTextQuestion.getText().toString().trim();
        if (!question.isEmpty()) {
            // Add user's question to chat history
            chatMessages.add(new ChatMessage(question, true));

            // Get the chatbot's answer from the database
            String answer = dbHelper.getAnswerForQuestion(question);
            if (answer != null) {
                // Add chatbot's answer to chat history
                chatMessages.add(new ChatMessage(answer, false));
            } else {
                // No answer found for the question
                chatMessages.add(new ChatMessage("Sorry, I couldn't understand your question.", false));
            }

            // Notify adapter of changes
            adapter.notifyDataSetChanged();

            // Scroll to the bottom of the chat history
            recyclerViewChat.scrollToPosition(chatMessages.size() - 1);

            // Clear the input field
            editTextQuestion.getText().clear();
        } else {
            Toast.makeText(this, "Please enter a question", Toast.LENGTH_SHORT).show();
        }
    }

}