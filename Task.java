
/***
 * Assignment : A java program that can perform CountPalindromes, NthFibonacci Number, SnakeToCamel Case Conversion, CountConsonants, BinaryToDecimal Conversion.
 * Name : Tanishq Dosaya
 * Date : 11/09/2024 - 13/09/2024
*/

import java.math.BigInteger;
import java.util.*;

public class Task {
    public static void main(String[] args) {
        Constants constant = new Constants();
        Scanner scanner = new Scanner(System.in);
        boolean isQuit = true;
        while (isQuit) {
            int choice;
            while (true) {
                try {
                    System.out.println(constant.Choice);
                    // scanner.nextLine();
                    choice = scanner.nextInt();
                    if (choice < 0) {
                        System.out.println(constant.NonNegative);
                    } 
                    else if (choice < 7 && choice > 0) {
                        break;
                    }
                    else{
                        System.out.println(constant.Invalid_Choice);
                    }
                }
                 catch (InputMismatchException a) {
                    System.out.println(constant.Invalid_number);
                    scanner.next();
                }
            }

            switch (choice) {
                case 1:
                    System.out.println("Task 1 is executing....");
                    System.out.print("Enter a string: ");
                    scanner.nextLine();
                    String userInput = scanner.nextLine();
                    int outcome = countUniquePalindromes(userInput);
                    System.out.println("Unique Palindromes: " + outcome);
                    break;

                case 2:
                    System.out.println("Task 2 is executing....");
                    System.out.print("Enter index value: ");
                    BigInteger input = null;

                    try {
                        input = scanner.nextBigInteger();
                        if (input.compareTo(BigInteger.ZERO) < 0) {
                            System.out.println(constant.Invalid_Negative);
                        } 
                        else if (input.compareTo(BigInteger.ZERO) >= 0
                                && input.compareTo(BigInteger.valueOf(10000)) <= 0) {
                            BigInteger result = fibonacci(input, BigInteger.ZERO, BigInteger.ONE);
                            System.out.println(result);
                        }
                        else {
                            System.out.println("warning!!! it exceeds the limit");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(constant.valid_Integer);
                    }
                    break;
                case 3:
                    System.out.println("Task 3 is executing....");  
                    System.out.println("Enter a string: ");
                    scanner.nextLine();
                    String originalString = scanner.nextLine();
                    String camelCaseResult = convertToCamelCase(originalString);
                    System.out.println("Camel case: " + camelCaseResult);
                    break;
                case 4:
                    System.out.println("Task 4 is executing....");
                    System.out.println("Enter the String: ");
                    scanner.nextLine();
                    String testString = scanner.nextLine();
                    int i = 0;
                    int count = 0;
                    checkConsonants(testString, i, count);
                    break;
                case 5:
                    System.out.println("Task 5 is executing....");
                    System.out.println("Enter the binary number: ");
                    scanner.nextLine();
                    String binaryNumber = scanner.nextLine();
                    try {
                        long n = Long.parseLong(binaryNumber);
                        if (n < 0) {
                            System.out.println(constant.Invalid_Negative);
                        } 
                        else {
                            int position = 0;
                            long decimalResult = 0;
                            convertBinaryToDecimal(n, position, decimalResult);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(constant.valid_Integer);
                    }
                    break;

                case 6:
                    System.out.println("Exiting the program.......");
                    isQuit = false;
            }
        }
        scanner.close();
    }

    // Task 4: CountConsonants
    public static void checkConsonants(String input, int i, int count) {
        if (i == input.length()) {
            System.out.println("The count of Consonant is: " + count);
        } 
        else {
            char currentChar = input.charAt(i);
            currentChar = Character.toLowerCase(currentChar);

            boolean decision = (currentChar >= 'a' && currentChar <= 'z') && (currentChar != 'a' && currentChar != 'e'
                    && currentChar != 'i' && currentChar != 'o' && currentChar != 'u');
            if (decision) {
                count++;
            }
            i++;
            checkConsonants(input, i, count);
        }
    }

    // Task 5: BinaryToDecimal
    static void convertBinaryToDecimal(long binaryNumber, int position, long decimalResult) {
        if (binaryNumber == 0) {
            System.out.println("The decimal number : " + decimalResult);
        } 
        else {
            long currentDigit = binaryNumber % 10;

            if (currentDigit == 1) {
                decimalResult = decimalResult + (int) Math.pow(2, position);
            }

            binaryNumber = binaryNumber / 10;
            position++;
            convertBinaryToDecimal(binaryNumber, position, decimalResult);
        }
    }

    // Task 2: NthFibonacci
    public static BigInteger fibonacci(BigInteger index, BigInteger a, BigInteger b) {
        if (index.equals(BigInteger.ZERO)) {
            return a;
        }
         else if (index.equals(BigInteger.ONE)) {
            return b;
        } 
        else {
            return fibonacci(index.subtract(BigInteger.ONE), b, a.add(b));
        }
    }

    // Task 3: SnakeToCamel
    public static String toSnakeCase(String input) {
        return input.trim().replaceAll("[^a-zA-Z0-9]+", "_").toLowerCase();
    }

    public static String toCamelCaseRecursive(String[] words, int index, String result) {
        if (index >= words.length) {
            return result;
        }
        String word = words[index];
        if (word.isEmpty()) {
            return toCamelCaseRecursive(words, index + 1, result);
        }

        if (result.isEmpty()) {
            result += word.toLowerCase();
        } 
        else {
            result += Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
        }
        return toCamelCaseRecursive(words, index + 1, result);
    }

    public static String convertToCamelCase(String input) {
        String snakeCase = toSnakeCase(input);
        String[] words = snakeCase.split("_");
        if (words.length == 0 || (words.length == 1 && words[0].isEmpty())) {
            return "";
        }
        return toCamelCaseRecursive(words, 0, "");
    }
 
    // Task 1: CountPalindromes 
    private static List<String> palindromeList = new ArrayList<>();
    public static void findPalindromes(String s, int start, int end) {
        if (start >= s.length()) {
            return;
        }
        if (end < s.length()) {
            String subStr = s.substring(start, end + 1);
            if (isPalindrome(subStr) && !isInList(subStr, 0)) {
                palindromeList.add(subStr);
            }
            findPalindromes(s, start, end + 1);
        }
        findPalindromes(s, start + 1, start + 1);
    }
    private static boolean isPalindrome(String s) {
        return isPalindromeHelper(s, 0, s.length() - 1);
    }
    private static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) {
            return true;
        }
        if (s.charAt(left) != s.charAt(right)) {
            return false;
        }
        return isPalindromeHelper(s, left + 1, right - 1);
    }
    private static boolean isInList(String s, int index) {
        if (index >= palindromeList.size()) {
            return false;
        }
        if (palindromeList.get(index).equals(s)) {
            return true;
        }
        return isInList(s, index + 1);
    }
    public static int countUniquePalindromes(String s) {
        palindromeList.clear();
        findPalindromes(s, 0, 0);
        return palindromeList.size();
    }

}
