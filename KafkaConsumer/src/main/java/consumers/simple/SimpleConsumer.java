package consumers.simple;

import kafka.log.TimestampOffset;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


/**
 * Consumer for simple logging messages without any logic.
 */
@ConditionalOnProperty(name = "consumer.simple.enabled", havingValue = "true")
@Component
@Slf4j
public class SimpleConsumer {

    @KafkaListener(topics = "${kafka.topic.simple}", groupId = "${kafka.groupId.simple}")
    public void listen(ConsumerRecord<?,?> record) {
        log.info(record.value().toString());
    }

}
