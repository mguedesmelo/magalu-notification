package com.magalu.notification.domain.business;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.magalu.notification.core.exception.data.ErrorMessage;
import com.magalu.notification.core.exception.data.ErrorType;
import com.magalu.notification.core.util.ObjectUtil;
import com.magalu.notification.core.util.StringUtil;

public abstract class BaseBusinessValidation<T> implements Serializable {
	@Serial
	private static final long serialVersionUID = 1816862671656480611L;

	private transient Set<ErrorMessage> errorMessages = new HashSet<>(0);

	protected boolean addErrorMessageMandatoryField(String fieldName) {
		return this.errorMessages.add(ErrorMessage.builder()
				.errorType(ErrorType.MANDATORY)
				.message(fieldName.trim())
				.build());
	}

	protected boolean addErrorMessageInvalidField(String fieldName) {
		return this.errorMessages.add(ErrorMessage.builder()
				.errorType(ErrorType.INVALID)
				.message(fieldName.trim())
				.build());
	}

	protected void clearErrorMessages() {
		this.errorMessages.clear();
	}

	protected Set<ErrorMessage> getErrorMessages() {
		return this.errorMessages;
	}

	protected void validateIn(String text, String fieldName, String... values) {
		if (!StringUtil.contains(text, values)) {
			this.addErrorMessageInvalidField(fieldName);
		}
	}

	protected void validateNotEmpty(Object object, String fieldName) {
		if (ObjectUtil.isEmpty(object)) {
			this.addErrorMessageMandatoryField(fieldName);
		}
	}

	public abstract void checkBeforeSave(T object);
}
