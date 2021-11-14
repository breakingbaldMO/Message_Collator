import java.util.*;

/**
 *
 *  @author elisamek
 *  @version 11/14/2021
 */
public class Main {

    public static void main(String[] args) {

     Listener listener = new Listener();
     MessageGenerator messageGenerator = new MessageGenerator();
     Processor processor = new Processor();

     System.out.println("\n------------ Message Collator ------------");
     Scanner scan = new Scanner(System.in);
     int numberOfMessages = -1;
     do {
         System.out.print("\nEnter the number of messages to send: ");
         while (!scan.hasNextInt()) {
             scan.next();
         }
         numberOfMessages = scan.nextInt();
         System.out.println("");
     }
     while (numberOfMessages < 1);
     sendMessages(listener, messageGenerator, processor, numberOfMessages - 1);

     System.out.println("\nVerified ordered processing of " + processor.getProccessedMessageCount() + " messages: " + processor.getProcessedMessages());

    }

    /**
     * sendMessages method.
     * Uses a MessageGenerator to send specified number of randomly ordered Messages to a Listener.
     * @param listener Listener
     * @param messageGenerator MessageGenerator
     * @param processor Processor
     * @param messageCount int
     */
    private static void sendMessages(Listener listener, MessageGenerator messageGenerator, Processor processor, int messageCount) {
        ArrayList<Message> messages =  messageGenerator.generateMessages(messageCount);
            int i = 0;
            while (i < messages.size()) {
                System.out.println("Received: " + messages.get(i).getOrderNumber());
                listener.receiveMessage(messages.get(i), processor);
                i++;
            }
        }

}
