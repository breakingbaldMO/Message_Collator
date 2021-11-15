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

        verifyMessageProcessing(processor);
    }

    /**
     * sendMessages method.
     * Uses a MessageGenerator to send specified number
     * of randomly ordered Messages to a main.java.Listener.
     *
     * @param listener         Listener
     * @param messageGenerator MessageGenerator
     * @param processor        Processor
     * @param messageCount     int
     */
    private static void sendMessages(final Listener listener,
                                     final MessageGenerator messageGenerator,
                                     final Processor processor,
                                     final int messageCount) {

        ArrayList<Message> messages =
                messageGenerator.generateMessages(messageCount);

        int i = 0;

        System.out.println("Sending messages ...\n");

        while (i < messages.size()) {
            System.out.println("Received - "
                    + messages.get(i).getOrderNumber());
            listener.receiveMessage(messages.get(i), processor);
            listener.getBatch(processor);
            i++;
        }
    }

    /**
     * getNumberOfMessages method.
     * Prompts user to enter number of messages to be sent/received.
     * @return int
     */
    public static int getNumberOfMessages() {
        System.out.println("\n------------ main.java.Message Collator ------------");
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
        while (numberOfMessages < 1);

        return numberOfMessages - 1;
    }

    public static void verifyMessageProcessing(Processor processor) {
        System.out.println("\nVerified ordered processing of "
                + processor.getProcessedMessageCount()
                + " messages (numbered 0 to "
                + (processor.getProcessedMessageCount() - 1)
                + "): " + processor.getProcessedMessages() + "\n\n");
    }

}
