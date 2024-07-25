package com.magalu.notification.sender;

import org.springframework.stereotype.Component;

@Component
public class SmsSender extends BaseSender {
	@Override
	public void send(String receiver, String message) {
		System.out.println("SMS notification not yet implemented");
	}
}
