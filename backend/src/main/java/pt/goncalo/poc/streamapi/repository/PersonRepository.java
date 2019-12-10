package pt.goncalo.poc.streamapi.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pt.goncalo.poc.streamapi.model.Person;

public interface PersonRepository extends ReactiveCrudRepository<Person,Integer> {
}
