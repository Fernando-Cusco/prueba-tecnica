package app.store.microserviceaccount.service.impl;

import app.store.microserviceaccount.client.ClientService;
import app.store.microserviceaccount.domain.entity.AccountEntity;
import app.store.microserviceaccount.domain.entity.MovementEntity;
import app.store.microserviceaccount.dto.MovementDto;
import app.store.microserviceaccount.dto.ResponseDto;
import app.store.microserviceaccount.mapper.MovementMapper;
import app.store.microserviceaccount.service.AccountService;
import app.store.microserviceaccount.service.MovementService;
import app.store.microserviceaccount.service.common.ServiceCommon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovementServiceImpl extends ServiceCommon implements MovementService {

    private static final String CLASS_NAME = MovementServiceImpl.class.getSimpleName();

    private final AccountService accountService;

    private final ClientService clientService;


    @Override
    public ResponseDto saveMovement(MovementDto movementDto) {
        Long idCuenta = movementDto.getIdCuenta();
        if (idCuenta == null) {
            log.error("{}.saveMovement() method called with null idCuenta", CLASS_NAME);
            return generateResponseNotFound("Account not found");
        }

        AccountEntity account = accountService.getAccountEntityById(idCuenta);
        if (account != null) {
            log.info("{}.saveMovement() method called with idCuenta: {}", CLASS_NAME, idCuenta);
            MovementEntity movementEntity = MovementMapper.toEntity(movementDto);
            BigDecimal amount = movementEntity.getAmount();
            if (amount == null || amount.compareTo(BigDecimal.ZERO) == 0) {
                log.error("{}.saveMovement() method called with null amount", CLASS_NAME);
                return generateResponseError("Amount cannot be 0", movementDto);
            }
            BigDecimal balance = account.getAmount();
            if (amount.compareTo(BigDecimal.ZERO) < 0) {
                if (balance.compareTo(amount.abs()) < 0) {
                    log.error("{}.saveMovement() method called with insufficient balance", CLASS_NAME);
                    return generateResponseError("Insufficient balance", movementDto);
                }
                account.setAmount(account.getAmount().add(amount));
            } else {
                account.setAmount(account.getAmount().add(amount));
            }
            movementEntity.setBalance(balance);
            account.addMovement(movementEntity);
            accountService.saveOrUpdateAccount(account);
            return generateResponseSuccess("Movement saved successfully", movementDto);
        }
        return generateResponseNotFound("Account not found");
    }

    @Override
    public ResponseDto getMovementByIdAndDate(Long accountId, String date) {
        log.info("{}.getMovementByIdAndDate() method called with accountId: {} and date: {}", CLASS_NAME, accountId, date);
        AccountEntity account = accountService.getAccountEntityById(accountId);
        if (account == null) {
            return generateResponseNotFound("Account not found");
        }
        Long idCliente = account.getClientId();

        String clientData = account.getClientName() == null ? getClientDataById(idCliente) : account.getClientName();
        if (clientData == null || clientData.isEmpty()) {
            return generateResponseNotFound("Client not found");
        }
        List<MovementEntity> movements = account.getMovements();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate movementDate = LocalDate.parse(date, formatter);
        List<Map<String, Object>> response = movements
                .stream()
                .filter(m -> !m.getCreatedAt().toLocalDate().isBefore(movementDate))
                .map(m -> generateDataReport(m, account, clientData))
                .toList();
        return generateResponseSuccess("Movements retrieved successfully", response);
    }

    @Override
    public ResponseDto getMovementReport(Long accountId, String startDate, String endDate) {
        log.info("{}.getMovementReport() method called with accountId: {}, startDate: {} and endDate: {}", CLASS_NAME, accountId, startDate, endDate);
        AccountEntity account = accountService.getAccountEntityById(accountId);
        if (account == null) {
            return generateResponseNotFound("Account not found");
        }
        Long idCliente = account.getClientId();

        String clientData = account.getClientName() == null ? getClientDataById(idCliente) : account.getClientName();
        if (clientData == null || clientData.isEmpty()) {
            return generateResponseNotFound("Client not found");
        }
        List<MovementEntity> movements = account.getMovements();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        List<Map<String, Object>> response = movements
                .stream()
                .filter(m -> !m.getCreatedAt().toLocalDate().isBefore(start) && !m.getCreatedAt().toLocalDate().isAfter(end))
                .map(m -> generateDataReport(m, account, clientData))
                .toList();
        return generateResponseSuccess("Movements retrieved successfully", response);
    }

    private Map<String, Object> generateDataReport(MovementEntity movement, AccountEntity account, String clientData) {
        Map<String, Object> map = new HashMap<>();
        map.put("Fecha", movement.getCreatedAt());
        map.put("Cliente", clientData);
        map.put("NumeroCuenta", account.getAccountNumber());
        map.put("Tipo", account.getAccountType());
        map.put("Saldo_Inicial", movement.getBalance());
        map.put("Estado", account.getStatus());
        map.put("Movimiento", movement.getAmount());
        map.put("Saldo_Disponible", account.getAmount());
        return map;
    }

    private String getClientDataById(Long idCliente) {
        return clientService.getDataClientById(idCliente);
    }

}
