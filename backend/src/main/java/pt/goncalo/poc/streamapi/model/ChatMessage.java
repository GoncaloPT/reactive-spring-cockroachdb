package pt.goncalo.poc.streamapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class ChatMessage implements Comparable{
    public ChatMessage() {
    }

    @Id
    private Integer id;
    @NotNull
    private String sender;
    @NotNull
    private String message;
    private Date date;

    @Override
    public int compareTo(Object o) {
        if(this.date == null)
            return -1;
        if(o instanceof ChatMessage && ((ChatMessage) o).getDate() != null) {
            return this.date.compareTo( ((ChatMessage)o).getDate());
        }
        return 1;
    }
}
