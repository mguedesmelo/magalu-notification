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
						.scheduledDateTime(LocalDateTime.now().plusMinutes(3))
						.notificationChannels(List.of(
								NotificationChannelDto.builder()
										.name("sms")
										.sendTo("81 555-5555")
										.build(),
								NotificationChannelDto.builder()
										.name("push")
										.sendTo("81 555-5555")
										.build()))
						.build());

		notificationService.save(
				NotificationRequestDto.builder()
						.id(null)
						.message("Another system test")
						.scheduledDateTime(LocalDateTime.now().plusMinutes(5))
						.notificationChannels(List.of(
								NotificationChannelDto.builder()
										.name("sms")
										.sendTo("81 555-5555")
										.build(),
								NotificationChannelDto.builder()
										.name("push")
										.sendTo("81 555-5555")
										.build(),
								NotificationChannelDto.builder()
										.name("whatsapp")
										.sendTo("81 555-5555")
										.build()
						))
				.build());
	}
}
