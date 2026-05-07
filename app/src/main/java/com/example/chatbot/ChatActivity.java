package com.example.chatbot;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.example.chatbot.model.ChatRequest;
import com.example.chatbot.model.ChatResponse;
import com.example.chatbot.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    TextView tvWelcome;
    RecyclerView rvMessages;
    EditText etMessage;
    Button btnSend;

    ArrayList<chats> messageList;
    MessageAdapter messageAdapter;
    AppDatabase db;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        tvWelcome = findViewById(R.id.tvWelcome);
        rvMessages = findViewById(R.id.rvMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        username = getIntent().getStringExtra("username");
        tvWelcome.setText("Welcome, " + username);

        db = AppDatabase.getInstance(this);

        messageList = new ArrayList<>();
        messageList.addAll(db.chatDao().getChatsByUsername(username));

        messageAdapter = new MessageAdapter(messageList);

        rvMessages.setLayoutManager(new LinearLayoutManager(this));
        rvMessages.setAdapter(messageAdapter);

        if (!messageList.isEmpty()) {
            rvMessages.scrollToPosition(messageList.size() - 1);
        }


        btnSend.setOnClickListener(v -> {
            String text = etMessage.getText().toString().trim();

            if (!text.isEmpty()) {

                chats chat = new chats(
                        username,
                        text,
                        "user",
                        getCurrentTime()
                );
                db.chatDao().insertChat(chat);
                messageList.add(chat);
                messageAdapter.notifyDataSetChanged();

                etMessage.setText("");

                rvMessages.scrollToPosition(messageList.size() - 1);
                sendMessageToBot(text);
            }
        });
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }

    private void sendMessageToBot(String userText) {
        ChatRequest request = new ChatRequest(userText);

        RetrofitClient.getApiService().sendMessage(request).enqueue(new Callback<ChatResponse>() {
            @Override
            public void onResponse(Call<ChatResponse> call, Response<ChatResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String botReply = response.body().getReply();

                    chats botChat = new chats(
                            username,
                            botReply,
                            "bot",
                            getCurrentTime()
                    );

                    db.chatDao().insertChat(botChat);
                    int position = messageList.size();
                    messageList.add(botChat);
                    messageAdapter.notifyItemInserted(position);
                    rvMessages.scrollToPosition(position);
                }
            }

            @Override
            public void onFailure(Call<ChatResponse> call, Throwable t) {
                chats errorChat = new chats(
                        username,
                        "Failed to connect to chatbot.",
                        "bot",
                        getCurrentTime()
                );

                int position = messageList.size();
                messageList.add(errorChat);
                messageAdapter.notifyItemInserted(position);
                rvMessages.scrollToPosition(position);
            }
        });
    }
}