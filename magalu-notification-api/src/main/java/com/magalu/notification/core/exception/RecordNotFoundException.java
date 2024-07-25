package com.magalu.notification.core.exception;

import java.io.Serial;

public class RecordNotFoundException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 4000265776953547670L;

	public RecordNotFoundException(String nomeEntidade) {
		super(String.format("Unable to locate the %s", nomeEntidade.trim()));
	}

    public static void throwMe(String message) {
    	throw new RecordNotFoundException(message);
    }

    public static void throwIf(String nomeEntidade, boolean condition) {
        if (condition) {
            throw new RecordNotFoundException(String.format("Unable to locate the %s", nomeEntidade.trim()));
        }
    }
}
