package com.magalu.notification.domain.business;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.magalu.notification.core.exception.BusinessException;
import com.magalu.notification.domain.dto.NotificationChannelDto;
import com.magalu.notification.domain.dto.NotificationRequestDto;

class NotificationBusinessValidationTest {
	private NotificationBusinessValidation validation;

    @BeforeEach
    public void setup() {
        this.validation = new NotificationBusinessValidation();
    }
    @Test
    void checkBeforeSaveThrowsExceptionForEmptyMessage() {
		NotificationRequestDto notificationRequest = NotificationRequestDto.builder()
				.scheduledDateTime(LocalDateTime.now())
				.notificationChannels(List.of(
						NotificationChannelDto.builder()
								.name("sms")
								.sendTo("81 555-5555")
								.build(),
						NotificationChannelDto.builder()
								.name("push")
								.sendTo("81 555-5555")
								.build()))
				.build();

        assertThrows(BusinessException.class, () -> validation.checkBeforeSave(notificationRequest));
    }

    @Test
    void checkBeforeSaveThrowsExceptionForEmptySendTo() {
        NotificationRequestDto notificationRequest = NotificationRequestDto.builder()
        		.message("Message")
        		.scheduledDateTime(LocalDateTime.now())
        		.notificationChannels(List.of(
						NotificationChannelDto.builder()
								.name("push")
								.build()))
        		.build();

        assertThrows(BusinessException.class, () -> validation.checkBeforeSave(notificationRequest));
    }

    @Test
    void checkBeforeSaveThrowsExceptionForInvalidChannel() {
        NotificationRequestDto notificationRequest = NotificationRequestDto.builder()
        		.message("Message")
        		.scheduledDateTime(LocalDateTime.now())
        		.notificationChannels(List.of(
						NotificationChannelDto.builder()
								.name("invalid")
								.sendTo("81 555-5555")
								.build()))
        		.build();

        assertThrows(BusinessException.class, () -> validation.checkBeforeSave(notificationRequest));
    }

    @Test
    void checkBeforeSaveThrowsExceptionForEmptyChannelName() {
        NotificationRequestDto notificationRequest = NotificationRequestDto.builder()
        		.message("Message")
        		.scheduledDateTime(LocalDateTime.now())
        		.notificationChannels(List.of(
						NotificationChannelDto.builder()
								.sendTo("81 555-5555")
								.build()))
        		.build();

        assertThrows(BusinessException.class, () -> validation.checkBeforeSave(notificationRequest));
    }

    @Test
    void checkBeforeSaveThrowsExceptionForEmptyScheduledDateTime() {
		NotificationRequestDto notificationRequest = NotificationRequestDto.builder()
				.message("Message")
				.notificationChannels(List.of(
						NotificationChannelDto.builder()
								.name("sms")
								.sendTo("81 555-5555")
								.build(),
						NotificationChannelDto.builder()
								.name("push")
								.sendTo("81 555-5555")
								.build()))
				.build();

        assertThrows(BusinessException.class, () -> validation.checkBeforeSave(notificationRequest));
    }

    @Test
    void checkBeforeSaveThrowsExceptionForEmptyNotificationTypes() {
        NotificationRequestDto notificationRequest = NotificationRequestDto.builder()
				.message("Message")
				.scheduledDateTime(LocalDateTime.now())
				.build();

        assertThrows(BusinessException.class, () -> validation.checkBeforeSave(notificationRequest));
    }

    @Test
    void checkBeforeSaveDoesNotThrowExceptionForValidRequest() {
        NotificationRequestDto notificationRequest = NotificationRequestDto.builder()
				.message("Message")
				.scheduledDateTime(LocalDateTime.now())
				.notificationChannels(List.of(
						NotificationChannelDto.builder()
								.name("sms")
								.sendTo("81 555-5555")
								.build(),
						NotificationChannelDto.builder()
								.name("push")
								.sendTo("81 555-5555")
								.build()))
				.build();

        assertDoesNotThrow(() -> validation.checkBeforeSave(notificationRequest));
    }
}
