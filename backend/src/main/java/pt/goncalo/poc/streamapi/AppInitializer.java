package pt.goncalo.poc.streamapi;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pt.goncalo.poc.streamapi.repository.PersonRepository;

@Lazy(false)
@Component
@AllArgsConstructor
@Log4j2
public class AppInitializer {

    private final PersonRepository repository;
    @EventListener(ContextRefreshedEvent.class)
    public void initialize(){
       /* log.warn("Inside initialize");
        //clean up table
        repository.deleteAll().subscribe();


        Person p = new Person(1,"Gonçalo", 32);
        repository.save(p);
        */

    }

}
