package com.message_collator;

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
        ArrayList<Integer> randomInts = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            randomInts.add(i);
        }
        Collections.shuffle(randomInts);
        ArrayList<Message> messageList = new ArrayList<>();
        for (int i = 0; i < randomInts.size(); i++) {
            Message generated = new Message(randomInts.get(i));
            messageList.add(generated);
        }
        return messageList;
    }

}
