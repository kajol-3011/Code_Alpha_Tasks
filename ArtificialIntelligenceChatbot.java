import java.util.Scanner;

public class AIChatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm your AI chatbot. Type 'bye' to exit.");
        
        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase();
            
            if (userInput.equals("bye")) {
                System.out.println("Chatbot: Goodbye! Have a great day!");
                break;
            }
            
            System.out.println("Chatbot: " + getResponse(userInput));
        }
        
        scanner.close();
    }
    
    private static String getResponse(String input) {
        if (input.contains("hi") || input.contains("hello") || input.contains("hey")) {
            return "Hello! How can I assist you today?";
        } else if (input.contains("your name")) {
            return "I am an AI chatbot created to assist you!";
        } else if (input.contains("how are you")) {
            return "I'm just a bot, but I'm functioning well! How about you?";
        } else if (input.contains("help")) {
            return "Sure! What do you need help with?";
        } else {
            return "I'm not sure I understand. Could you rephrase that?";
        }
    }
}
