package main.java;

import java.util.ArrayList;

/**
 * Processor class.
 *
 * @author elisamek
 * @version 11/14/2021
 */
public class Processor {

    /**
     * private int nextExpected
     */
    private int nextExpected = 0;

    /**
     * private ArrayList processedMessages
     */
    final private ArrayList<Message> processedMessages = new ArrayList<>();

    /**
     * receiveBatch method.
     * Receives batch of Messages and verifies messages are in order beginning with expected message number
     * before adding messages to processedMessages.
     * Returns true if correct order is verified.
     * Returns false if Message batch is not in order or does not match next expected value.
     * @param batch ArrayList
     * @return boolean
     */
    public boolean receiveBatch(ArrayList<Message> batch) {
        if (!verifyBatch(batch)) {
            return false;
        }
        int i = 0;
        while (i < batch.size()) {
            processedMessages.add(batch.get(i));
            i++;
            nextExpected++;
        }
        return true;
    }

    /**
     * verifyBatch method.
     * Checks for ordered delivery of batch
     * prior to processing.
     * @param batch ArrayList
     * @return boolean
     */
        public boolean verifyBatch(ArrayList<Message> batch) {
            if (batch.get(0).getOrderNumber() != nextExpected) {
                return false;
            }
            int i = 0;
            int nextPointer = nextExpected;
            while (i < batch.size()) {
                if (nextPointer == batch.get(i).getOrderNumber()) {
                    i++;
                    nextPointer++;
                } else {
                    return false;
                }
            }
            return true;
        }

    /**
     * getProcessedMessages method.
     * Displays processed Messages.
     * @return String
     */
    public String getProcessedMessages() {
        String result = "";
        for (Message processedMessage : processedMessages) {
            result += " " + processedMessage.getOrderNumber();
        }
        return result;
    }

    /**
     * getProcessedMessageCount method.
     * @return int
     */
    public int getProcessedMessageCount() {
        return processedMessages.size();
    }
}
