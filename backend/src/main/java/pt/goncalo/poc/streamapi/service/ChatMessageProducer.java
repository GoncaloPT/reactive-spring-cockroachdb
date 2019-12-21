package pt.goncalo.poc.streamapi.service;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import pt.goncalo.poc.streamapi.model.ChatMessage;

@Component
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class ChatMessageProducer {
    private final Source source;
    public void produceMessage(ChatMessage m){
        source.output().send(MessageBuilder.withPayload(m).build());
    }

}
