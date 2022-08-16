package market.service.kafkaService;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.model.Message;
import market.service.kafkaService.producer.Producer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HandlerProducerService {
    private final Producer producer;
    public String createKafkaOrder(Message message) throws JsonProcessingException {
        return producer.sendMessage(message);
        //посылаем на топик значение, логика реализованна в Producer
    }
}
