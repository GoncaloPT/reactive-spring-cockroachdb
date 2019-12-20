package pt.goncalo.poc.streamapi.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import pt.goncalo.poc.streamapi.model.ChatMessage;

public interface ChatRepository extends ReactiveCrudRepository<ChatMessage,Integer> {
}
