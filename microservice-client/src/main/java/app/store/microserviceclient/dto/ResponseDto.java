package app.store.microserviceclient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    private String message;
    private String code;
    private Integer httpStatus;
    private Object data;
}
