package pt.goncalo.poc.streamapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.goncalo.poc.streamapi.model.Person;
import pt.goncalo.poc.streamapi.repository.PersonRepository;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {
    private final PersonRepository repository;
    /**
     *
     * @return
     */
    @GetMapping("/")
    public Flux<Person> get(){
         return repository.findAll();
    }
}
