package com.magalu.notification.sender;

import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PushSenderTest {
    @Test
    void sendPrintsNotImplementedMessage() {
        PrintStream mockPrintStream = Mockito.mock(PrintStream.class);
        System.setOut(mockPrintStream);
        new PushSender().send("receiver", "message");
        Mockito.verify(mockPrintStream).println("Push notification not yet implemented");
    }
}
