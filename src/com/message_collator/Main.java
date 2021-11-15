package com.message_collator;

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
     * com.message_collator.Main method.
     * @param args String[]
     */
    public static void main(final String[] args) {
        Listener listener = new Listener();
        MessageGenerator messageGenerator = new MessageGenerator();
        Processor processor = new Processor();

        sendMessages(listener, messageGenerator,
                processor, getNumberOfMessages());

        System.out.println("\nVerified ordered processing of "
                + processor.getProccessedMessageCount()
                + " messages (numbered 0 to "
                + (processor.getProccessedMessageCount() - 1)
                + "): " + processor.getProcessedMessages());
    }

    /**
     * sendMessages method.
     * Uses a MessageGenerator to send specified number
     * of randomly ordered Messages to a Listener.
     *
     * @param listener         com.message_collator.Listener
     * @param messageGenerator com.message_collator.MessageGenerator
     * @param processor        com.message_collator.Processor
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
            i++;
        }
    }

    /**
     * getNumberOfMessages method.
     * Prompts user to enter number of messages to be sent/received.
     * @return int
     */
    public static int getNumberOfMessages() {
        System.out.println("\n------------ Message Collator ------------");
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

}
