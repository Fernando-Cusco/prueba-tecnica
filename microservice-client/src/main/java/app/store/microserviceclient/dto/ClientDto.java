package app.store.microserviceclient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ClientDto {
    @JsonProperty("id_person")
    private Long idPerson;
    @JsonProperty("id_client")
    private Long idClient;
    private String nombres;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    @JsonProperty("cliente_id")
    private Long clienteId;
    private String contrasena;
    private Boolean estado;
}
