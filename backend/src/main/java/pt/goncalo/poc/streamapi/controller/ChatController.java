package pt.goncalo.poc.streamapi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.goncalo.poc.streamapi.model.ChatMessage;
import pt.goncalo.poc.streamapi.service.ChatService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Log4j2

public class ChatController {
    private final ChatService service;

    @CrossOrigin
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/messages")
    public Publisher<ChatMessage> messagePublisher(){
        return service.getMessagePublisher();
    }
    @CrossOrigin
    @PostMapping(value = "/message", consumes = "application/json")
    @Valid
    public void messageReceiver(@Valid @RequestBody ChatMessage message){
        log.info("received a new message {} from sender {}",message.getMessage(),message.getSender());
        service.publishMessage(message.getSender(),message.getMessage());
    }

}
