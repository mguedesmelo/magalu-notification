package com.magalu.notification.scheduling;

import java.io.Serial;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.magalu.notification.core.util.Constants;
import com.magalu.notification.service.NotificationService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SendScheduledNotificationsTask extends BaseTask {
    @Serial
    private static final long serialVersionUID = -2248033744236928140L;
    private NotificationService notificationService;

    @Scheduled(fixedRate = Constants.TASK_INTERVAL)
    public void send() {
        this.notificationService.sendNotifications();
    }
}
