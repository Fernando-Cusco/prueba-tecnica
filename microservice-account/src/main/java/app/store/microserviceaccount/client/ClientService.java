package app.store.microserviceaccount.client;

import app.store.microserviceaccount.dto.ResponseDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {

    private static final String CLASS_NAME = ClientService.class.getSimpleName();

    private final ClientFeignClient feignClient;

    public String getDataClientById(Long id) {
        log.info("{}.getDataClientById() called", CLASS_NAME);
        try {
            ResponseDto clientResponse = feignClient.getClientById(id).getBody();
            if (clientResponse == null) {
                return null;
            }
            @SuppressWarnings("unchecked")
            Map<String, Object> data = (Map<String, Object>) clientResponse.getData();
            return (String) data.get("nombres");
        } catch (FeignException e) {
            log.error("Error: {}", e.getMessage());
            String responseBody = e.contentUTF8();
            log.error("Response body: {}", responseBody);
            return null;
        }
    }

}
