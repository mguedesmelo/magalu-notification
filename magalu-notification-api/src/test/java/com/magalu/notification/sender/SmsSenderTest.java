package com.magalu.notification.sender;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SmsSenderTest {
    @Test
    void sendPrintsNotImplementedMessage() {
        PrintStream mockPrintStream = Mockito.mock(PrintStream.class);
        System.setOut(mockPrintStream);
        new SmsSender().send("receiver", "message");
        Mockito.verify(mockPrintStream).println("SMS notification not yet implemented");
    }
}
