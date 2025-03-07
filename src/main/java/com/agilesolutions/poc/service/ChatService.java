package com.agilesolutions.poc.service;

import com.agilesolutions.poc.model.Bom;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatClient chatClient;
    private final ChatModel aiClient;

    @Value("classpath:/prompts/story.st")
    private Resource bomPromptResource;

    public String getBom(String version) {

        BeanOutputConverter<String> outputConverter = new BeanOutputConverter<>(String.class);

        String promptString = "list only library name and version for all java spring library version contained by springboot with version {version}{format}";

        PromptTemplate promptTemplate = new PromptTemplate(promptString);
        promptTemplate.add("version", version);
        promptTemplate.add("format", outputConverter.getFormat());

        ChatResponse response = aiClient.call(promptTemplate.create());
        return outputConverter.convert(response.getResult().getOutput().getContent());

    }

    public List<Bom> getBomModel(String version) {

        var outputParser = new BeanOutputParser<>(Bom.class);

        PromptTemplate promptTemplate = new PromptTemplate(bomPromptResource, Map.of("author", version, "format", outputParser.getFormat()));
        Prompt prompt = promptTemplate.create();

        return chatClient.prompt(prompt)
                .call()
                .entity(new ParameterizedTypeReference<List<Bom>>() {});
    }
}