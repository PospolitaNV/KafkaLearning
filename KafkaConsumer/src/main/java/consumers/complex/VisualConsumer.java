package consumers.complex;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Changes color every time when message appears.
 */
@ConditionalOnProperty(name = "consumer.visual.enabled", havingValue = "true")
@Component
@Slf4j
public class VisualConsumer {

    private JFrame frame;
    private Random random = new Random();

    @PostConstruct
    void initFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 400, 400, 400);
        frame.setMinimumSize(new Dimension(400, 400));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    @KafkaListener(topics = {"${kafka.topic.simple}", "${kafka.topic.domain}"}, groupId = "${kafka.groupId.visual}")
    public void listen(ConsumerRecord<?, ?> record) {
        frame.getContentPane().setBackground(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
        log.info(record.value().toString());
    }

}
