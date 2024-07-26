package com.magalu.notification.core.exception.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@AllArgsConstructor
@SuperBuilder
public class ErrorMessage {
    private ErrorType errorType;
    private String message;
}
