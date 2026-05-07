package com.example.chatbot;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface chatDao {

    @Insert
    void insertChat(chats chat);

    @Query("SELECT * FROM chats WHERE username = :username ORDER BY id ASC")
    List<chats> getChatsByUsername(String username);
}