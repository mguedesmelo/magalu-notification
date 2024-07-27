package com.magalu.notification.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.magalu.notification.domain.entity.Notification;

@Repository
public interface NotificationRepository extends BaseRepository<Notification> {
    @Query("SELECT n FROM Notification n JOIN n.notificationChannels nc "
            + "WHERE n.scheduledDateTime <= :now "
            + "AND nc.sentDateTime IS NULL "
            + "AND nc.active = TRUE")
    List<Notification> findAllNotificationsToSend(LocalDateTime now);

    @Query("SELECT DISTINCT n FROM Notification n JOIN n.notificationChannels nc "
    		+ "WHERE nc.sentDateTime IS NULL "
    		+ "AND nc.active = true")
    List<Notification> findAllScheduledNotifications(LocalDateTime now);
}
