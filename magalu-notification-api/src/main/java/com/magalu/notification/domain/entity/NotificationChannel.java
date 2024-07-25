package com.magalu.notification.domain.entity;

import java.io.Serial;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
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
@Table(name = "tb_notification_channel")
public class NotificationChannel extends BaseEntity {
    @Serial
	private static final long serialVersionUID = -690452325859131523L;

    @Column(name = "name", length = 10, nullable = false)
    @Pattern(regexp = "sms|push|whatsapp|email", message = "The notification channel must be one of the following: SMS, Push, WhatsApp or Email")
	private String name;

    @Column(name = "send_to", length = 250, nullable = false)
    private String sendTo;
}
