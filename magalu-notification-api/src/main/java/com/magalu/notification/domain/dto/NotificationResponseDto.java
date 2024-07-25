package com.magalu.notification.domain.dto;

import java.io.Serial;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.magalu.notification.core.util.Constants;
import com.magalu.notification.core.util.ObjectUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class NotificationResponseDto extends BaseNotificationDto {
	@Serial
	private static final long serialVersionUID = 4200310800305934549L;

	@JsonIgnore
	private LocalDateTime creationDate;

	@JsonProperty("creationDate")
	public String getCreationDateString() {
		if (ObjectUtil.isEmpty(this.creationDate)) {
			return "";
		}
		return this.creationDate.format(DateTimeFormatter.ofPattern(Constants.PATTERN_DD_MM_YYYY_HH_MM));
	}
}
