package pt.goncalo.poc.streamapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ChatRoom {
    @Id
    private int roomId;
    private String roomName;

}
