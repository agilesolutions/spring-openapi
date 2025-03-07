package com.agilesolutions.poc.rest;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OpenAIController.class)
class OpenAIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("openAiChatModel")
    private ChatModel aiClient;

    @Test
    public void givenGetCatHaiku_whenCallingAiClient_thenCorrect() throws Exception {
        mockMvc.perform(get("/ai/bom"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsStringIgnoringCase("cat")));
    }


}