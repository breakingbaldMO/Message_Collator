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
    private int orderNumber;

    /**
     * private String message
     */
    private String message;

    /**
     * Message constructor.
     * @param orderNumber int
     */
    public Message(int orderNumber) {
        this.orderNumber = orderNumber;
        this.message = "Message number #" + orderNumber;
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
