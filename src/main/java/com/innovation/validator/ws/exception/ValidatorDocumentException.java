package com.innovation.validator.ws.exception;

import java.util.Collections;
import java.util.List;

public class ValidatorDocumentException extends RuntimeException {

    private final List<String> reasons;

    public ValidatorDocumentException(String message) {
        super(message);
        reasons = null;
    }

    public ValidatorDocumentException(String message, Throwable cause) {
        super(message, cause);
        reasons = null;
    }

    public ValidatorDocumentException(Throwable cause) {
        super(cause);
        reasons = null;
    }

    public ValidatorDocumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        reasons = null;
    }

    public ValidatorDocumentException(String message, List<String> reasons) {
        super(message);
        this.reasons = reasons;
    }

    public ValidatorDocumentException(String message, String reason) {
        super(message);
        this.reasons = Collections.singletonList(reason);
    }
}