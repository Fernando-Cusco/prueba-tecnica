package app.store.microserviceclient.service.common;

import app.store.microserviceclient.common.Constants;
import app.store.microserviceclient.dto.ResponseDto;

public class ServiceCommon {

    protected ResponseDto generateResponseSuccessCreated(String message, Object data) {
        return ResponseDto.builder()
                .code(Constants.CODE_SUCCESS)
                .httpStatus(Constants.STATUS_CODE_SUCCESS_CREATED)
                .message(message)
                .data(data)
                .build();
    }

    protected ResponseDto generateResponseSuccess(String message, Object data) {
        return ResponseDto.builder()
                .code(Constants.CODE_SUCCESS)
                .httpStatus(Constants.STATUS_CODE_SUCCESS)
                .message(message)
                .data(data)
                .build();
    }

    protected ResponseDto generateResponseError(String message, Object data) {
        return ResponseDto.builder()
                .code(Constants.MESSAGE_FAILED)
                .httpStatus(Constants.STATUS_CODE_FAILED)
                .message(message)
                .data(data)
                .build();
    }

    protected ResponseDto generateResponseNotFound(String message) {
        return ResponseDto.builder()
                .code(Constants.CODE_ERROR)
                .httpStatus(Constants.STATUS_CODE_NOT_FOUND)
                .message(message)
                .build();
    }

}
