package consumers.complex;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Converts message to Domain class and then logs it.
 */
@Component
@Slf4j
public class DomainConsumer {

    @KafkaListener(topics = "${kafka.topic.domain}", groupId = "${kafka.groupId.domain}")
    public void listen(ConsumerRecord<?,?> record) {
        log.info(record.value().toString());
    }

}
