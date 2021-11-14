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
    private ArrayList<Message> processedMessages = new ArrayList<>();

    /**
     * receiveBatch method.
     * Receives batch of Messages and verifies messages are in order beginning with expected message number
     * before adding messages to processedMessages.
     * Returns true if successful.
     * Returns false if Message batch is not in order or does not match next expected value.
     * @param batch ArrayList
     * @return boolean
     */
    public boolean receiveBatch(ArrayList<Message> batch) {
        if (batch.get(0).getOrderNumber() != nextExpected) {
            return false;
        }
        else {
            int i = 0;
            while (i < batch.size()) {
                if (nextExpected == batch.get(i).getOrderNumber()) {
                    processedMessages.add(batch.get(i));
                    i++;
                    nextExpected++;
                }
            }
            return true;
        }
    }

    /**
     * getProcessedMessages method.
     * Displays processed Messages.
     * @return String
     */
    public String getProcessedMessages() {
        String result = "";
        for (int i = 0; i < processedMessages.size(); i++) {
            result += " " + processedMessages.get(i).getOrderNumber();
        }
        return result;
    }

    public int getProccessedMessageCount() {
        return processedMessages.size();
    }
}
