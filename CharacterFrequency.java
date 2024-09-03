/***
 * Task 2: Character Frequency in a String.
 * Author Name : Tanishq Dosaya
 * Date : 02/09/2024
 */

import java.util.Scanner;

public class CharacterFrequency {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.print("Enter the string: ");
        String input = scanner.nextLine();
        
        
        if (input.isEmpty()) {
            System.out.println("Output: ");
            return;
        }

        
        StringBuilder result = new StringBuilder();
        
        int n = input.length();
        int count = 1;
        
        
        for (int i = 1; i < n; i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {            
                count++;
            } else {
                result.append(input.charAt(i - 1)).append(count);
                count = 1;
            }
        }

        result.append(input.charAt(n - 1)).append(count);

        System.out.println("Output: " + result.toString());
        
        scanner.close();
    }
}

