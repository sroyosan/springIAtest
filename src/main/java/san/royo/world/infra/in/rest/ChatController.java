package san.royo.world.infra.in.rest;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ChatController {
    private final ChatClient chatClient;
    private final EmbeddingClient embeddingClient;

    @Autowired
    public ChatController(ChatClient chatoCliente, EmbeddingClient embeddingClient) {
        this.chatClient = chatoCliente;
        this.embeddingClient = embeddingClient;
    }

    @GetMapping("/ai/chat")
    public ResponseEntity<Map> chat(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        ChatResponse response = chatClient.call(
                new Prompt(message));
        return ResponseEntity.ok(Map.of("Chat", response.getResult().getOutput().getContent()));
    }

    @GetMapping("/ai/embed")
    public ResponseEntity<Map> embed(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        EmbeddingResponse response = embeddingClient.embedForResponse(List.of(message));
        return ResponseEntity.ok(Map.of("embed", response.getResult().getOutput()));
    }


}
