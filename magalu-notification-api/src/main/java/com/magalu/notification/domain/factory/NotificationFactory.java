package com.magalu.notification.domain.factory;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.magalu.notification.core.util.ObjectUtil;
import com.magalu.notification.domain.dto.NotificationChannelDto;
import com.magalu.notification.domain.dto.NotificationRequestDto;
import com.magalu.notification.domain.dto.NotificationResponseDto;
import com.magalu.notification.domain.entity.Notification;
import com.magalu.notification.domain.entity.NotificationChannel;

@Component
public class NotificationFactory {
    public Notification createNotification(NotificationRequestDto notificationRequest) {
        if (ObjectUtil.isEmpty(notificationRequest)) {
            return null;
        }
        return Notification.builder()
                .id(notificationRequest.getId())
                .creationDate(LocalDateTime.now())
                .message(notificationRequest.getMessage())
                .scheduledDateTime(notificationRequest.getScheduledDateTime())
                .notificationChannels(notificationRequest.getNotificationChannels()
                        .stream()
                        .map(notificationChannel -> NotificationChannel.builder()
                        		.creationDate(LocalDateTime.now())
                                .type(notificationChannel.getType())
                                .sendTo(notificationChannel.getSendTo())
                                .sentDateTime(null)
                                .active(Boolean.TRUE)
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    public Notification updateNotification(Notification notification, 
            NotificationRequestDto notificationRequest) {
        if (ObjectUtil.hasSomeEmptyObject(notification, notificationRequest)) {
            return null;
        }
        notification.setMessage(notificationRequest.getMessage());
        notification.setScheduledDateTime(notificationRequest.getScheduledDateTime());
        notification.setNotificationChannels(notificationRequest.getNotificationChannels()
                .stream()
                .map(notificationChannelRequest -> NotificationChannel.builder()
                        .type(notificationChannelRequest.getType())
                        .sendTo(notificationChannelRequest.getSendTo())
                        .sentDateTime(notificationChannelRequest.getSentDateTime())
                        .active(notificationChannelRequest.getActive())
                        .build())
                .collect(Collectors.toList())
                );
        return notification;
    }

    public NotificationResponseDto createNotificationResponse(Notification notification) {
        if (ObjectUtil.isEmpty(notification)) {
            return null;
        }
        return NotificationResponseDto.builder()
                .id(notification.getId())
                .creationDate(notification.getCreationDate())
                .message(notification.getMessage())
                .scheduledDateTime(notification.getScheduledDateTime())
                .notificationChannels(notification.getNotificationChannels()
                        .stream()
                        .map(notificationChannel -> NotificationChannelDto.builder()
                                .type(notificationChannel.getType())
                                .sendTo(notificationChannel.getSendTo())
                                .active(notificationChannel.getActive())
                                .sentDateTime(notificationChannel.getSentDateTime())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }


    public NotificationRequestDto createNotificationRequest(Notification notification) {
        if (ObjectUtil.isEmpty(notification)) {
            return null;
        }
        return NotificationRequestDto.builder()
                .id(notification.getId())
                .message(notification.getMessage())
                .scheduledDateTime(notification.getScheduledDateTime())
                .notificationChannels(notification.getNotificationChannels()
                        .stream()
                        .map(notificationChannel -> NotificationChannelDto.builder()
                                .type(notificationChannel.getType())
                                .sendTo(notificationChannel.getSendTo())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
