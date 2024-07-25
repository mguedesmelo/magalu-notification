package com.magalu.notification.api.controller;

import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notification")
public class NotificationRestController extends BaseRestController {
	@Autowired
	private NotificationService notificationService;

	@GetMapping
	public ResponseEntity<List<NotificationResponseDto>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(this.notificationService.findAll());
	}

	@Operation(summary = "Get a notification by its id")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "200", description = "Found the notification", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = Book.class)) }),
	  @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
	    content = @Content), 
	  @ApiResponse(responseCode = "404", description = "Notification not found", 
	    content = @Content) })
	@GetMapping(value = "/{id}")
	public ResponseEntity<NotificationResponseDto> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(this.notificationService.findById(id));
	}

	@PostMapping
	public ResponseEntity<NotificationResponseDto> save(@Valid @RequestBody NotificationRequestDto notificationRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.notificationService.save(notificationRequest));
	}

	@PutMapping
	public ResponseEntity<NotificationResponseDto> update(@Valid @RequestBody NotificationRequestDto notificationRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(this.notificationService.update(notificationRequest));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		this.notificationService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
