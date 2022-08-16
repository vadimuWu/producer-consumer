package market.config.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class kafkaConfig {

    private final KafkaProperties kafkaProperties;
    //Что входит в класс(поля):
    //Разделенный запятыми список пар хост:порт, используемых для установления начальных подключений к кластеру Kafka. Применяется ко всем компонентам, если они не переопределены.
    //private List<String> bootstrapServers = new ArrayList<>(Collections.singletonList("localhost:9092"));
    //Идентификатор для передачи серверу при выполнении запросов. Используется для ведения журнала на стороне сервера.
    //private String clientId;
    //Дополнительные свойства, общие для производителей и потребителей, используемые для настройки клиента.
    //private final Map<String, String> properties = new HashMap<>();

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> properties = kafkaProperties.buildProducerProperties();
        //Создайте начальную карту свойств производителя из состояния этого экземпляра.
        //Это позволяет вам добавлять дополнительные свойства, если это необходимо, и переопределять компонент kafkaProducerFactory по умолчанию.
        //Возвращается:
        //свойства производителя, инициализированные настройками, определенными для этого экземпляра
        //public Map<String, Object> buildProducerProperties() {
        //		Map<String, Object> properties = buildCommonProperties();
        //		properties.putAll(this.producer.buildProperties());
        //		return properties;
        //	}
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
    //Шаблон для выполнения высокоуровневых операций.
    // При использовании с DefaultKafkaProducerFactory шаблон является потокобезопасным.

    @Bean
    public NewTopic topic() {
        //Новая тема, которая будет создана с помощью Admin.createTopics(Collection).
        return TopicBuilder
                //Конструктор для новой темы. Начиная с 2.6 разделов и реплик по умолчанию используется значение Optional.empty(),
                // указывающее, что будут применены значения по умолчанию для брокера.
                .name("sending.something")
                //Создайте TopicBuilder с указанным именем.
                //Параметры:
                //имя – это имя.
                //Возвращается:
                //строитель.
                .partitions(1)
                //Установите количество разделов (брокер по умолчанию "num.partitions").
                //Параметры:
                //partitionCount – количество разделов.
                //Возвращается:
                //строитель.
                .replicas(1)
                //Установите количество реплик (брокер по умолчанию 'default.replication.factor').
                //Параметры:
                //replicaCount – количество реплик (которые будут преобразованы в короткие).
                //Возвращается:
                //строитель.
                .build();
    }
}
