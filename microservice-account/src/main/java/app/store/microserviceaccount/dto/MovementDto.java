package app.store.microserviceaccount.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovementDto {
    private Long id;
    @JsonProperty("id_cuenta")
    private Long idCuenta;
    private String descripcion;
    private BigDecimal monto;
    private BigDecimal balance;
    private LocalDateTime fecha;
}
