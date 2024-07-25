package com.magalu.notification.scheduling;

import java.io.Serial;
import java.io.Serializable;

public abstract class BaseTask implements Serializable {
	@Serial
	private static final long serialVersionUID = 8222896613614259061L;

	public abstract void send();
}
