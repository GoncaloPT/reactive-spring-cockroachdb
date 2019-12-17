package pt.goncalo.poc.streamapi.service;

import org.springframework.stereotype.Component;
import pt.goncalo.poc.streamapi.model.ChatMessage;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;
import java.util.stream.Stream;


@Component()
public class ChatService implements IChatService{

    private UnicastProcessor<ChatMessage> eventPublisher = UnicastProcessor.create();
    private Flux<ChatMessage> messages = eventPublisher.publish().autoConnect();


    public Flux<ChatMessage> getMessagePublisher() {
        return messages;
    }

    public void publishMessage(String personId, String message) {
        ChatMessage chatMessage = new ChatMessage(null,personId, message, new Date());
        eventPublisher.onNext(chatMessage);
    }
}
