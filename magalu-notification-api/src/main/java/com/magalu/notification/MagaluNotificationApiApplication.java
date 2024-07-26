package com.magalu.notification;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.magalu.notification.domain.dto.NotificationChannelDto;
import com.magalu.notification.domain.dto.NotificationRequestDto;
import com.magalu.notification.service.NotificationService;

@SpringBootApplication
@EnableScheduling
public class MagaluNotificationApiApplication implements CommandLineRunner {
    @Autowired
    private NotificationService notificationService;

    public static void main(String[] args) {
        SpringApplication.run(MagaluNotificationApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        notificationService.save(
                NotificationRequestDto.builder()
                        .id(null)
                        .message("System test")
                        .scheduledDateTime(LocalDateTime.now().plusMinutes(1))
                        .notificationChannels(List.of(
                                NotificationChannelDto.builder()
                                        .type("sms")
                                        .sendTo("81 111-1111")
                                        .build(),
                                NotificationChannelDto.builder()
                                        .type("push")
                                        .sendTo("81 222-2222")
                                        .build()))
                        .build());

        notificationService.save(
                NotificationRequestDto.builder()
                        .id(null)
                        .message("Another system test")
                        .scheduledDateTime(LocalDateTime.now().plusMinutes(1))
                        .notificationChannels(List.of(
                                NotificationChannelDto.builder()
                                        .type("sms")
                                        .sendTo("81 333-3333")
                                        .build(),
                                NotificationChannelDto.builder()
                                        .type("push")
                                        .sendTo("81 444-4444")
                                        .build(),
                                NotificationChannelDto.builder()
                                        .type("whatsapp")
                                        .sendTo("81 555-5555")
                                        .build()
                        ))
                .build());

		notificationService.save(
				NotificationRequestDto.builder()
						.id(null)
						.message("Pending notification")
						.scheduledDateTime(LocalDateTime.now().plusMinutes(1))
						.notificationChannels(List.of(
								NotificationChannelDto.builder()
										.type("sms")
										.sendTo("81 666-6666")
										.build(),
								NotificationChannelDto.builder()
										.type("push")
										.sendTo("81 777-7777")
										.build()
						))
				.build());
    }
}
