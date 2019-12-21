package pt.goncalo.poc.streamapi;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import pt.goncalo.poc.streamapi.model.ChatMessage;

import java.util.Date;

/**
 * This class can be used as an auto generator for chat messages
 */
//@EnableBinding(ChatSource.Source.class)
@Log4j2
public class ChatSource {

    @Bean
    @InboundChannelAdapter(value = Source.CHAT, poller = @Poller(fixedDelay = "5000", maxMessagesPerPoll = "1"))
    public MessageSource<ChatMessage> timerMessageSource() {
        return new MessageSource<ChatMessage>() {
            public Message<ChatMessage> receive() {
                log.info("******************\nAt the Source\n******************");
                ChatMessage message = new ChatMessage(null,"auto sender","new one", new Date());

                log.info("Sending value: " + message);
                return MessageBuilder.withPayload(message).build();
            }
        };
    }

    public interface Source {
        String CHAT = "chat-source";

        @Output(CHAT)
        MessageChannel chatSource();
    }
}
