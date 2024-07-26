package com.magalu.notification.service;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.magalu.notification.core.exception.RecordNotFoundException;
import com.magalu.notification.domain.business.NotificationBusinessValidation;
import com.magalu.notification.domain.dto.NotificationRequestDto;
import com.magalu.notification.domain.dto.NotificationResponseDto;
import com.magalu.notification.domain.entity.Notification;
import com.magalu.notification.domain.factory.NotificationFactory;
import com.magalu.notification.repository.NotificationRepository;
import com.magalu.notification.sender.SenderFactory;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NotificationService extends BaseService {
    @Serial
    private static final long serialVersionUID = -7822029479148825639L;
    private transient NotificationFactory notificationFactory;
    private transient NotificationRepository notificationRepository;
    private NotificationBusinessValidation notificationBusinessValidation;
    private transient SenderFactory senderFactory;

    public List<NotificationResponseDto> findAll() {
        return this.notificationRepository.findAll()
                .stream()
                .map(notificationFactory::createNotificationResponse)
                .toList();
    }

    public NotificationResponseDto findById(Long id) {
        return this.notificationRepository.findById(id)
                .map(notificationFactory::createNotificationResponse)
                .orElseThrow(() -> new RecordNotFoundException("notification"));
    }

    public NotificationResponseDto save(NotificationRequestDto notificationRequest) {
        Notification notification = notificationFactory.createNotification(notificationRequest);
        this.notificationBusinessValidation.checkBeforeSave(notification);
        return notificationFactory.createNotificationResponse(this.notificationRepository.save(notification));
    }

    public NotificationResponseDto update(NotificationRequestDto notificationRequest) {
        return this.notificationRepository.findById(notificationRequest.getId()).map(notification -> {
            notification = this.notificationFactory.updateNotification(notification, notificationRequest);
            this.notificationBusinessValidation.checkBeforeSave(notification);
            return notificationFactory.createNotificationResponse(this.notificationRepository.save(notification));
            }).orElseThrow(() -> new RecordNotFoundException("user"));
    }

    public void delete(Long id) {
        RecordNotFoundException.throwIf("notification", !this.notificationRepository.existsById(id));
        this.notificationRepository.deleteById(id);
    }

    @Transactional
    public void sendNotifications() {
        List<Notification> notificationsToSend = notificationRepository.findByScheduledDateTimeBeforeAndSentDateTimeIsNull(LocalDateTime.now());
        notificationsToSend.forEach(notification -> {
            notification.getNotificationChannels().forEach(notificationChannel -> this.senderFactory
                    .createSender(notificationChannel.getName())
                    .send(notificationChannel.getSendTo(), notification.getMessage()));
            notification.setSentDateTime(LocalDateTime.now());
            notificationRepository.save(notification);
        });
    }
}
