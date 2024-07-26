package com.magalu.notification.domain.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.magalu.notification.domain.dto.NotificationChannelDto;
import com.magalu.notification.domain.dto.NotificationRequestDto;
import com.magalu.notification.domain.entity.Notification;
import com.magalu.notification.domain.entity.NotificationChannel;

class NotificationFactoryTest {
    @Test
    void createNotificationReturnsNullForNullNotificationRequest() {
        NotificationFactory notificationFactory = new NotificationFactory();
        assertNull(notificationFactory.createNotification(null));
    }

    @Test
    void createNotificationReturnsNotificationForValidRequest() {
        NotificationFactory notificationFactory = new NotificationFactory();
        NotificationRequestDto notificationRequest = NotificationRequestDto.builder()
                .id(1L)
                .message("Test message")
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

        Notification notification = notificationFactory.createNotification(notificationRequest);

        assertNotNull(notification);
        assertEquals(notificationRequest.getId(), notification.getId());
        assertEquals(notificationRequest.getMessage(), notification.getMessage());
        assertEquals(notificationRequest.getScheduledDateTime(), notification.getScheduledDateTime());
        assertEquals(notificationRequest.getNotificationChannels().size(), notification.getNotificationChannels().size());
    }

    @Test
    void updateNotificationReturnsNullForNullObjects() {
        NotificationFactory notificationFactory = new NotificationFactory();
        assertNull(notificationFactory.updateNotification(null, null));
    }

    @Test
    void updateNotificationUpdatesNotificationForValidObjects() {
        NotificationFactory notificationFactory = new NotificationFactory();
        Notification notification = new Notification();
        NotificationRequestDto notificationRequest = NotificationRequestDto.builder()
                .message("Updated message")
                .scheduledDateTime(LocalDateTime.now())
                .notificationChannels(List.of(
                        NotificationChannelDto.builder()
                                .name("sms")
                                .sendTo("81 555-5555")
                                .build(),
                        NotificationChannelDto.builder()
                                .name("whatsapp")
                                .sendTo("81 555-5555")
                                .build()))
                .build();

        Notification updatedNotification = notificationFactory.updateNotification(notification, notificationRequest);

        assertNotNull(updatedNotification);
        assertEquals(notificationRequest.getMessage(), updatedNotification.getMessage());
        assertEquals(notificationRequest.getScheduledDateTime(), updatedNotification.getScheduledDateTime());
        assertEquals(notificationRequest.getNotificationChannels().size(), updatedNotification.getNotificationChannels().size());
    }

    @Test
    void createNotificationRequestReturnsNullForNullNotification() {
        NotificationFactory notificationFactory = new NotificationFactory();
        assertNull(notificationFactory.createNotificationResponse(null));
    }

    @Test
    void createNotificationRequestReturnsNotificationResponseForValidNotification() {
        NotificationFactory notificationFactory = new NotificationFactory();
        Notification notification = Notification.builder()
                .id(1L)
                .message("Test message")
                .scheduledDateTime(LocalDateTime.now())
                .notificationChannels(List.of(
                        NotificationChannel.builder().name("sms").sendTo("Test receiver").build(),
                        NotificationChannel.builder().name("push").sendTo("Test receiver").build()))
                .build();

        NotificationRequestDto notificationRequest = notificationFactory.createNotificationRequest(notification);

        assertNotNull(notificationRequest);
        assertEquals(notification.getId(), notificationRequest.getId());
        assertEquals(notification.getMessage(), notificationRequest.getMessage());
        assertEquals(notification.getScheduledDateTime(), notificationRequest.getScheduledDateTime());
        assertEquals(notification.getNotificationChannels().size(), notificationRequest.getNotificationChannels().size());
    }
}
