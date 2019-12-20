package pt.goncalo.poc.streamapi.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.goncalo.poc.streamapi.model.ChatMessage;
import pt.goncalo.poc.streamapi.service.ChatService;
import pt.goncalo.poc.streamapi.service.IChatService;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Log4j2
public class ChatController {
    private final IChatService service;

    @CrossOrigin
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/messages")
    public Flux<ChatMessage> messagePublisher(){
        return service.getMessagePublisher();
    }
    @CrossOrigin
    @PostMapping(value = "/message", consumes = "application/json")
    @Valid
    public void messageReceiver(@Valid @RequestBody ChatMessage message){
        log.info("received a new message {} from sender {}",message.getMessage(),message.getSender());
        service.publishMessage(message.getSender(),message.getMessage());
    }

    @CrossOrigin
    @GetMapping(value = "/")
    public Flux<ChatMessage> list(){
        return service.getMessagePublisher();
    }



}
