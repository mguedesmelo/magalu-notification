package com.magalu.notification.sender;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.magalu.notification.core.util.ObjectUtil;

@Component
public class SenderFactory {
    private Map<String, BaseSender> mapSenders = new HashMap<>(0);

    public BaseSender createSender(String notificationType) {
        BaseSender sender = null;
        String typeUpper = notificationType.toUpperCase();
        sender = mapSenders.get(notificationType);
        if (ObjectUtil.isEmpty(sender)) {
            switch (typeUpper) {
            case "SMS":
                this.mapSenders.put(notificationType, new SmsSender());
                break;
            case "PUSH":
                this.mapSenders.put(notificationType, new PushSender());
                break;
            case "WHATSAPP":
                this.mapSenders.put(notificationType, new WhatsAppSender());
                break;
            case "EMAIL":
                this.mapSenders.put(notificationType, new EmailSender());
                break;
            default:
                throw new IllegalArgumentException("Unknown notification type: " + notificationType);
            }
        }
        return mapSenders.get(notificationType);
    }
}
