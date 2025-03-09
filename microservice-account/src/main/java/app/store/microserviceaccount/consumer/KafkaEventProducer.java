package app.store.microserviceaccount.consumer;

import app.store.microserviceaccount.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventProducer {

    private final AccountService accountService;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(Map<String, Object> message) {
        log.info("### MESSAGE CLIENT CREATE: {}", message);
        log.info("### MESSAGE CLIENT CREATE TOPIC: {}", message.get("topic"));
        log.info("### CREATING ACCOUNT DEFAULT ###");
        Integer accountId = (Integer) message.get("idPerson");
        accountService.saveDefaultAccount(Long.valueOf(accountId.toString()));
    }


}
