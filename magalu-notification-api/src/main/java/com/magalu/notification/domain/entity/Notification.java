package com.magalu.notification.domain.entity;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tb_notification")
public class Notification extends BaseEntity {
	@Serial
	private static final long serialVersionUID = 6911531923286011278L;

	@Column(name = "scheduled_date_time", nullable = false)
	private LocalDateTime scheduledDateTime;

	@Column(name = "sent_date_time")
	private LocalDateTime sentDateTime;

	@Column(name = "message", length = 200, nullable = false)
	private String message;

	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<NotificationChannel> notificationChannels = new ArrayList<>(0);
}
