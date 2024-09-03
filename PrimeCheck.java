/***
 * Task 3: Prime Number Checker.
 * Author Name : Tanishq Dosaya
 * Date : 02/09/2024
 */

import java.util.Scanner;

public class PrimeCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = -1;  

        while (true) {
            System.out.println("Enter the value of n (positive integer greater than 1):");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();

                if (n > 1) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter a number greater than 1.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid positive integer.");
                scanner.next();
            }
        }

        boolean isPrime = true;

        for (int i = 2; i <= Math.sqrt(n); i++) { 
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        }

        if (!isPrime) {
            System.out.println("The given number is NOT prime");
        } else {
            System.out.println("The given number is PRIME");
        }

        scanner.close();
    }
}
