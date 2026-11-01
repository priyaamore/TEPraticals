
import java.util.Scanner;

public class SimpleChatbot {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Chatbot: Hello! Welcome to AI Chatbot.");
        System.out.println("Chatbot: How can I help you?");
        System.out.println("Chatbot: Type 'bye' to exit.");

        while (true) {

            System.out.print("You: ");
            String input = sc.nextLine().toLowerCase();

            // Predefined rules and pattern matching

            if (input.contains("hello") || input.contains("hi")) {
                System.out.println("Chatbot: Hello! How are you?");
            }

            else if (input.contains("your name")) {
                System.out.println("Chatbot: I am a Simple AI Chatbot.");
            }

            else if (input.contains("how are you")) {
                System.out.println("Chatbot: I am fine. Thank you!");
            }

            else if (input.contains("help")) {
                System.out.println("Chatbot: Sure! How can I assist you?");
            }

            else if (input.contains("college")) {
                System.out.println("Chatbot: College is a place to learn and grow.");
            }

            else if (input.contains("thank")) {
                System.out.println("Chatbot: You're welcome!");
            }

            else if (input.contains("bye")) {
                System.out.println("Chatbot: Goodbye! Have a nice day.");
                break;
            }

            else {
                System.out.println("Chatbot: Sorry, I don't understand.");
            }
        }

        sc.close();
    }
}
