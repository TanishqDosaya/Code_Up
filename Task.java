/***
 * In this java program we can find out the nth Fibonacci number and also convert the binary number to decimal.
 * owner : Tanishq Dosaya
 * Date : 11/09/2024
*/


import java.util.Scanner;

public class Task {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isQuit = true;
        while (isQuit) {
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 4:
                    System.out.println("Enter the binary number: ");
                    int binaryNumber = scanner.nextInt();

                    int position = 0;
                    int decimalResult = 0;
                    convertBinaryToDecimal(binaryNumber, position, decimalResult);
                    break;

                case 5:
                    System.out.print("Enter index value: ");

                    String input = scanner.nextLine();

                    try {
                        long n = Long.parseLong(input);
                        if (n < 0) {
                            System.out.println("Invalid input. Please enter a non-negative integer.");
                        } else {
                            System.out.println("Fibonacci number at index " + n + " is: " + fibonacci(n));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                    }

            }
        }
        scanner.close();
    }

    static void convertBinaryToDecimal(int binaryNumber, int position, int decimalResult) {
        if (binaryNumber == 0) {
            System.out.println(decimalResult);
        } else {
            int currentDigit = binaryNumber % 10;

            if (currentDigit == 1) {
                decimalResult = decimalResult + (int) Math.pow(2, position);
            }

            binaryNumber = binaryNumber / 10;
            position++;
            convertBinaryToDecimal(binaryNumber, position, decimalResult);
        }
    }

    public static int fibonacci(long index) {
        if (index == 0) {
            return 0;
        } else if (index == 1) {
            return 1;
        } else {
            return fibonacci(index - 1) + fibonacci(index - 2);
        }
    }

}
