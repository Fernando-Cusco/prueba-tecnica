package app.store.microserviceaccount.common;

import org.springframework.http.HttpStatus;

public class Constants {

    private Constants() {}

    public static final String MESSAGE_SUCCESS = "Success";
    public static final String MESSAGE_FAILED = "Failed";

    public static final String CODE_SUCCESS = "000";
    public static final String CODE_ERROR = "001";


    public static final Integer STATUS_CODE_SUCCESS = HttpStatus.OK.value();
    public static final Integer STATUS_CODE_SUCCESS_CREATED = HttpStatus.CREATED.value();
    public static final Integer STATUS_CODE_NOT_FOUND = HttpStatus.NOT_FOUND.value();
    public static final Integer STATUS_CODE_FAILED = HttpStatus.INTERNAL_SERVER_ERROR.value();
}
