package market.model;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class Message {
    String message;
    Integer number;
}