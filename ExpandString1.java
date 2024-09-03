import java.util.Scanner;

public class ExpandString1 {

    public static String expandString(String input) {
        String expandedString = "";
        Constant constant = new Constant();
        for (int i = 0; i < input.length(); i += 2) {
            char character = input.charAt(i);
            char digitChar = input.charAt(i + 1); 
            if (!Character.isLetter(character) || !Character.isDigit(digitChar)) {
                return constant.s;
            }

            int count = digitChar - '0'; 

            for (int j = 0; j < count; j++) {
                expandedString += character;
            }
        }
        
        return expandedString; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        String expanded;
        
        while (true) {
            System.out.print("Enter the string (characters followed by digits, e.g., a2b3) or type 'exit' to quit: ");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }

            if (input.length() % 2 != 0) {
                System.out.println("Invalid input format! Please enter letters followed by digits (e.g., a2b3).");
            } else {
                expanded = expandString(input);

                if (expanded.startsWith("Invalid")) {
                    System.out.println(expanded);
                } else {
                    System.out.println("Expanded string: " + expanded);
                }
            }
        }
        
        scanner.close();
    }
}
