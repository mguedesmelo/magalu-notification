package com.magalu.notification.domain.dto;

import java.io.Serial;
import java.io.Serializable;
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
public class NotificationChannelDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -1378057872778470818L;

    private String type;

    private String sendTo;

    @JsonIgnore
    private LocalDateTime sentDateTime;

    @JsonProperty("sentDateTime")
    public String getSentDateTimeString() {
        if (ObjectUtil.isEmpty(this.sentDateTime)) {
            return "";
        }
        return this.sentDateTime.format(DateTimeFormatter.ofPattern(Constants.PATTERN_DD_MM_YYYY_HH_MM));
    }

    private Boolean active;
}
