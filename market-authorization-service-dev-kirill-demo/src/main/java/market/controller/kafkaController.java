package market.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import market.model.Message;
import market.service.kafkaService.HandlerProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class kafkaController {
    private final HandlerProducerService handlerProducerService;

    @PostMapping
    public String createKafkaOrder(@RequestBody Message message) throws JsonProcessingException {
        log.info("create kafka order request received");
        return handlerProducerService.createKafkaOrder(message);
        //возврашаем запрос в формате строк в сервис HandlerProducerService, при отправке использовале патерн Message, находящийся в директории model.Message
    }
}
