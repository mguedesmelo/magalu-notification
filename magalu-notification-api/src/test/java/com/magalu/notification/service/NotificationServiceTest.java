package com.magalu.notification.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.magalu.notification.core.exception.RecordNotFoundException;
import com.magalu.notification.domain.business.NotificationBusinessValidation;
import com.magalu.notification.domain.dto.NotificationRequestDto;
import com.magalu.notification.domain.dto.NotificationResponseDto;
import com.magalu.notification.domain.entity.Notification;
import com.magalu.notification.domain.entity.NotificationChannel;
import com.magalu.notification.domain.factory.NotificationFactory;
import com.magalu.notification.repository.NotificationRepository;
import com.magalu.notification.sender.SenderFactory;
import com.magalu.notification.sender.SmsSender;

class NotificationServiceTest {
    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationFactory notificationFactory;

    @Mock
	private NotificationBusinessValidation notificationBusinessValidation;

    @Mock
	private SenderFactory senderFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllReturnsAllNotifications() {
        NotificationResponseDto notificationResponse = new NotificationResponseDto();
        when(notificationRepository.findAll()).thenReturn(Arrays.asList(new Notification()));
        when(notificationFactory.createNotificationResponse(any(Notification.class))).thenReturn(notificationResponse);

        List<NotificationResponseDto> result = notificationService.findAll();

        assertEquals(1, result.size());
        assertEquals(notificationResponse, result.get(0));
    }

    @Test
    void findByIdReturnsNotification() {
    	NotificationResponseDto notificationResponse = new NotificationResponseDto();
        when(notificationRepository.findById(anyLong())).thenReturn(Optional.of(new Notification()));
        when(notificationFactory.createNotificationResponse(any(Notification.class))).thenReturn(notificationResponse);

        NotificationResponseDto result = notificationService.findById(1L);

        assertEquals(notificationResponse, result);
    }

    @Test
    void findByIdThrowsExceptionWhenNotFound() {
        when(notificationRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> notificationService.findById(1L));
    }

    @Test
    void saveCreatesAndSavesNotification() {
    	NotificationRequestDto notificationRequest = new NotificationRequestDto();
        Notification notification = new Notification();
        when(notificationFactory.createNotification(any(NotificationRequestDto.class))).thenReturn(notification);
        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);
        when(notificationFactory.createNotificationResponse(any(Notification.class)))
        		.thenReturn(new NotificationResponseDto());

        NotificationResponseDto notificationResponse = notificationService.save(notificationRequest);

        assertNotNull(notificationResponse);
    }

    @Test
    void updateUpdatesAndSavesNotification() {
    	NotificationRequestDto notificationRequest = new NotificationRequestDto();
        Notification notification = new Notification();
        when(notificationRepository.findById(any())).thenReturn(Optional.of(notification));
        when(notificationFactory.updateNotification(any(Notification.class), any(NotificationRequestDto.class))).thenReturn(notification);
        when(notificationRepository.save(any(Notification.class))).thenReturn(notification);
        when(notificationFactory.createNotificationResponse(any(Notification.class)))
        		.thenReturn(new NotificationResponseDto());

        NotificationResponseDto notificationResponse = notificationService.update(notificationRequest);

        assertNotNull(notificationResponse);
    }

    @Test
    void updateThrowsExceptionWhenNotFound() {
        NotificationRequestDto notificationRequest = new NotificationRequestDto();
        when(notificationRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> notificationService.update(notificationRequest));
    }

    @Test
    void deleteDeletesNotification() {
        when(notificationRepository.existsById(anyLong())).thenReturn(true);
        doNothing().when(notificationRepository).deleteById(anyLong());

        notificationService.delete(1L);

        verify(notificationRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteThrowsExceptionWhenNotFound() {
        when(notificationRepository.existsById(anyLong())).thenReturn(false);

        assertThrows(RecordNotFoundException.class, () -> notificationService.delete(1L));
    }

    @Test
    void sendNotificationsSendsAndUpdatesNotifications() {
        Notification notification = new Notification();
        notification.setScheduledDateTime(LocalDateTime.now().minusHours(1));
        notification.setNotificationChannels(List.of(
        		NotificationChannel.builder().name("sms").build()
        		));
		when(notificationRepository
				.findByScheduledDateTimeBeforeAndSentDateTimeIsNull(any(LocalDateTime.class)))
				.thenReturn(List.of(notification));
		when(senderFactory.createSender(any())).thenReturn(new SmsSender());
		when(notificationRepository.save(any(Notification.class))).thenReturn(notification);

        notificationService.sendNotifications();

        assertNotNull(notification.getSentDateTime());
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }
}
