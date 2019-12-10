package pt.goncalo.poc.streamapi.controller;

import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.goncalo.poc.streamapi.model.Person;
import pt.goncalo.poc.streamapi.repository.PersonRepository;

@RestController
@AllArgsConstructor
public class PersonController {
    private final PersonRepository repository;

    /**
     * @return
     */
    @GetMapping("/person/")
    public Publisher<Person> get() {
        return repository
                .findAll();

    }
}
