package market.config.kafka;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import springfox.documentation.swagger2.mappers.ModelMapper;

@EnableKafka
//необезательная аннотация!
@Configuration
public class kafkaConfigConsumer {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
