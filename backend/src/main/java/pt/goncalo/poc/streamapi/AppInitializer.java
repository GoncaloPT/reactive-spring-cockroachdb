package pt.goncalo.poc.streamapi;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import pt.goncalo.poc.streamapi.repository.PersonRepository;
import reactor.core.publisher.Mono;

@Lazy(false)
@Component
@AllArgsConstructor
@Log4j2
public class AppInitializer {

    private final PersonRepository repository;
    private final DatabaseClient dbClient;
    @EventListener(ContextRefreshedEvent.class)
    public void initialize(){

        log.warn("Inside initialize");


        Mono<Void> chat_message_table_created = dbClient.execute("CREATE TABLE IF NOT EXISTS chat_message (" +
                "id int, " +
                "sender varchar(255), " +
                "message varchar(2000), " +
                "date DATE )").then().doOnSuccess(a -> log.info("INITIALIZER - Chat Message table exists!!"));
                //.doOnError(ex -> log.warn("error creating table", ex));
        chat_message_table_created.subscribe();

       /*
        //clean up table
        repository.deleteAll().subscribe();


        Person p = new Person(1,"Gonçalo", 32);
        repository.save(p);
        */

    }

}
