package pt.goncalo.poc.streamapi.service;

import pt.goncalo.poc.streamapi.model.ChatMessage;
import reactor.core.publisher.Flux;

public interface IChatService {
    public Flux<ChatMessage> getMessagePublisher();

    public ChatMessage publishMessage(String personId, String message);


}