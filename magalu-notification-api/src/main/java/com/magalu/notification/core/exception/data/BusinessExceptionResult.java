package com.magalu.notification.core.exception.data;

import java.util.Set;

import com.magalu.notification.core.exception.BusinessException;

public class BusinessExceptionResult {
    private BusinessException businessException;

    public BusinessExceptionResult(BusinessException businessException) {
        this.businessException = businessException;
    }

    public Set<String> getInvalidFields() {
        return this.businessException.getErrorMessagesByType(ErrorType.INVALID);
    }

    public Set<String> getMandatoryFields() {
        return this.businessException.getErrorMessagesByType(ErrorType.MANDATORY);
    }
}