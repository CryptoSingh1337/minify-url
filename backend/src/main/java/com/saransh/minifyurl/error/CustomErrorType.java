package com.saransh.minifyurl.error;

/**
 * Created by CryptoSingh1337 on 6/17/2021
 */
public class CustomErrorType {

    private final String errorMessage;

    public CustomErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
