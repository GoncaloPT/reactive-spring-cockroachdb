package pt.goncalo.poc.streamapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@EqualsAndHashCode
public class Person {
    @Id
    private Integer id;
    private String name;
    private Integer age;
}
