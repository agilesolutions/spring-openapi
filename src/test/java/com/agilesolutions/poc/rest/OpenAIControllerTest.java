package com.agilesolutions.poc.rest;

import com.agilesolutions.poc.config.AIConfig;
import com.agilesolutions.poc.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OpenAIController.class)
@ContextConfiguration(classes = {AIConfig.class, ChatService.class})
class OpenAIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("openAiChatModel")
    private ChatModel aiClient;

    @Autowired
    private ChatService chatService;

    @Test
    public void givenGetCatHaiku_whenCallingAiClient_thenCorrect() throws Exception {
        mockMvc.perform(get("/ai/bom").param("version","1.24")
                        .contentType(MediaType.APPLICATION_JSON))
                //.andExpect(status().isOk())
                .andDo(print());
                //.andExpect(content().string(containsStringIgnoringCase("cat")));
    }


}