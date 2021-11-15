package com.message_collator;

/**
 * Message class.
 *
 * @author elisamek
 * @version 11/14/2021
 */
public class Message {

    /**
     * private int orderNumber
     */
    final private int orderNumber;

    /**
     * private String message
     */
    final private String message;

    /**
     * com.message_collator.Message constructor.
     * @param orderNumber int
     */
    public Message(int orderNumber) {
        this.orderNumber = orderNumber;
        this.message = "com.message_collator.Message number #" + orderNumber;
    }

    /**
     * getOrderNumber method
     * @return int
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * getMessage method
     * @return String
     */
    public String getMessage() {
        return message;
    }
}
