package com.magalu.notification.core.exception;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.magalu.notification.core.exception.data.ErrorMessage;
import com.magalu.notification.core.exception.data.ErrorType;

public class BusinessException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = -7006992541930435245L;

	private final transient Set<ErrorMessage> errorMessages = new HashSet<>(0);

    public Set<String> getErrorMessagesByType(ErrorType errorType) {
        return errorMessages.stream()
                .filter(errorMessage -> errorMessage.getErrorType().equals(errorType))
                .map(ErrorMessage::getMessage)
                .collect(Collectors.toSet());
    }

	public BusinessException(Set<ErrorMessage> errorMessages) {
		this.errorMessages.addAll(errorMessages);
	}

    public static BusinessException throwMe(ErrorMessage message) {
        return new BusinessException(Set.of(message));
    }

    public static void throwIf(ErrorMessage errorMessage, boolean condition) {
        if (condition) {
            throw new BusinessException(Set.of(errorMessage));
        }
    }

    public static void throwIfHasErrorMessages(Set<ErrorMessage> errorMessages) {
        if (!errorMessages.isEmpty()) {
            throw new BusinessException(errorMessages);
        }
    }

    public Set<String> getErrorMessagesInvalidFields() {
    	return this.getErrorMessagesByType(ErrorType.INVALID);
    }

    public Set<String> getErrorMessagesMandatoryFields() {
    	return this.getErrorMessagesByType(ErrorType.MANDATORY);
    }
}
