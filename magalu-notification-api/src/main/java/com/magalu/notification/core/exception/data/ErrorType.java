package com.magalu.notification.core.exception.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {
	MANDATORY(1, "Mandatory"), 
	INVALID(2, "Invalid");

	private Integer id;

	private String name;

	public static ErrorType getEnumByName(String name) {
		for (ErrorType errorType : ErrorType.values()) {
			if (errorType.getName().equalsIgnoreCase(name)) {
				return errorType;
			}
		}
		return null;
	}

	public static ErrorType getEnumById(Integer id) {
		for (ErrorType errorTypes : ErrorType.values()) {
			if (errorTypes.getId().equals(id)) {
				return errorTypes;
			}
		}
		return null;
	}
}
