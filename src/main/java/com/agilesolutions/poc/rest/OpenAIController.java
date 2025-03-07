package com.agilesolutions.poc.rest;

import com.agilesolutions.poc.model.Bom;
import com.agilesolutions.poc.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("ai")
public class OpenAIController {

    private final ChatService chatService;

    @GetMapping("/bom")
    public ResponseEntity<String> generateHaiku(@RequestBody String version) {
        return ResponseEntity.ok(chatService.getBom(version));
    }

    @GetMapping("/bomModel")
    public Bom chat(@RequestBody String version) {
        return chatService.getBomModel(version);
    }

}
