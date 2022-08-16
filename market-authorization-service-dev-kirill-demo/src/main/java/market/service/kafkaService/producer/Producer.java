package market.service.kafkaService.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Producer {

    @Value("${topic.name}")
    private String orderTopic;
    //записываем имя топика в строковую переменную orderTopic
    private final ObjectMapper objectMapper;
    // используем класс ObjectMapper, так как он предоставляет функциональные возможности для чтения и записи JSON
    private final KafkaTemplate<String, String> kafkaTemplate;
    //используем шаблон, для выполнения высоко уровневых операций, является потокобезопасным, так как настроили его в market.config.kafka.kafkaConfig
    public String sendMessage(Message message) throws JsonProcessingException {
        //в параметры передаем заранее написанную модель market.model.Message
        String orderAsMessage = objectMapper.writeValueAsString(message);
        //записываем в переменную orderAsMessage, с помошью метода ObjectMapper (writeValueAsString) "
        // Метод, который можно использовать для сериализации любого значения Java в виде строки. ", переменную message, переданную через пост запрос
        kafkaTemplate.send(orderTopic, orderAsMessage);
        //посылаем в топик sending.something, значение (сеарилизованное) orderAsMessage
        log.info("kafka order produced {}", orderAsMessage);
        return "message sent";
    }
}
