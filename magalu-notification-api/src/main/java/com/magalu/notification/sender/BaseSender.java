package com.magalu.notification.sender;

public abstract class BaseSender {
    public abstract boolean send(String receiver, String message);
}
