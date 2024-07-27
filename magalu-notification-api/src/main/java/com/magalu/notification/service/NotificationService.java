package com.magalu.notification.service;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.magalu.notification.core.exception.BusinessException;
import com.magalu.notification.core.exception.RecordNotFoundException;
import com.magalu.notification.core.util.Constants;
import com.magalu.notification.core.util.ObjectUtil;
import com.magalu.notification.core.util.StringUtil;
import com.magalu.notification.domain.business.NotificationBusinessValidation;
import com.magalu.notification.domain.dto.NotificationRequestDto;
import com.magalu.notification.domain.dto.NotificationResponseDto;
import com.magalu.notification.domain.entity.Notification;
import com.magalu.notification.domain.entity.NotificationChannel;
import com.magalu.notification.domain.factory.NotificationFactory;
import com.magalu.notification.repository.NotificationRepository;
import com.magalu.notification.sender.BaseSender;
import com.magalu.notification.sender.SenderFactory;

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
            }).orElseThrow(() -> new RecordNotFoundException("notification"));
    }

    public void delete(Long id) {
        RecordNotFoundException.throwIf("notification", !this.notificationRepository.existsById(id));
        this.notificationRepository.deleteById(id);
    }

    public NotificationResponseDto activateDeactivate(Long id, String notificationType, Boolean active) {
		return this.notificationRepository.findById(id).map(notification -> {
			List<NotificationChannel> channelsToUpdate = notification.getNotificationChannels();
			if (StringUtil.isNotEmpty(notificationType)) {
				BusinessException.throwInvalidIf("Invalid notification type",
						StringUtil.doesNotContains(notificationType, Constants.VALID_NOTIFICATION_TYPES));
				channelsToUpdate = notification.getNotificationChannels()
						.stream()
						.filter(notificationChannel -> notificationType.equalsIgnoreCase(notificationChannel.getType()))
						.toList();
			}
			channelsToUpdate.forEach(notificationChannel -> notificationChannel.setActive(active));
			return notificationFactory.createNotificationResponse(this.notificationRepository.save(notification));
		}).orElseThrow(() -> new RecordNotFoundException("notification"));
    }

    public List<NotificationResponseDto> findAllScheduledNotifications() {
        return notificationRepository.findAllScheduledNotifications(LocalDateTime.now())
                .stream()
                .map(notificationFactory::createNotificationResponse)
                .toList();
    }

    public void sendNotifications() {
		List<Notification> notificationsToSend = notificationRepository.findAllNotificationsToSend(LocalDateTime.now());
		notificationsToSend.forEach(notification -> {
			notification.getNotificationChannels()
					.stream()
					.filter(notificationChannel -> 
							notificationChannel.getActive() && 
							ObjectUtil.isEmpty(notificationChannel.getSentDateTime()))
					.toList()
					.forEach(notificationChannel -> {
						BaseSender sender = senderFactory.createSender(notificationChannel.getType());
						// Add improvement to resend notification in case of failure
						if (sender.send(notificationChannel.getSendTo(), notification.getMessage())) {
							notificationChannel.setSentDateTime(LocalDateTime.now());
						}
					});
			notificationRepository.save(notification);
		});
    }
}
