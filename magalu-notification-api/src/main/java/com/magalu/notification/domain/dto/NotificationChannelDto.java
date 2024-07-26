package com.magalu.notification.domain.dto;

import java.io.Serial;
import java.io.Serializable;

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
public class NotificationChannelDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -1378057872778470818L;

    private String name;

    private String sendTo;
}
