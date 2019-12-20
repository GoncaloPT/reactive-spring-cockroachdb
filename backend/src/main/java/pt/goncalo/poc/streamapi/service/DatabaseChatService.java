package pt.goncalo.poc.streamapi.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.messaging.support.MessageBuilder;
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
@RequiredArgsConstructor
//@EnableBinding({Source.class, Sink.class})
//@MessageEndpoint
@Log4j2
public class DatabaseChatService implements IChatService{
    private final ChatRepository repository;
    private final Source source;
    private final Sink sink;

    private UnicastProcessor<ChatMessage> eventPublisher = UnicastProcessor.create();
    private Flux<ChatMessage> messages = eventPublisher.publish().autoConnect();

    @Override
    public Flux<ChatMessage> getMessagePublisher() {

        return repository.findAll().mergeWith(messages);
    }

    /**
     * Receives new messages
     */
    //@StreamListener(Sink.INPUT)
    public void MessageReceiver(ChatMessage chatMessage){
        log.warn("[SINK]You've got mail! {0}", chatMessage);
        eventPublisher.onNext(chatMessage);
    }

    /**
     * Saves message in the database for history and sends message
     * TODO: It would make more sense to create another app that would
     * subscribe to message publisher and save... it is what it is.. uber app
     * @param personId
     * @param message
     */
    @Override
    public void publishMessage(String personId, String message) {
        ChatMessage c = new ChatMessage(null,personId,message, new Date());
        repository.save(c).doOnSuccess(
                cMessage -> source.output().send(MessageBuilder.withPayload(c).build())
        ).subscribe();


    }

}
