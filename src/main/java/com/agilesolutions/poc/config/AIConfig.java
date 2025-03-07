package com.agilesolutions.poc.config;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {

    @Bean("openAiChatModel")
    ChatModel aiClient() {

        return new OpenAiChatModel(new OpenAiApi(""));

    }

}
