package app.store.microserviceaccount.mapper;

import app.store.microserviceaccount.domain.entity.MovementEntity;
import app.store.microserviceaccount.dto.MovementDto;

public class MovementMapper {
    private MovementMapper() {
    }

    public static MovementEntity toEntity(MovementDto dto) {
        return MovementEntity.builder()
                .id(dto.getId())
                .amount(dto.getMonto())
                .balance(dto.getBalance())
                .createdAt(dto.getFecha())
                .description(dto.getDescripcion())
                .build();
    }

    public static MovementDto toDto(MovementEntity entity) {
        return MovementDto.builder()
                .id(entity.getId())
                .monto(entity.getAmount())
                .balance(entity.getBalance())
                .fecha(entity.getCreatedAt())
                .build();
    }

}
