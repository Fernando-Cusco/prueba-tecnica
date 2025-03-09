package app.store.microserviceaccount.controller;

import app.store.microserviceaccount.controller.common.ApiCommon;
import app.store.microserviceaccount.dto.AccountDto;
import app.store.microserviceaccount.dto.ResponseDto;
import app.store.microserviceaccount.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
@Slf4j
@RequiredArgsConstructor
public class AccountController extends ApiCommon {

    private static final String CONTROLLER_NAME = AccountController.class.getSimpleName();

    private final AccountService accountService;

    private final HttpServletRequest request;


    @PostMapping
    public ResponseEntity<ResponseDto> saveAccount(@RequestBody AccountDto accountDto) {
        log.info("### {}.saveAccount() method is called", CONTROLLER_NAME);
        logRequest(request);
        ResponseDto responseDto = accountService.saveAccount(accountDto);
        if (responseDto == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getAccountById(@PathVariable Long id) {
        log.info("### {}.getAccountById() method is called", CONTROLLER_NAME);
        logRequest(request);
        ResponseDto responseDto = accountService.getAccountById(id);
        if (responseDto == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

    @GetMapping("/cliente/{clientId}")
    public ResponseEntity<ResponseDto> getAccountsByClientId(@PathVariable Long clientId) {
        log.info("### {}.getAccountsByClientId() method is called", CONTROLLER_NAME);
        logRequest(request);
        ResponseDto responseDto = accountService.getAllAccounts(clientId);
        if (responseDto == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

}
