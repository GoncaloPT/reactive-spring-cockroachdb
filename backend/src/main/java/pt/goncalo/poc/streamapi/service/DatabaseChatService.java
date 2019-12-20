package pt.goncalo.poc.streamapi.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pt.goncalo.poc.streamapi.model.ChatMessage;
import pt.goncalo.poc.streamapi.repository.ChatRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Actually does the same as ChatService but also storing in database instead of only in memory data
 */
@Component

public class DatabaseChatService implements IChatService{
    private final ChatRepository repository;
    private UnicastProcessor<ChatMessage> eventPublisher = UnicastProcessor.create();
    private Flux<ChatMessage> messages = eventPublisher.publish().autoConnect();
    public DatabaseChatService(ChatRepository repository) {
        this.repository = repository;

    }

    @Override
    public Flux<ChatMessage> getMessagePublisher() {
        return repository.findAll().mergeWith(messages);
    }

    @Override
    public void publishMessage(String personId, String message) {
        ChatMessage c = new ChatMessage(null,personId,message, new Date());
        repository.save(c).doOnSuccess(cMessage -> eventPublisher.onNext(cMessage)).subscribe();
    }

}
