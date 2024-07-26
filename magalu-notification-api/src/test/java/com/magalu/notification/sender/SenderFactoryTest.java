package com.magalu.notification.sender;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SenderFactoryTest {
    private SenderFactory senderFactory;

    @BeforeEach
    public void setup() {
        this.senderFactory = new SenderFactory();
    }

    @Test
    void createSenderReturnsSmsSenderForSmsType() {
        assertTrue(senderFactory.createSender("SMS") instanceof SmsSender);
    }

    @Test
    void createSenderReturnsPushSenderForPushType() {
        assertTrue(senderFactory.createSender("PUSH") instanceof PushSender);
    }

    @Test
    void createSenderReturnsWhatsAppSenderForWhatsAppType() {
        assertTrue(senderFactory.createSender("WHATSAPP") instanceof WhatsAppSender);
    }

    @Test
    void createSenderThrowsExceptionForUnknownType() {
        assertThrows(IllegalArgumentException.class, () -> senderFactory.createSender("UNKNOWN"));
    }

    @Test
    void createSenderReturnsSameInstanceForSameType() {
        BaseSender sender1 = senderFactory.createSender("SMS");
        BaseSender sender2 = senderFactory.createSender("SMS");
        assertSame(sender1, sender2);
    }
}
