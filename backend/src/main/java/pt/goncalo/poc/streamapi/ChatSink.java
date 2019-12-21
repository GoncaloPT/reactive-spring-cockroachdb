package pt.goncalo.poc.streamapi;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import pt.goncalo.poc.streamapi.model.ChatMessage;

/**
 * This is a sample Sink, can be used to receive chat messages
 */
//@EnableBinding(ChatSink.Sink.class)
@Log4j2
public class ChatSink {

    // Sink application definition
    @StreamListener(Sink.CHAT)
    public void receive(ChatMessage message) {
        log.info("******************\nAt the Sink\n******************");
        log.info("Received chat message from sender" + message);
    }

    public interface Sink {
        String CHAT = "chat-sink";

        @Input(CHAT)
        SubscribableChannel chatSink();
    }
}
