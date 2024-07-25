package com.magalu.notification.domain.dto;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@SuperBuilder
public class NotificationRequestDto extends BaseNotificationDto {
    @Serial
	private static final long serialVersionUID = -5647260749974794842L;

}
