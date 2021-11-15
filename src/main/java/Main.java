package main.java;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for Message Collator.
 *
 * @author elisamek
 * @version 11/14/2021
 */
public class Main {

    /**
     * Main method.
     * @param args String[]
     */
    public static void main(final String[] args) {
        Listener listener = new Listener();
        MessageGenerator messageGenerator = new MessageGenerator();
        Processor processor = new Processor();

        sendMessages(listener, messageGenerator,
                processor, getNumberOfMessages());

        verifyMessageProcessingMessage(processor);
    }

    /**
     * sendMessages method.
     * Uses a MessageGenerator to send specified number
     * of randomly ordered Messages to a Listener.
     *
     * @param listener         Listener
     * @param messageGenerator MessageGenerator
     * @param processor        Processor
     * @param messageCount     int
     */
    public static void sendMessages(final Listener listener,
                                     final MessageGenerator messageGenerator,
                                     final Processor processor,
                                     final int messageCount) {

        ArrayList<Message> messages =
                messageGenerator.generateMessages(messageCount);

        int i = 0;

        System.out.println("Sending messages ...\n");

        do {
            System.out.println("Received - "
                    + messages.get(i).getOrderNumber());
            listener.receiveMessage(messages.get(i));
            listener.getBatch(processor);
            i++;
        } while (i < messages.size());
    }

    /**
     * getNumberOfMessages method.
     * Prompts user to enter number of messages to be sent/received.
     * @return int
     */
    public static int getNumberOfMessages() {
        System.out.println("\n------------ Message Collator ------------");
        System.out.println("\nA message processing component that "
                            + "receives N number of messages "
                            + "numbered from 0..N, one at a time.");
        System.out.println("\nBatches are processed once the next in "
                            + "order message(s) has been received.");
        System.out.println("\n------------------------------------------");
        Scanner scan = new Scanner(System.in);
        int numberOfMessages;
        do {
            System.out.print("\nEnter the number of messages to send: ");
            while (!scan.hasNextInt()) {
                scan.next();
                System.out.print("\nEnter the number of messages to send: ");
            }
            numberOfMessages = scan.nextInt();
            System.out.println();
        }
        while (numberOfMessages < 2);

        return numberOfMessages - 1;
    }

    /**
     * verifyMessageProcessingMessage.
     * @param processor Processor
     */
    public static void verifyMessageProcessingMessage(final Processor processor) {
        System.out.println("\nVerified ordered processing of "
                + processor.getProcessedMessageCount()
                + " messages (numbered 0 to "
                + (processor.getProcessedMessageCount() - 1)
                + "): " + processor.getProcessedMessages() + "\n\n");
    }

}
