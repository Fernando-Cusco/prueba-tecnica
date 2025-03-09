package app.store.microserviceaccount.service;

import app.store.microserviceaccount.dto.MovementDto;
import app.store.microserviceaccount.dto.ResponseDto;

public interface MovementService {
    ResponseDto saveMovement(MovementDto movementDto);

    ResponseDto getMovementByIdAndDate(Long accountId, String date);

    ResponseDto getMovementReport(Long accountId, String startDate, String endDate);
}
