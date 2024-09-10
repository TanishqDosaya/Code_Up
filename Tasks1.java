/***
 * Assignment: A Java program that can perform Valid Parentheses Combination, Digit Sum Loop, Consecutive Number Summer, Caesar Cipher with Shift Variability and Encoding Challenge with ASCII Conversion.
 * Owner: Tanishq Dosaya
 * Date: 09/09/2024
 */


 import java.util.InputMismatchException;
 import java.util.Scanner;
 
 public class Tasks1 {
 
     // Task 1: Valid Parentheses Combination Generator
     public static void generateStrings(char[] input1, char[] result, int index, boolean[] used, int length) {
         if (index == length) {
             for (int i = 0; i < length; i++) {
                 System.out.print(result[i]);
             }
             System.out.println();
             return;
         }
         for (int i = 0; i < input1.length; i++) {
             if (!used[i]) {
                 used[i] = true;
                 result[index] = input1[i];
                 generateStrings(input1, result, index + 1, used, length);
                 used[i] = false;
             }
         }
     }
 
     // TASK 2: Digit Sum Loop
     public static void DigitSumLoop(int n) {
         int num = 0;
         int rem = 0;
         while (n != 0) {
             rem = n % 10;
             num = num + rem;
             n = n / 10;
         }
         if (num > 9) {
             DigitSumLoop(num);
         } else {
             System.out.println("The single digit: " + num);
         }
     }
 
     // TASK 3: Consecutive Number Summer
     private static void printSequence(int start, int end) {
         System.out.print(start);
         for (int i = start + 1; i <= end; i++) {
             System.out.print(" + " + i);
         }
         System.out.println();
     }
 
     // Task 4: Caesar Cipher with Shift Variability
     public static void caesarCipher(String input, int[] shiftPattern) {
         int patternLength = shiftPattern.length;
         String result = "";
         for (int i = 0, patternIndex = 0; i < input.length(); i++) {
             char currentChar = input.charAt(i);
             if (currentChar != ' ') {
                 int shift = shiftPattern[patternIndex % patternLength];
                 char shiftedChar = (char) (currentChar + shift);
                 result += shiftedChar;
                 patternIndex++;
             } else {
                 result += ' ';
             }
         }
         System.out.println("Encrypted Text: " + result);
     }
 
 
     // Task 5: Encoding Challenge with ASCII Conversion
     public static void encodeToAscii(int[] digits, int[] series) {
         java.util.Arrays.sort(digits);
         
         for (int i = 0; i < digits.length / 2; i++) {
             int temp = digits[i];
             digits[i] = digits[digits.length - 1 - i];
             digits[digits.length - 1 - i] = temp;
         }
         
         StringBuilder result = new StringBuilder();
         
         for (int i = 0; i < series.length; i++) {
             int index = series[i] - 1;
             if (index >= 0 && index < digits.length) {
                 int digit = digits[index];
                 int asciiValue = digit + 48;
                 result.append(asciiValue);
             } else {
                 System.out.println("Invalid series input. Please provide valid indexes.");
                 return;
             }
         
         System.out.println("Encoded ASCII output: " + result.toString());
     }
     }
 
 
 
     public static void main(String[] args) {
 
         Scanner in = new Scanner(System.in);
         String n = "";
         Constant constant = new Constant();
 
         do {
             System.out.println(
                     "+ 1. Valid Parentheses Combination Generator\n+ 2. Digit Sum Loop\n+ 3. Consecutive Number Summer\n+ 4. Caesar Cipher with Shift Variability\n+ 5. Encoding Challenge with ASCII Conversion\n+ 6. Exit");
             int choice ;
             try {
                 System.out.println("Enter your Choice:");
                 choice = in.nextInt();
                 in.nextLine(); // Consume newline
             } catch (Exception e) {
                 System.out.println("Invalid input. Please enter a number.");
                 in.close();
                 return;
             }
 
             switch (choice) {
 
                 case 1:
                     System.out.println(constant.EXECUTE + "1-----------");
                     System.out.println("Enter a String:");
                     String inputString = in.nextLine();
                     char[] input1 = new char[inputString.length()];
                     boolean[] used = new boolean[input1.length];
                     for (int i = 0; i < inputString.length(); i++) {
                         input1[i] = inputString.charAt(i);
                     }
                     char[] result = new char[input1.length];
                     for (int length = 1; length <= input1.length; length++) {
                         generateStrings(input1, result, 0, used, length);
                     }
                     break;
                     
                 case 2:
                     do {
                         try {
                             System.out.println("Enter any positive interger number");
                             int input = in.nextInt();
                             in.nextLine();
                             if (input < 0) {
                                 System.out.println(constant.ENTER_POSITIVE);
 
                             } else {
                                 DigitSumLoop(input);
                             }
                         } catch (InputMismatchException e) {
                             System.out.println(constant.INVALID);
                         }
                         System.out.println("do you want to perform the same operation?");
                         in.nextLine();
                         n = in.next();
 
                     } while (n.equalsIgnoreCase("Yes"));
                     // System.out.println("hello");
                     break;
 
                 case 3:
                     try {
                         System.out.println("Enter a positive integer: ");
                         int num = in.nextInt();
                         if (num < 0) {
                             System.out.println(constant.ENTER_POSITIVE);
 
                         } else {
                             for (int i = 1; i < num; i++) {
                                 int sum = 0;
                                 for (int j = i; j < num; j++) {
                                     sum += j;
                                     if (sum == num) {
                                         printSequence(i, j);
                                     } else if (sum > num) {
                                         break;
                                     }
                                 }
                             }
                         }
                     } catch (InputMismatchException e) {
                         System.out.println(constant.INVALID);
                     }
                     break;
 
                 case 4:
                     System.out.println("Enter the String: ");
                     String input = in.nextLine();
                     System.out.println("Enter Caesar cipher shift pattern (space-separated integers): ");
                     String[] shiftInput = in.nextLine().split(" ");
                     int[] shiftPattern = new int[shiftInput.length];
                     boolean invalidShift = false;
                     for (int i = 0; i < shiftInput.length; i++) {
                         try {
                             shiftPattern[i] = Integer.parseInt(shiftInput[i]);
                         } catch (NumberFormatException e) {
                             System.out.println(constant.INVALID_PATTERN);
                             invalidShift = true;
                             break;
                         }
                     }
                     if (!invalidShift) {
                         caesarCipher(input, shiftPattern);
                     }
                     break;
 
                 case 5:
                 System.out.println("Enter digits array (space-separated single digits): ");
                 String[] digitsInput = in.nextLine().split(" ");
                 int[] digits = new int[digitsInput.length];
                 boolean invalidDigits = false;
                 for (int i = 0; i < digitsInput.length; i++) {
                     try {
                         digits[i] = Integer.parseInt(digitsInput[i]);
                     } catch (NumberFormatException e) {
                         System.out.println(constant.DIGIT);
                         invalidDigits = true;
                         break;
                     }
                 }
 
                 if (!invalidDigits) {
                     System.out.println("Enter series array (space-separated integers): ");
                     String[] seriesInput = in.nextLine().split(" ");
                     int[] series = new int[seriesInput.length];
                     boolean invalidSeries = false;
                     for (int i = 0; i < seriesInput.length; i++) {
                         try {
                             series[i] = Integer.parseInt(seriesInput[i]);
                         } catch (NumberFormatException e) {
                             System.out.println(constant.SERIES);
                             invalidSeries = true;
                             break;
                         }
                     }
 
                     if (!invalidSeries) {
                         encodeToAscii(digits, series);
                     }
                 }
                 break;
 
                 case 6:
                     System.out.println("Exiting...");
                     break;
                 default:
                     System.out.println(constant.CHOICE);
                     n = "yes";
                    
             }
             System.out.println("Do you want to perform another operation? Enter 'yes' to continue or 'no' to exit.");
             in.nextLine();
             n = in.next();
 
         } while (n.equalsIgnoreCase("Yes"));
 
         in.close();
     }
 }
 