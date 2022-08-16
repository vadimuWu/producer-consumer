package market.service.kafkaService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.dto.KafkaDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {
    private static final String orderTopic = "${topic.name}";
    //записали в переменную имя топика
    private final ObjectMapper objectMapper;
    //используем этот класс, так как он умеет читать значения(сеариализованные)
    @KafkaListener(topics = orderTopic)
    //позволяет обратится к топику
    public void consumeMessage(String message) throws JsonProcessingException {
        KafkaDto kafkaDto = objectMapper.readValue(message, KafkaDto.class);
        //используем метод readValue, так как он выполняет (Метод десериализации содержимого JSON из заданной строки содержимого JSON.)
        if(!(kafkaDto == null)) {
            log.info("message consumed {}", message);
        }
    }
}
