package com.magalu.notification.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.magalu.notification.domain.entity.Notification;

@Repository
public interface NotificationRepository extends BaseRepository<Notification> {
    List<Notification> findByScheduledDateTimeBeforeAndSentDateTimeIsNull(LocalDateTime dateTime);
}
