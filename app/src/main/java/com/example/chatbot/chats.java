package com.example.chatbot;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chats")
public class chats {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;
    private String messageText;
    private String sender;
    private String timestamp;

    public chats(String username, String messageText, String sender, String timestamp) {
        this.username = username;
        this.messageText = messageText;
        this.sender = sender;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getSender() {
        return sender;
    }

    public String getTimestamp() {
        return timestamp;
    }
}