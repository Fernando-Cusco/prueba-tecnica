package app.store.microserviceaccount.controller;

import app.store.microserviceaccount.controller.common.ApiCommon;
import app.store.microserviceaccount.dto.AccountDto;
import app.store.microserviceaccount.dto.MovementDto;
import app.store.microserviceaccount.dto.ResponseDto;
import app.store.microserviceaccount.service.AccountService;
import app.store.microserviceaccount.service.MovementService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
@Slf4j
@RequiredArgsConstructor
public class MovementController extends ApiCommon {

    private static final String CONTROLLER_NAME = MovementController.class.getSimpleName();

    private final MovementService movementService;

    private final HttpServletRequest request;


    @PostMapping
    public ResponseEntity<ResponseDto> saveMovement(@RequestBody MovementDto movementDto) {
        log.info("### {}.saveMovement() method is called", CONTROLLER_NAME);
        logRequest(request);
        ResponseDto responseDto = movementService.saveMovement(movementDto);
        if (responseDto == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

    @GetMapping("/{id}/{date}")
    public ResponseEntity<ResponseDto> getMovementByIdAndDate(@PathVariable Long id, @PathVariable String date) {
        log.info("### {}.getMovementByIdAndDate() method is called", CONTROLLER_NAME);
        logRequest(request);
        ResponseDto responseDto = movementService.getMovementByIdAndDate(id, date);
        if (responseDto == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

    @GetMapping("/reporte/{id}/{fechaInicio}/{fechaFin}")
    public ResponseEntity<ResponseDto> getMovementReport(@PathVariable Long id,
                                                         @PathVariable String fechaInicio,
                                                         @PathVariable String fechaFin) {
        log.info("### {}.getMovementReport() method is called", CONTROLLER_NAME);
        logRequest(request);
        ResponseDto responseDto = movementService.getMovementReport(id, fechaInicio, fechaFin);
        if (responseDto == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(responseDto.getHttpStatus()).body(responseDto);
    }

}
