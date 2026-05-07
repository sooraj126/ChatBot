# LLM ChatBot App

## Overview
LLM ChatBot is an Android application built using Java and connected with an LLM backend. The app allows users to enter a username and chat with an AI chatbot. User and bot messages are displayed in a chat style interface and the chat history is stored locally using Room Database with username based filtering.

The app uses a Node.js backend connected to the OpenAI API to generate chatbot responses.

The app allows the user to:

- enter a username and open chat screen  
- send messages to AI chatbot  
- receive AI generated replies  
- view chat style conversation using RecyclerView  
- store chat history locally using Room Database  
- load previous chat history for the same username  

---

# Features

## Username Based Login
- user enters a username to continue  
- username is passed to chat screen using Intent  

---

## Chat Screen
- RecyclerView based chat interface  
- user messages shown on right side  
- bot messages shown on left side  
- timestamps shown for messages  
- messages added dynamically on send button click  

---

## Chat History
- chat history stored locally using Room Database  
- previous chats loaded when same username logs in again  
- different usernames have separate chat histories  

---

# Technologies Used

## Android
- Java  
- XML Layouts  
- Android Studio  
- RecyclerView  
- Room Database  
- Retrofit  

---

## Backend
- Node.js  
- Express.js  
- OpenAI API  
- dotenv  

---

# App Flow

## User Flow
1. User enters username  
2. Chat screen opens  
3. User types a message  
4. Message displayed in RecyclerView  
5. Message saved in Room Database  
6. Message sent to backend using Retrofit  
7. Backend sends prompt to OpenAI API  
8. AI response returned to Android app  
9. Bot reply displayed and stored in Room Database  

---

## Backend Flow
1. Android app sends message to `/chat` endpoint  
2. Backend receives user message  
3. Backend creates prompt  
4. Prompt sent to OpenAI API  
5. OpenAI generates response  
6. Backend returns chatbot reply to Android app  

---

# Backend Setup

```bash
cd backend
npm install
node server.js
```
In .env file replace the api key:
```
OPENAI_API_KEY=your_api_key_here
```
Backend runs on:

http://localhost:3000

---
## Android Setup

1. Open project in Android Studio
2. Sync Gradle
3. Run app on emulator
4. Make sure backend server is running

---

## Room Database Structure

#### Chats Table
Stores:

- id
- username
- messageText
- sender
- timestamp

---

## Project Structure (Important Files)

- MainActivity.java → Handles username input and navigation
- ChatActivity.java → Main chatbot logic and API handling
- Chats.java → Room Entity and model class
- MessageAdapter.java → RecyclerView adapter for chat messages
- ChatDao.java → Room database queries
- AppDatabase.java → Main Room database class
- ChatRequest.java → Request model for API
- ChatResponse.java → Response model for API
- ApiService.java → Retrofit API interface
- RetrofitClient.java → Backend connection setup
- server.js → Backend logic and OpenAI integration
