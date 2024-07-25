package com.magalu.notification.domain.business;

import java.io.Serial;

import org.springframework.stereotype.Component;

import com.magalu.notification.core.exception.BusinessException;
import com.magalu.notification.domain.dto.NotificationRequestDto;

@Component
public class NotificationBusinessValidation extends BaseBusinessValidation<NotificationRequestDto> {
	@Serial
	private static final long serialVersionUID = -5677795817075873892L;

	@Override
	public void checkBeforeSave(NotificationRequestDto notificationRequest) {
		validateNotEmpty(notificationRequest.getMessage(), "Message");
		validateNotEmpty(notificationRequest.getScheduledDateTime(), "Scheduled date");
		validateNotEmpty(notificationRequest.getNotificationChannels(), "Notification type");
		notificationRequest.getNotificationChannels().forEach(channel -> {
			validateNotEmpty(channel.getName(), "notification channel");
			validateNotEmpty(channel.getSendTo(), "send to");

			validateIn(channel.getName(), "notification channel", "sms", "push", "whatsapp", "email");
		});

		BusinessException.throwIfHasErrorMessages(getErrorMessages());
	}
}
