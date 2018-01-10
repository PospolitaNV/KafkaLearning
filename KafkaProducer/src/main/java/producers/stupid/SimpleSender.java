package producers.stupid;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * Simple sender - sends to topic and logs sended string.
 */
@Slf4j
@Component
public class SimpleSender {

    @Value("${kafka.topic.simple}")
    String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private RandomString randomString = new RandomString(8);

    @Scheduled(fixedRateString = "${kafka.producer.sendRate.simple}")
    public void send() {
        String payload = randomString.nextString();
        log.info("sending payload='{}' to topic='{}'", payload, topicName);
        kafkaTemplate.send(topicName, payload);
    }

}
