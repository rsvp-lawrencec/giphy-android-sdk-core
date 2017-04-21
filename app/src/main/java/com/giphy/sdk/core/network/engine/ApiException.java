package com.giphy.sdk.core.network.engine;

import com.giphy.sdk.core.network.response.ErrorResponse;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class ApiException extends Exception {

    private final ErrorResponse errorResponse;

    public ApiException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ApiException(String detailMessage, ErrorResponse errorResponse) {
        super(detailMessage);
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
