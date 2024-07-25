package com.magalu.notification.domain.business;

import java.io.Serial;

import org.springframework.stereotype.Component;

import com.magalu.notification.core.exception.BusinessException;
import com.magalu.notification.domain.entity.Notification;

@Component
public class NotificationBusinessValidation extends BaseBusinessValidation<Notification> {
	@Serial
	private static final long serialVersionUID = -5677795817075873892L;

	@Override
	public void checkBeforeSave(Notification notification) {
		clearErrorMessages();
		validateNotEmpty(notification.getMessage(), "Message");
		validateNotEmpty(notification.getScheduledDateTime(), "Scheduled date");
		validateNotEmpty(notification.getNotificationChannels(), "Notification type");
		notification.getNotificationChannels().forEach(channel -> {
			validateNotEmpty(channel.getName(), "notification channel");
			validateNotEmpty(channel.getSendTo(), "send to");

			validateIn(channel.getName(), "notification channel", "sms", "push", "whatsapp", "email");
		});

		BusinessException.throwIfHasErrorMessages(getErrorMessages());
	}
}
