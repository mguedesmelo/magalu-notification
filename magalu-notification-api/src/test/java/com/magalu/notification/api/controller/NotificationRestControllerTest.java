package com.magalu.notification.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.magalu.notification.domain.dto.NotificationRequestDto;
import com.magalu.notification.domain.dto.NotificationResponseDto;
import com.magalu.notification.service.NotificationService;

class NotificationRestControllerTest {
    @InjectMocks
    private NotificationRestController notificationRestController;

    @Mock
    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(notificationRestController, "notificationService", notificationService);
    }

    @Test
    void findAllReturnsOkStatusAndList() {
        List<NotificationResponseDto> mockList = List.of(
                new NotificationResponseDto(), 
                new NotificationResponseDto());
        Mockito.when(notificationService.findAll()).thenReturn(mockList);

        ResponseEntity<List<NotificationResponseDto>> response = notificationRestController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockList, response.getBody());
    }

    @Test
    void findByIdReturnsOkStatusAndResponse() {
        NotificationResponseDto mockResponse = new NotificationResponseDto();
        Mockito.when(notificationService.findById(1L)).thenReturn(mockResponse);

        ResponseEntity<NotificationResponseDto> response = notificationRestController.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResponse, response.getBody());
    }

    @Test
    void saveReturnsCreatedStatusAndResponse() {
        NotificationRequestDto notificationRequest = new NotificationRequestDto();
        NotificationResponseDto notificationResponse = new NotificationResponseDto();
        Mockito.when(notificationService.save(notificationRequest)).thenReturn(notificationResponse);

        ResponseEntity<NotificationResponseDto> response = notificationRestController.save(notificationRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(notificationResponse, response.getBody());
    }

    @Test
    void updateReturnsOkStatusAndResponse() {
        NotificationRequestDto notificationRequest = new NotificationRequestDto();
        NotificationResponseDto notificationResponse = new NotificationResponseDto();
        Mockito.when(notificationService.update(notificationRequest)).thenReturn(notificationResponse);

        ResponseEntity<NotificationResponseDto> response = notificationRestController.update(notificationRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notificationResponse, response.getBody());
    }

    @Test
    void deleteReturnsNoContentStatus() {
        Mockito.doNothing().when(notificationService).delete(Mockito.any());
        ResponseEntity<Void> response = notificationRestController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
