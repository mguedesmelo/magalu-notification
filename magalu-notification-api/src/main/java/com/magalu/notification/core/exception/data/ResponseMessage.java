package com.magalu.notification.core.exception.data;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseMessage implements Serializable {
    @Serial
    private static final long serialVersionUID = -1968779282906288944L;
    private int errorCode;
    private String message;
}
