package com.agilesolutions.poc.service;

import com.agilesolutions.poc.model.Bom;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatService {

    @Qualifier("openAiChatModel")
    private final ChatModel aiClient;

    public String getBom(String version) {

        BeanOutputConverter<String> outputConverter = new BeanOutputConverter<>(String.class);

        String promptString = "list only library name and version for all java spring library version contained by springboot with version {version}{format}";

        PromptTemplate promptTemplate = new PromptTemplate(promptString);
        promptTemplate.add("version", version);
        promptTemplate.add("format", outputConverter.getFormat());

        ChatResponse response = aiClient.call(promptTemplate.create());
        return outputConverter.convert(response.getResult().getOutput().getContent());

    }

    public Bom getBomModel(String version) {

        BeanOutputConverter<Bom> outputConverter = new BeanOutputConverter<>(Bom.class);

        String promptString = "list only library name and version for all java spring library version contained by springboot with version {version}{format}";

        PromptTemplate promptTemplate = new PromptTemplate(promptString);
        promptTemplate.add("version", version);
        promptTemplate.add("format", outputConverter.getFormat());

        ChatResponse response = aiClient.call(promptTemplate.create());
        return outputConverter.convert(response.getResult().getOutput().getContent());
    }
}