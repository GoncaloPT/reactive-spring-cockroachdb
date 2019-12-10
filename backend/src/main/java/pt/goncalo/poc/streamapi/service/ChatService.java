package pt.goncalo.poc.streamapi.service;

import org.springframework.stereotype.Component;
import pt.goncalo.poc.streamapi.model.ChatMessage;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Supplier;
import java.util.stream.Stream;


@Component()
public class ChatService {

    private BlockingQueue<ChatMessage> queue = new ArrayBlockingQueue<ChatMessage>(100);
    private Supplier<Stream<ChatMessage>> messageStreamSupplier = () ->
            Stream.generate(() -> {
                try {
                    return queue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });


    public Flux<ChatMessage> getMessagePublisher() {
        //return eventProcessor.replay().autoConnect().share();
        
        return ConnectableFlux.fromStream(messageStreamSupplier.get()).share().replay().autoConnect();
        //return Flux.from(publisher);

    }

    public void publishMessage(String personId, String message) {

        ChatMessage chatMessage = new ChatMessage(personId, message, new Date());
        queue.add(chatMessage);



    }


}
