package org.example.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("open-api")
public class OpenAIController {

//    private OpenAiChatModel openAiChatModel;
        private ChatClient chatClient;
//    OpenAIController(OpenAiChatModel openAiChatModel) {
//        this.chatClient = ChatClient.create(openAiChatModel);
//    }
    ChatMemoryRepository chatMemoryRepository;
    OpenAIController(ChatClient.Builder builder) {
        this.chatClient = builder
//                .defaultAdvisors(new InMemoryChatMemoryRepository())
                .build();
    }

    @GetMapping("ask/{message}")
    public String ask(@PathVariable String message) {
        chatMemoryRepository = new InMemoryChatMemoryRepository();
        String response = chatClient.prompt(message).call().content();

        return response;
    }
}
