package san.royo.world.infra.config;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiEmbeddingClient;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfiguration {

    @Bean
    public ChatClient chatoCliente() {
        var openAiApi = new OpenAiApi(System.getenv("OPENAI_API_KEY"));

        return new OpenAiChatClient(openAiApi, OpenAiChatOptions.builder()
                .withModel("gpt-3.5-turbo")
                .withTemperature(Float.valueOf(0.4f))
                .withMaxTokens(200)
                .build());
    }

    @Bean
    public EmbeddingClient embeddingClient() {
        var openAiApi = new OpenAiApi(System.getenv("OPENAI_API_KEY"));

        return new OpenAiEmbeddingClient(openAiApi, MetadataMode.EMBED, OpenAiEmbeddingOptions.builder()
                .withModel("text-embedding-3-small")
                .build());
    }

}
