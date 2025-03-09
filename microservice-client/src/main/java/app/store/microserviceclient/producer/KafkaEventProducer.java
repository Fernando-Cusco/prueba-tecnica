package app.store.microserviceclient.producer;

import app.store.microserviceclient.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventProducer {

    @Value("${kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

    public void send(ClientDto clientDto) {
        log.info("### EventProducer.send() method is called");
        Map<String, Object> event = new HashMap<>();
        event.put("clientId", clientDto.getClienteId());
        event.put("names", clientDto.getNombres());
        event.put("idPerson", clientDto.getIdClient());
        CompletableFuture.runAsync(() -> kafkaTemplate.send(topic, event));
    }



}
