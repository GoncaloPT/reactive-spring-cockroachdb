package pt.goncalo.poc.streamapi.service;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import pt.goncalo.poc.streamapi.model.ChatMessage;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Stream;


@Component
public class ChatService {

    private BlockingQueue<ChatMessage> queue = new ArrayBlockingQueue<ChatMessage>(100);
    private Flux<ChatMessage> publisher = Flux.fromStream(Stream.generate(()-> {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }));

    public Publisher<ChatMessage> getMessagePublisher(){

        return Flux.from(publisher);

    }

    public void publishMessage(String personId, String message){
        ChatMessage chatMessage = new ChatMessage(personId,message,new Date());
        queue.add(chatMessage);

    }






}
