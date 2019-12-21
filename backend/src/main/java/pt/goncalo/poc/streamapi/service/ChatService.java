package pt.goncalo.poc.streamapi.service;

import pt.goncalo.poc.streamapi.model.ChatMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.util.Date;


//@Component()
public class ChatService implements IChatService{

    private UnicastProcessor<ChatMessage> eventPublisher = UnicastProcessor.create();
    private Flux<ChatMessage> messages = eventPublisher.publish().autoConnect();

    public Flux<ChatMessage> getMessagePublisher() {
        return messages;
    }

    public ChatMessage publishMessage(String personId, String message) {
        ChatMessage chatMessage = new ChatMessage(null,personId, message, new Date());
        eventPublisher.onNext(chatMessage);
        return chatMessage;
    }


}
