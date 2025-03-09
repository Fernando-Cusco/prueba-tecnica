package app.store.microserviceaccount.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {
    private Long id;
    @JsonProperty("cliente_id")
    private Long clientId;
    @JsonProperty("numero_cuenta")
    private String numeroCuenta;
    @JsonProperty("tipo_cuenta")
    private String tipoCuenta;
    private BigDecimal monto;
    private Boolean estado;
}
