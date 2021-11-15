package test.java;

import main.java.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;


class ProcessorTest {

    @Test
    @DisplayName("In Order Batch")
    void receiveBatchInOrder() {
        Listener listener = new Listener();
        Processor processor = new Processor();
        Message message0 = new Message(0);
        Message message1 = new Message(1);
        ArrayList<Message> batch = new ArrayList<Message>();
        batch.add(message0);
        batch.add(message1);
        Assertions.assertTrue(processor.receiveBatch(batch));
    }

    @Test
    @DisplayName("Out of Order Batch - Start with non-zero")
    void receiveBatchOutOfOrder() {
        Processor processor = new Processor();
        Message message0 = new Message(0);
        Message message1 = new Message(1);
        ArrayList<Message> batch = new ArrayList<Message>();
        batch.add(message1);
        batch.add(message0);
        Assertions.assertFalse(processor.receiveBatch(batch));
    }

    @Test
    @DisplayName("Out of Order Batch - Consecutive Messages")
    void receiveBatchOutOfOrderMultiple() {
        Processor processor = new Processor();
        Message message0 = new Message(0);
        Message message1 = new Message(1);
        Message message2 = new Message(2);
        Message message4 = new Message(4);
        ArrayList<Message> batch = new ArrayList<Message>();
        batch.add(message0);
        batch.add(message1);
        batch.add(message2);
        batch.add(message4);
        Assertions.assertFalse(processor.receiveBatch(batch));
    }

    @Test
    @DisplayName("Verify Final Processed Result")
    void verifyFinalProcessedMessages() {
        Listener listener = new Listener();
        MessageGenerator messageGenerator = new MessageGenerator();
        Processor processor = new Processor();
        ArrayList<Message> messages =
                messageGenerator.generateMessages(100);

        int i = 0;
        while (i < messages.size()) {
            System.out.println("Received - "
                    + messages.get(i).getOrderNumber());
            listener.receiveMessage(messages.get(i), processor);
            listener.getBatch(processor);
            i++;
        }
        Assertions.assertEquals(listener.getMessageBuffer(), processor.getProcessedMessages());
    }

}