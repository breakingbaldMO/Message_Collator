package main.java;

import java.util.ArrayList;

/**
 * Listener class: Message processing component receives numbered messages in random order
 * and sends them to Processor in batches.
 *
 * @author elisamek
 * @version 11/14/2021
 */
public class Listener {

    /**
     * private messageBuffer ArrayList
     * Stores incoming messages pending batch processing.
     */
  final private ArrayList<Message> messageBuffer;

    /**
     * private int next
     * Points to index of next message to batch.
     */
    private int next = 0;

    /**
     * Constructor method.
     */
    public Listener() {
        this.messageBuffer = new ArrayList<>();
    }

    /**
     * process method.
     * @param batch ArrayList
     */
    private void process(Processor processor, ArrayList<Message> batch) {
        if (processor.receiveBatch(batch)) {
            System.out.println("\tProcessing batch --> " + getBatch(batch));
        }
        else {
            System.out.println("Error processing batch.");
        }
    }

    /**
     * recieveMessage method.
     * Takes a Message as a parameter and inserts into the messageBuffer in chronological order.
     * In-order insertion takes O(n) time.
     * @param message Message
     */
    public void receiveMessage(Message message) throws IndexOutOfBoundsException {
        if (messageBuffer.size() == 0) {
            messageBuffer.add(message);
        }
        else {
            int i = 0;
            while (i < messageBuffer.size() && message.getOrderNumber() > messageBuffer.get(i).getOrderNumber()) {
                i++;
            }
            messageBuffer.add(i, message);
        }
    }

    /**
     * getBatch method.
     * Evaluates batch returned from checkStatus() and calls process()
     * @param processor
     */
    public void getBatch(Processor processor) {
        ArrayList<Message> batch = checkStatus();
        if (batch.size() > 0) {
            process(processor, batch);
        }
    }

    /**
     * checkStatus method.
     * Checks the availability to process the next message number(s).
     * @return ArrayList
     */
    public ArrayList<Message> checkStatus() {
        ArrayList<Message> batch = new ArrayList<>();
        if (messageBuffer.get(next).getOrderNumber() == next) {
            batch.add(messageBuffer.get(next));
            next++;
            while (next < messageBuffer.size() && next == messageBuffer.get(next).getOrderNumber()) {
                batch.add(messageBuffer.get(next));
                next++;
            }
        }
        return batch;
    }

    /**
     * getMessageBuffer method.
     * Displays Messages in messageBuffer.
     * @return String
     */
    public String getMessageBuffer() {
        String result = "";
        for (int i = 0; i < messageBuffer.size(); i++) {
            result += " " + messageBuffer.get(i).getOrderNumber();
        }
        return result;
    }

    /**
     * getBatch method.
     * Method to view contents of a batch.
     * @param batch ArrayList
     * @return String
     */
    public String getBatch(ArrayList<Message> batch) {
        String result = "";
        for (int i = 0; i < batch.size(); i++) {
            result += " " + batch.get(i).getOrderNumber();
        }
        return result;
    }

}
