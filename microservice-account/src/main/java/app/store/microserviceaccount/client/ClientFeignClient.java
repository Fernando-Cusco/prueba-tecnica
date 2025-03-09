package app.store.microserviceaccount.client;

import app.store.microserviceaccount.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(url = "http://microservice-client:8080", name = "product-service")
public interface ClientFeignClient {


    @GetMapping(value = "/clientes/{id}", produces = "application/json")
    ResponseEntity<ResponseDto> getClientById(@PathVariable Long id);

}
