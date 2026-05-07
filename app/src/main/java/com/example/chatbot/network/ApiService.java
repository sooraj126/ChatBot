package com.example.chatbot.network;

import com.example.chatbot.model.ChatRequest;
import com.example.chatbot.model.ChatResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("chat")
    Call<ChatResponse> sendMessage(@Body ChatRequest request);
}