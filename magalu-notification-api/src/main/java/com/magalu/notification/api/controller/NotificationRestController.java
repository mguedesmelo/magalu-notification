package com.magalu.notification.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.magalu.notification.domain.dto.NotificationRequestDto;
import com.magalu.notification.domain.dto.NotificationResponseDto;
import com.magalu.notification.service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class NotificationRestController extends BaseRestController {
	private NotificationService notificationService;

	@Operation(summary = "Get notification list")
	@GetMapping
	public ResponseEntity<List<NotificationResponseDto>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.notificationService.findAll());
	}

	@Operation(summary = "Get a notification by its id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<NotificationResponseDto> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.notificationService.findById(id));
	}

	@Operation(summary = "Create a new notification")
	@PostMapping
	public ResponseEntity<NotificationResponseDto> save(@Valid @RequestBody NotificationRequestDto notificationRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.notificationService.save(notificationRequest));
	}

	@Operation(summary = "Update existing notification by its id")
	@PutMapping
	public ResponseEntity<NotificationResponseDto> update(@Valid @RequestBody NotificationRequestDto notificationRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(this.notificationService.update(notificationRequest));
	}

	@Operation(summary = "Delete existing notification by its id")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		this.notificationService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
