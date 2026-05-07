import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import OpenAI from "openai";

dotenv.config();

const app = express();

const client = new OpenAI({
  apiKey: process.env.OPENAI_API_KEY,
});

app.use(cors());
app.use(express.json());

app.post("/chat", async (req, res) => {
  try {
    const userMessage = req.body.message;

    const prompt = `
You are a  AI chatbot inside an Android mobile chatbot app.
Reply clearly and simply to the user's message and keep it short.

User message: ${userMessage}
`.trim();

    const response = await client.responses.create({
      model: "gpt-4.1-mini",
      input: prompt,
    });

    res.json({
      reply: response.output_text || "No response returned.",
    });

  } catch (error) {
    console.error(error);
    res.status(500).json({
      reply: "Server error while generating response.",
    });
  }
});

app.listen(3000, () => {
  console.log("Server running on http://localhost:3000");
});