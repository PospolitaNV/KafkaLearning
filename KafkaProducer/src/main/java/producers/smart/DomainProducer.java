package producers.smart;

import domain.Message;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class DomainProducer {

    @Value("${kafka.topic.domain}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private RandomString randomString = new RandomString(8);

    @Scheduled(fixedRateString = "${kafka.producer.sendRate.domain}")
    public void send() {
        Message payload = new Message(randomString.nextString(), LocalDateTime.now());
        log.info("sending payload='{}' to topic='{}'", payload, topicName);
        kafkaTemplate.send(topicName, payload.toString());
    }

}
