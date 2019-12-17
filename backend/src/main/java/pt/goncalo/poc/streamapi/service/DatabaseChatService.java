package pt.goncalo.poc.streamapi.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pt.goncalo.poc.streamapi.model.ChatMessage;
import pt.goncalo.poc.streamapi.repository.ChatRepository;
import reactor.core.publisher.Flux;

/**
 * Actually does the same as ChatService but using the database instead of in memory data
 */
@Component
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseChatService implements IChatService{
    private final ChatRepository repository;

    @Override
    public Flux<ChatMessage> getMessagePublisher() {
        return null;
    }

    @Override
    public void publishMessage(String personId, String message) {

    }
}
