package market.dto;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class KafkaDto {
    String message;
    Integer number;
}
//создали DTO, для того, чтобы потом преобразовать наши значения в нужный нам патерн