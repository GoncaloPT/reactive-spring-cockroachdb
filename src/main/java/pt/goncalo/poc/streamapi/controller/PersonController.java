package pt.goncalo.poc.streamapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import pt.goncalo.poc.streamapi.repository.PersonRepository;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {
    private final PersonRepository repository;

    /**
     * @return
     */
    @GetMapping("/")
    public Flux<ServerResponse> get() {
        return repository
                .findAll()
                .flatMap(person -> ServerResponse.ok().bodyValue(person))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(error ->
                        ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                );
    }
}
