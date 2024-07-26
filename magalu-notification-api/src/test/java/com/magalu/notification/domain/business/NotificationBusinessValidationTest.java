package com.magalu.notification.domain.business;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.magalu.notification.core.exception.BusinessException;
import com.magalu.notification.domain.entity.Notification;
import com.magalu.notification.domain.entity.NotificationChannel;

class NotificationBusinessValidationTest {
    private NotificationBusinessValidation validation;

    @BeforeEach
    public void setup() {
        this.validation = new NotificationBusinessValidation();
    }
    @Test
    void checkBeforeSaveThrowsExceptionForEmptyMessage() {
        Notification notification = Notification.builder()
                .scheduledDateTime(LocalDateTime.now())
                .notificationChannels(List.of(
                        NotificationChannel.builder()
                                .type("sms")
                                .sendTo("81 555-5555")
                                .build(),
                        NotificationChannel.builder()
                                .type("push")
                                .sendTo("81 555-5555")
                                .build()))
                .build();

        assertThrows(BusinessException.class, () -> validation.checkBeforeSave(notification));
    }

    @Test
    void checkBeforeSaveThrowsExceptionForEmptySendTo() {
        Notification notification = Notification.builder()
                .message("Message")
                .scheduledDateTime(LocalDateTime.now())
                .notificationChannels(List.of(
                        NotificationChannel.builder()
                                .type("push")
                                .build()))
                .build();

        assertThrows(BusinessException.class, () -> validation.checkBeforeSave(notification));
    }

    @Test
    void checkBeforeSaveThrowsExceptionForInvalidChannel() {
        Notification notification = Notification.builder()
                .message("Message")
                .scheduledDateTime(LocalDateTime.now())
                .notificationChannels(List.of(
                        NotificationChannel.builder()
                                .type("invalid")
                                .sendTo("81 555-5555")
                                .build()))
                .build();

        assertThrows(BusinessException.class, () -> validation.checkBeforeSave(notification));
    }

    @Test
    void checkBeforeSaveThrowsExceptionForEmptyChannelName() {
        Notification notification = Notification.builder()
                .message("Message")
                .scheduledDateTime(LocalDateTime.now())
                .notificationChannels(List.of(
                        NotificationChannel.builder()
                                .sendTo("81 555-5555")
                                .build()))
                .build();

        assertThrows(BusinessException.class, () -> validation.checkBeforeSave(notification));
    }

    @Test
    void checkBeforeSaveThrowsExceptionForEmptyScheduledDateTime() {
        Notification notification = Notification.builder()
                .message("Message")
                .notificationChannels(List.of(
                        NotificationChannel.builder()
                                .type("sms")
                                .sendTo("81 555-5555")
                                .build(),
                        NotificationChannel.builder()
                                .type("push")
                                .sendTo("81 555-5555")
                                .build()))
                .build();

        assertThrows(BusinessException.class, () -> validation.checkBeforeSave(notification));
    }

    @Test
    void checkBeforeSaveThrowsExceptionForEmptyNotificationTypes() {
        Notification notification = Notification.builder()
                .message("Message")
                .scheduledDateTime(LocalDateTime.now())
                .build();

        assertThrows(BusinessException.class, () -> validation.checkBeforeSave(notification));
    }

    @Test
    void checkBeforeSaveDoesNotThrowExceptionForValidRequest() {
        Notification notification = Notification.builder()
                .message("Message")
                .scheduledDateTime(LocalDateTime.now())
                .notificationChannels(List.of(
                        NotificationChannel.builder()
                                .type("sms")
                                .sendTo("81 555-5555")
                                .build(),
                        NotificationChannel.builder()
                                .type("push")
                                .sendTo("81 555-5555")
                                .build()))
                .build();

        assertDoesNotThrow(() -> validation.checkBeforeSave(notification));
    }
}
