package app.store.microserviceaccount.mapper;

import app.store.microserviceaccount.domain.entity.AccountEntity;
import app.store.microserviceaccount.dto.AccountDto;

public class AccountMapper {
    private AccountMapper() {
    }

    public static AccountEntity toEntity(AccountDto accountDto) {
        return AccountEntity.builder()
                .id(accountDto.getId())
                .clientId(accountDto.getClientId())
                .accountNumber(accountDto.getNumeroCuenta())
                .accountType(accountDto.getTipoCuenta())
                .amount(accountDto.getMonto())
                .status(accountDto.getEstado())
                .build();
    }

    public static AccountDto toDto(AccountEntity accountEntity) {
        return AccountDto.builder()
                .id(accountEntity.getId())
                .clientId(accountEntity.getClientId())
                .numeroCuenta(accountEntity.getAccountNumber())
                .tipoCuenta(accountEntity.getAccountType())
                .monto(accountEntity.getAmount())
                .estado(accountEntity.getStatus())
                .build();
    }

}
