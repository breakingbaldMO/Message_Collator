package main.java;

import java.util.ArrayList;
import java.util.Collections;

/**
 * MessageGenerator class
 */
public class MessageGenerator {

    /**
     * Generates shuffled ArrayList of messages
     * @param size int, specifies size of list to generate
     * @return ArrayList of Messages in shuffled order
     */
    public ArrayList<Message> generateMessages(int size) {
        ArrayList<Message> messageList = new ArrayList<>();
        ArrayList<Integer> randomInts = new ArrayList<>();
        if (size < 1) {
            return messageList;
        }

        for (int i = 0; i <= size; i++) {
            randomInts.add(i);
        }
        Collections.shuffle(randomInts);

        for (int i = 0; i < randomInts.size(); i++) {
            Message generated = new Message(randomInts.get(i));
            messageList.add(generated);
        }
        return messageList;
    }

}
