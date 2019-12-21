package pt.goncalo.poc.streamapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.MessageEndpoint;

@SpringBootApplication
@EnableBinding({Source.class, Sink.class})
@MessageEndpoint
public class StreamapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamapiApplication.class, args);
	}
}
