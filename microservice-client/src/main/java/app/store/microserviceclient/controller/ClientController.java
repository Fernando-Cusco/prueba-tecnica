package app.store.microserviceclient.controller;


import app.store.microserviceclient.controller.common.ApiCommon;
import app.store.microserviceclient.dto.ClientDto;
import app.store.microserviceclient.dto.ResponseDto;
import app.store.microserviceclient.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Slf4j
@RequiredArgsConstructor
public class ClientController extends ApiCommon {

    private static final String CONTROLLER_NAME = ClientController.class.getSimpleName();

    private final ClientService clientService;

    private final HttpServletRequest request;


    @PostMapping
    public ResponseEntity<ResponseDto> saveClient(@RequestBody ClientDto clientDto) {
        log.info("### {}.saveClient() method is called", CONTROLLER_NAME);
        logRequest(request);
        ResponseDto responseDto = clientService.saveClient(clientDto);
        if (responseDto == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getClientById(@PathVariable Long id) {
        log.info("### {}.getClientById() method is called", CONTROLLER_NAME);
        logRequest(request);
        ResponseDto responseDto = clientService.getClientById(id);
        if (responseDto == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto) {
        log.info("### {}.updateClient() method is called", CONTROLLER_NAME);
        logRequest(request);
        ResponseDto responseDto = clientService.updateClient(id, clientDto);
        if (responseDto == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteClient(@PathVariable Long id) {
        log.info("### {}.deleteClient() method is called", CONTROLLER_NAME);
        logRequest(request);
        ResponseDto responseDto = clientService.deleteClient(id);
        if (responseDto == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllClients() {
        log.info("### {}.getAllClients() method is called", CONTROLLER_NAME);
        logRequest(request);
        ResponseDto responseDto = clientService.getAllClients();
        if (responseDto == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

}
