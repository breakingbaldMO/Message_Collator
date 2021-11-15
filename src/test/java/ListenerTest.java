package test.java;

import main.java.Listener;
import main.java.Message;
import main.java.MessageGenerator;
import main.java.Processor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ListenerTest {

    @Test
    @DisplayName("Validate Receive Message")
    public void shouldReceiveMessage() {
        Listener listener = new Listener();
        MessageGenerator messageGenerator = new MessageGenerator();
        ArrayList<Message> messages =
                messageGenerator.generateMessages(1);
        listener.receiveMessage(messages.get(0));
        Assertions.assertFalse(listener.getMessageBuffer().isEmpty());
    }

    @Test
    @DisplayName("Validate Message Order")
    public void inOrderMessages() {
        Listener listener = new Listener();
        Message message3 = new Message(3);
        Message message2 = new Message(2);
        Message message1 = new Message(1);
        listener.receiveMessage(message3);
        listener.receiveMessage(message2);
        listener.receiveMessage(message1);
        Assertions.assertEquals(" 1 2 3", listener.getMessageBuffer());

    }

    @Test
    @DisplayName("Validate Processing of 0 when first")
    public void checkProcess() {
        Listener listener = new Listener();
        Processor processor = new Processor();
        Message message0 = new Message(0);
        listener.receiveMessage(message0);
        listener.getBatch(processor);
        Assertions.assertEquals(" 0", processor.getProcessedMessages());
    }

    @Test
    @DisplayName("Generate Negative Messages Input Exception")
    public void throwErrorReceiveMessage() {
        Listener listener = new Listener();
        MessageGenerator messageGenerator = new MessageGenerator();
        ArrayList<Message> messages =
                messageGenerator.generateMessages(-1);
        Assertions.assertThrows(RuntimeException.class, () ->
                listener.receiveMessage(messages.get(0)));
    }

}
