package com.magalu.notification.sender;

import org.springframework.stereotype.Component;

@Component
public class PushSender extends BaseSender {
    @Override
    public boolean send(String receiver, String message) {
        System.out.println("Push notification not yet implemented");
        return Boolean.TRUE;
    }
}
