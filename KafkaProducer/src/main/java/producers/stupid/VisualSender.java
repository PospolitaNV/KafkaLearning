package producers.stupid;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Simple sender - sends to topic and logs sended string.
 */
@ConditionalOnProperty(name = "producer.visual.enabled", havingValue = "true")
@Slf4j
@Component
public class VisualSender {

    @Value("${kafka.topic.visual}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private RandomString randomString = new RandomString(8);

    @Scheduled(fixedRateString = "${kafka.producer.sendRate.visual}")
    public void send() {
        String payload = randomString.nextString();
        log.info("sending payload='{}' to topic='{}'", payload, topicName);
        kafkaTemplate.send(topicName, payload);
    }

}
