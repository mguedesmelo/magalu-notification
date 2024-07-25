package com.magalu.notification.sender;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WhatsAppSenderTest {
    @Test
    void sendLogsDebugMessage() {
        PrintStream mockPrintStream = Mockito.mock(PrintStream.class);
        System.setOut(mockPrintStream);
        new WhatsAppSender().send("receiver", "message");
        Mockito.verify(mockPrintStream).println("WhatsApp notification not yet implemented");
    }
}
