/***
 * Task 5: Longest Substring Without Repeating Characters.
 * Author Name : Tanishq Dosaya
 * Date : 02/09/2024
 */

import java.util.Scanner;

public class LongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            int currentLength = 0;
            boolean isUnique = true;

            for (int j = i; j < s.length(); j++) {

                for (int k = i; k < j; k++) {
                    if (s.charAt(k) == s.charAt(j)) {
                        isUnique = false;
                        break;
                    }
                }

                if (!isUnique) {
                    break;
                }

                currentLength++;
            }

            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }

 
    public static boolean isValidInput(String input) {
        return input.matches("[a-zA-Z0-9]*");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a string to find the length of the longest substring without repeating characters (or type 'exit' to quit):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            }

            if (!isValidInput(input)) {
                System.out.println("Invalid input! Please enter a string with only letters and digits.");
                System.out.println("Example of valid input: 'abc123', 'abcdef', or '12345'.");
            } else {
 
                int result = lengthOfLongestSubstring(input);
                System.out.println("Length of the longest substring without repeating characters: " + result);
            }
        }

        scanner.close();
    }
}
