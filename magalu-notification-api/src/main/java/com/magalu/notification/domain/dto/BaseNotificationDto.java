package com.magalu.notification.domain.dto;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.magalu.notification.core.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseNotificationDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 3601782508509320166L;

    private Long id;

    @JsonFormat(pattern = Constants.PATTERN_DD_MM_YYYY_HH_MM)
    private LocalDateTime scheduledDateTime;

    private String message;

    @Builder.Default
    private List<NotificationChannelDto> notificationChannels = new ArrayList<>(0);

    @JsonIgnore
    public Type getType() {
        return getClass().getGenericSuperclass();
    }
}
