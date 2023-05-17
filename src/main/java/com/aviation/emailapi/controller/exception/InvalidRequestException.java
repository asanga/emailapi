package com.aviation.emailapi.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class InvalidRequestException extends RuntimeException{
    private int errorCode;
    private String errorMsg;
}
