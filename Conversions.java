/***
 * This program is used to implement the base conversions and also perform arithmetic operations(Addition and Subtraction) between the same base.
 * Name : Tanishq Dosaya
 * Date : 24/09/2024
 */


import java.util.*;

public class Conversions {
    // Constants constant = new Constants();

    // Recursively checks if the input string is a valid binary number.
    public static boolean checkBinary(int index, String input) {
        if (index >= input.length()) {
            return true;
        }

        char ch = input.charAt(index);
        if (ch == '1' || ch == '0') {
            return checkBinary(index + 1, input);
        } else if (ch < '0') {
            System.out.println(Constants.INVALID_MESSAGE);
            return false;
        } else {
            System.out.println(Constants.NOT_BINARY_MESSAGE);
            return false;
        }
    }
    
    // Recursively checks if the input string is a valid octal number.
    public static boolean checkOctal(int index, String input) {
        if (index >= input.length()) {
            return true;
        }

        char ch = input.charAt(index);
        if (ch >= '0' && ch < '8') {
            return checkOctal(index + 1, input);
        } else if (ch < '0') {
            System.out.println(Constants.INVALID_MESSAGE);
            return false;
        } else {
            System.out.println(Constants.NOT_OCTAL_MESSAGE);
            return false;
        }
    }
    
    // Recursively checks if the input string is a valid decimal number.
    public static boolean checkDecimal(int index, String input) {
        if (index >= input.length()) {
            return true;
        }
        char ch = input.charAt(index);
        if (ch >= '0' && ch <= '9') {
            return checkDecimal(index + 1, input);
        } else if (ch < '0') {
            System.out.println(Constants.INVALID_MESSAGE);
            return false;
        } else {
            System.out.println(Constants.NOT_DECIMAL_MESSAGE);
            return false;
        }
    }

    // Converts a binary number to its decimal equivalent recursively.
    static long convertBinaryToDecimal(long binaryNumber, int position, long decimalResult) {
        if (binaryNumber == 0) {
            return decimalResult;
        } else {
            long currentDigit = binaryNumber % 10;
            if (currentDigit == 1) {
                decimalResult += power(2, position);
            }
            binaryNumber /= 10;
            position++;
            return convertBinaryToDecimal(binaryNumber, position, decimalResult);
        }
    }

    // Converts an octal number to its decimal equivalent recursively.
    static long convertOctalToDecimal(long octalNumber, int position, long decimalResult) {
        if (octalNumber == 0) {
            return decimalResult;
        } else {
            long currentDigit = octalNumber % 10;
            decimalResult += power(8, position, currentDigit);
            octalNumber /= 10;
            position++;
            return convertOctalToDecimal(octalNumber, position, decimalResult);
        }
    }

    // Calculates a raised to the power b, then multiplies the result by the current digit.
    static long power(long a, long b, long currentDigit) {
        long ans = currentDigit;
        if (b == 0) {
            return ans;
        } else {
            for (int i = 0; i < b; i++) {
                ans = ans * a;
            }
            return ans;
        }
    }
 
    // Computes a raised to the power b iteratively.
    static long power(long a, long b) {
        long ans = 1;
        if (b == 0) {
            return ans;
        } else {
            for (int i = 0; i < b; i++) {
                ans = ans * a;
            }
            return ans;
        }
    }

    // Converts a hexadecimal string into its decimal equivalent.
    public static long convertHexToDecimal(String hex) {
        long decimal = 0;
        int length = hex.length();
        for (int i = 0; i < length; i++) {
            char hexChar = hex.charAt(i);
            long hexValue = hexCharToDecimal(hexChar);
            if(hexValue < 0){
                System.err.println(Constants.INVALID_CHARACTER_MESSAGE);
                break;
            }
            decimal = decimal * 16 + hexValue;
        }
        return decimal;
    }

    // Converts a single hexadecimal character to its decimal value.
    public static long hexCharToDecimal(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - '0';
        } else if (ch >= 'A' && ch <= 'F') {
            return 10 + (ch - 'A');
        } else if (ch >= 'a' && ch <= 'f') {
            return 10 + (ch - 'a');
        } else {
            return -1;
        }
    }

    // Converts a decimal number to its binary equivalent.
    static long decimalToBinary(long decimalno) {
        long binaryNumber = 0, index = 0;
        while (decimalno != 0) {
            binaryNumber += (decimalno % 2) * power(10, index++);
            decimalno /= 2;
        }
        return binaryNumber;
    }

    // Converts a decimal number to its octal equivalent.
    static long decimalToOctal(long decimalno) {
        long octalNumber = 0, index = 0;
        while (decimalno != 0) {
            octalNumber += (decimalno % 8) * power(10, index++);
            decimalno /= 8;
        }
        return octalNumber;
    }

    // Converts a decimal number to its hexadecimal equivalent.
    static String decimalToHexadecimal(long decimalno) {
        if (decimalno == 0) {
            return "0";
        }
        String hexString = "";
        while (decimalno != 0) {
            long remainder = decimalno % 16;
            if (remainder < 10) {
                hexString = remainder + hexString;
            } else {
                hexString = (char) ('A' + remainder - 10) + hexString;
            }
            decimalno /= 16;
        }
        return hexString;
    }

    // Returns the maximum length between two strings.
    public static int findMaxLength(String str1, String str2) {
        int length1 = str1.length();
        int length2 = str2.length();
        return (length1 > length2) ? length1 : length2;
    }

    // Pads the input string with leading zeros until it reaches the specified length.
    public static String padWithZeros(String input, int length) {
        while (input.length() < length) {
            input = "0" + input;
        }
        return input;
    }

    // Adds two binary numbers and returns the sum as a binary string.
    public static String addBinary(String binary1, String binary2) {
        int maxLength = findMaxLength(binary1, binary2);
        binary1 = padWithZeros(binary1, maxLength);
        binary2 = padWithZeros(binary2, maxLength);
        String result = "";
        int carry = 0;
        for (int i = maxLength - 1; i >= 0; i--) {
            int digit1 = binary1.charAt(i) - '0';
            int digit2 = binary2.charAt(i) - '0';
            int sum = digit1 + digit2 + carry;
            if (sum >= 2) {
                carry = 1;
                sum = sum % 2;
            } else {
                carry = 0;
            }
            result = sum + result;
        }
        if (carry == 1) {
            result = "1" + result;
        }
        result = result.replaceFirst(Constants.SYMBOLS, "");
        return result;
    }

    // Adds two octal numbers and returns the sum as an octal string.
    public static String addOctal(String octal1, String octal2) {
        int maxLength = findMaxLength(octal1, octal2);
        octal1 = padWithZeros(octal1, maxLength);
        octal2 = padWithZeros(octal2, maxLength);
        String result = "";
        int carry = 0;
        for (int i = maxLength - 1; i >= 0; i--) {
            int digit1 = octal1.charAt(i) - '0';
            int digit2 = octal2.charAt(i) - '0';
            int sum = digit1 + digit2 + carry;
            carry = sum / 8;
            result = (sum % 8) + result;
        }
        if (carry > 0) {
            result = carry + result;
        }
        return result;
    }

    // Adds two decimal numbers and returns the sum.
    public static long addDecimal(long decimal1, long decimal2) {
        return decimal1 + decimal2;
    }

    // Adds two hexadecimal numbers and returns the sum as a hexadecimal string.
    public static String addHexadecimal(String hex1, String hex2) {
        int maxLength = findMaxLength(hex1, hex2);
        hex1 = padWithZeros(hex1, maxLength);
        hex2 = padWithZeros(hex2, maxLength);
        String result = "";
        long carry = 0;
        for (int i = maxLength - 1; i >= 0; i--) {
            long digit1 = hexCharToDecimal(hex1.charAt(i));
            long digit2 = hexCharToDecimal(hex2.charAt(i));
            long sum = digit1 + digit2 + carry;
            carry = sum / 16;
            result = decimalToHexChar(sum % 16) + result;
        }
        if (carry > 0) {
            result = decimalToHexChar(carry) + result;
        }
        return result;
    }

    // Converts a decimal number (0-15) to its corresponding hexadecimal character.
    public static char decimalToHexChar(long decimal) {
        if (decimal >= 0 && decimal <= 9) {
            return (char) (decimal + '0');
        } else if (decimal >= 10 && decimal <= 15) {
            return (char) (decimal - 10 + 'A');
        } else {
            throw new IllegalArgumentException("Invalid decimal for hex");
        }
    }

    // Subtracts two binary numbers and returns the difference as a binary string.
    public static String subtractBinary(String binary1, String binary2) {
        boolean isNegative = false;
        if (isGreaterBinary(binary2, binary1)) {
            String temp = binary1;
            binary1 = binary2;
            binary2 = temp;
            isNegative = true;
        }
        int maxLength = findMaxLength(binary1, binary2);
        binary1 = padWithZeros(binary1, maxLength);
        binary2 = padWithZeros(binary2, maxLength);
        String result = "";
        int borrow = 0;

        for (int i = maxLength - 1; i >= 0; i--) {
            int digit1 = binary1.charAt(i) - '0';
            int digit2 = binary2.charAt(i) - '0';
            digit1 = digit1 - borrow;
            if (digit1 < digit2) {
                digit1 += 2;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result = (digit1 - digit2) + result;
        }
        result = result.replaceFirst(Constants.SYMBOLS, "");
        if (isNegative) {
            result = "-" + result;
        }
        return result;
    }

    // Compares two binary numbers and returns true if the first is greater.
    public static boolean isGreaterBinary(String binary1, String binary2) {
        if (binary1.length() > binary2.length()) {
            return true;
        } else if (binary1.length() < binary2.length()) {
            return false;
        }
        for (int i = 0; i < binary1.length(); i++) {
            if (binary1.charAt(i) > binary2.charAt(i)) {
                return true;
            } else if (binary1.charAt(i) < binary2.charAt(i)) {
                return false;
            }
        }
        return false;
    }

    // Subtracts two decimal numbers and returns the result.
    public static long subtractDecimal(long decimal1, long decimal2) {
        return decimal1 - decimal2;  // Simple subtraction in decimal
    }
    
    // Subtracts two hexadecimal numbers and returns the difference as a hexadecimal string.
    public static String subtractHexadecimal(String hex1, String hex2) {
        boolean isNegative = false;
        if (isGreaterHexadecimal(hex2, hex1)) {
            String temp = hex1;
            hex1 = hex2;
            hex2 = temp;
            isNegative = true;
        }
        int maxLength = findMaxLength(hex1, hex2);
        hex1 = padWithZeros(hex1, maxLength);
        hex2 = padWithZeros(hex2, maxLength);
        String result = "";
        int borrow = 0;

        for (int i = maxLength - 1; i >= 0; i--) {
            long digit1 = hexCharToDecimal(hex1.charAt(i));
            long digit2 = hexCharToDecimal(hex2.charAt(i));
            digit1 = digit1 - borrow;
            if (digit1 < digit2) {
                digit1 += 16;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result = decimalToHexChar(digit1 - digit2) + result;
        }
        result = result.replaceFirst(Constants.SYMBOLS, "");
        if (isNegative) {
            result = "-" + result;
        }

        return result;
    }

    // Compares two hexadecimal numbers and returns true if the first is greater.
    public static boolean isGreaterHexadecimal(String hex1, String hex2) {
        if (hex1.length() > hex2.length()) {
            return true;
        } else if (hex1.length() < hex2.length()) {
            return false;
        }
        for (int i = 0; i < hex1.length(); i++) {
            if (hexCharToDecimal(hex1.charAt(i)) > hexCharToDecimal(hex2.charAt(i))) {
                return true;
            } else if (hexCharToDecimal(hex1.charAt(i)) < hexCharToDecimal(hex2.charAt(i))) {
                return false;
            }
        }
        return false;
    }

    // Subtracts two octal numbers and returns the difference as an octal string.
    public static String subtractOctal(String octal1, String octal2) {
        boolean isNegative = false;
        if (isGreaterOctal(octal2, octal1)) {
            String temp = octal1;
            octal1 = octal2;
            octal2 = temp;
            isNegative = true;
        }
        int maxLength = findMaxLength(octal1, octal2);
        octal1 = padWithZeros(octal1, maxLength);
        octal2 = padWithZeros(octal2, maxLength);
        String result = "";
        int borrow = 0;
        for (int i = maxLength - 1; i >= 0; i--) {
            int digit1 = octal1.charAt(i) - '0';
            int digit2 = octal2.charAt(i) - '0';

            digit1 = digit1 - borrow;

            if (digit1 < digit2) {
                digit1 += 8;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result = (digit1 - digit2) + result;
        }
        result = result.replaceFirst(Constants.SYMBOLS, "");
        if (isNegative) {
            result = "-" + result;
        }

        return result;
    }

    // Compares two octal numbers and returns true if the first is greater.
    public static boolean isGreaterOctal(String octal1, String octal2) {
        if (octal1.length() > octal2.length()) {
            return true;
        } else if (octal1.length() < octal2.length()) {
            return false;
        }
        for (int i = 0; i < octal1.length(); i++) {
            if (octal1.charAt(i) > octal2.charAt(i)) {
                return true;
            } else if (octal1.charAt(i) < octal2.charAt(i)) {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean isvalid = true;
        while (isvalid) {
            System.out.println(Constants.CHOICE_PROMPT);
            choice = scanner.nextInt();
            try {
                if (choice <= 0) {
                    System.out.println(Constants.INVALID_CHOICE_MESSAGE);
                }
                if (choice > 21) {
                    System.out.println(Constants.CHOICE_OUT_OF_RANGE_MESSAGE);
                }
            } catch (InputMismatchException e) {
                System.out.println(Constants.INVALID_NUMBER_MESSAGE);
            }

            switch (choice) {
                case 1:
                    // binary to decimal
                    System.out.println(Constants.Task_1);
                    System.out.println(Constants.Binary);
                    scanner.nextLine();
                    String inputString1 = scanner.nextLine();
                    boolean isBinary1 = checkBinary(0, inputString1);
                    if (isBinary1) {
                        long binaryNumber1 = Long.parseLong(inputString1);
                        int position = 0;
                        long decimalResult = 0;
                        long finalDecimal = convertBinaryToDecimal(binaryNumber1, position, decimalResult);
                        System.out.println(Constants.Decimal_Is + finalDecimal + "\n");
                    }
                    break;

                case 2:
                    // octal to decimal
                    System.out.println(Constants.Task_2);
                    System.out.println(Constants.Octal);
                    scanner.nextLine();
                    String inputString2 = scanner.nextLine();
                    boolean isOctal = checkOctal(0, inputString2);
                    if (isOctal) {
                        long octalNumber = Long.parseLong(inputString2);
                        int position = 0;
                        long decimalResult = 0;
                        long finalDecimal = convertOctalToDecimal(octalNumber, position, decimalResult);
                        System.out.println(Constants.Decimal_Is + finalDecimal + "\n");
                    }
                    break;

                case 3:
                    // hexadecimal to decimal
                    System.out.println(Constants.Task_3);
                    System.out.println(Constants.Hexadecimal);
                    scanner.nextLine();
                    String inputString3 = scanner.nextLine();
                    long decimalValue = convertHexToDecimal(inputString3);
                    System.out.println(Constants.Decimal_Is + decimalValue + "\n");
                    break;

                case 4:
                    // decimal to binary
                    System.out.println(Constants.Task_4);
                    System.out.println(Constants.Decimal);
                    scanner.nextLine();
                    String inputString4 = scanner.nextLine();
                    boolean isDecimal = checkDecimal(0, inputString4);
                    if (isDecimal) {
                        long decimalNumber = Long.parseLong(inputString4);
                        System.out.println(Constants.Binary_Is + decimalToBinary(decimalNumber) + "\n");
                    }
                    break;

                case 5:
                    // decimal to octal
                    System.out.println(Constants.Task_5);
                    System.out.println(Constants.Decimal);
                    scanner.nextLine();
                    String inputString5 = scanner.nextLine();
                    boolean isDecimal1 = checkDecimal(0, inputString5);
                    if (isDecimal1) {
                        long decimalNumber = Long.parseLong(inputString5);
                        System.out.println(Constants.Octal_Is + decimalToOctal(decimalNumber) + "\n");
                    }
                    break;

                case 6:
                    // decimal to hexadecimal
                    System.out.println(Constants.Task_6);
                    System.out.println(Constants.Decimal);
                    scanner.nextLine();
                    String inputString6 = scanner.nextLine();
                    boolean isDecimal2 = checkDecimal(0, inputString6);
                    if (isDecimal2) {
                        long decimalNumber = Long.parseLong(inputString6);
                        System.out.println(Constants.HexaDecimal_Is + decimalToHexadecimal(decimalNumber) + "\n");
                    }
                    break;

                case 7:
                    // binary to octal
                    System.out.println(Constants.Task_7);
                    System.out.println(Constants.Binary);
                    scanner.nextLine();
                    String inputString7 = scanner.nextLine();
                    boolean isBinary7 = checkBinary(0, inputString7);
                    if (isBinary7) {
                        long binaryNumber = Long.parseLong(inputString7);
                        int position = 0;
                        long decimalResult = 0;
                        long decimalNumber = convertBinaryToDecimal(binaryNumber, position, decimalResult);
                        long octalNumber = decimalToOctal(decimalNumber);
                        System.out.println(Constants.Octal_Is + octalNumber + "\n");
                    } else {
                        System.out.println(Constants.Invalid_Binary);
                    }
                    break;

                case 8:
                    // binary to hexadecimal
                    System.out.println(Constants.Task_8);
                    System.out.println(Constants.Binary);
                    scanner.nextLine();
                    String inputString8 = scanner.nextLine();
                    boolean isBinary8 = checkBinary(0, inputString8);
                    if (isBinary8) {
                        long binaryNumber = Long.parseLong(inputString8);
                        int position = 0;
                        long decimalResult = 0;
                        long decimalNumber = convertBinaryToDecimal(binaryNumber, position, decimalResult);
                        System.out.println(Constants.HexaDecimal_Is + decimalToHexadecimal(decimalNumber) + "\n");
                    } else {
                        System.out.println(Constants.Invalid_Binary);
                    }
                    break;

                case 9:
                    // octal to binary
                    System.out.println(Constants.Task_9);
                    System.out.println(Constants.Octal);
                    scanner.nextLine();
                    String inputString9 = scanner.nextLine();
                    int index9 = 0;
                    boolean isOctal9 = checkOctal(index9, inputString9);
                    if (isOctal9) {
                        long octalNumber = Long.parseLong(inputString9);
                        long decimalResult = 0;
                        long decimalNumber = convertOctalToDecimal(octalNumber, 0, decimalResult);
                        long binaryNumber = decimalToBinary(decimalNumber);
                        System.out.println(Constants.Binary_Is + binaryNumber + "\n");
                    } else {
                        System.out.println(Constants.Invalid_Octal);
                    }
                    break;
                case 10:
                    // octal to hexadecimal
                    System.out.println(Constants.Task_10);
                    System.out.println(Constants.Octal);
                    scanner.nextLine();
                    String inputString10 = scanner.nextLine();
                    int index10 = 0;
                    boolean isOctal10 = checkOctal(index10, inputString10);
                    if (isOctal10) {
                        long octalNumber = Long.parseLong(inputString10);
                        long decimalResult = 0;
                        long decimalNumber = convertOctalToDecimal(octalNumber, 0, decimalResult);
                        System.out.println(Constants.HexaDecimal_Is + decimalToHexadecimal(decimalNumber) + "\n");
                    } else {
                        System.out.println(Constants.Invalid_Octal);
                    }
                    break;
                case 11:
                    // Hexadecimal to binary
                    System.out.println(Constants.Task_11);
                    System.out.println(Constants.Hexadecimal);
                    scanner.nextLine();
                    String inputString11 = scanner.nextLine();
                    long decimalNUmber = convertHexToDecimal(inputString11);
                    long binaryValue = decimalToBinary(decimalNUmber);
                    System.out.println(Constants.Binary_Is + binaryValue + "\n");
                    break;

                case 12:
                    // Hexadecimal to octal
                    System.out.println(Constants.Task_12);
                    System.out.println(Constants.Hexadecimal);
                    scanner.nextLine();
                    String inputString12 = scanner.nextLine();
                    long decimalNUmber1 = convertHexToDecimal(inputString12);
                    long octalNumber = decimalToOctal(decimalNUmber1);
                    System.out.println(Constants.Binary_Is + octalNumber + "\n");
                    break;

                case 13:
                    // Binary Addition
                    System.out.println(Constants.Task_13);
                    System.out.println(Constants.First);
                    scanner.nextLine(); // Consume the newline character
                    String binaryAdd1 = scanner.nextLine();
                    System.out.println(Constants.Second);
                    String binaryAdd2 = scanner.nextLine();
                    if (!checkBinary(0, binaryAdd1) || !checkBinary(0, binaryAdd2)) {
                        break;
                    }
                    String binarySum = addBinary(binaryAdd1, binaryAdd2);
                    System.out.println(Constants.Result + binarySum);
                    break;

                case 14:
                    // octal addition
                    System.out.println(Constants.Task_14);
                    System.out.println(Constants.First);
                    scanner.nextLine();
                    String octal1 = scanner.nextLine();
                    boolean isOctal1 = checkOctal(0, octal1);
                    if (isOctal1 == false) {
                        break;
                    }
                    System.out.println(Constants.Second);
                    String octal2 = scanner.nextLine();
                    boolean isOctal2 = checkOctal(0, octal2);

                    if (isOctal1 == true && isOctal2 == true) {
                        String octalSum = addOctal(octal1, octal2);
                        System.out.println(Constants.Result + octalSum);
                    }
                    break;

                case 15:
                    // Decimal Addition
                    System.out.println(Constants.Task_15);
                    System.out.println(Constants.First);
                    String decimalA = scanner.next();
                    boolean isDecimalA = checkDecimal(0, decimalA);
                    if (isDecimalA == false) {
                        break;
                    }
                    System.out.println(Constants.Second);
                    String decimalB = scanner.next();
                    boolean isDecimalB = checkDecimal(0, decimalB);

                    if (isDecimalA == true && isDecimalB == true) {
                        long decimal1 = Long.parseLong(decimalA);
                        long decimal2 = Long.parseLong(decimalB);
                        long decimalSum = addDecimal(decimal1, decimal2);
                        System.out.println(Constants.Result + decimalSum);
                    }
                    break;

                case 16:
                    // Hexadecimal addition
                    System.out.println(Constants.Task_16);
                    System.out.println(Constants.First);
                    scanner.nextLine();
                    String hex1 = scanner.nextLine();
                    System.out.println(Constants.Second);
                    String hex2 = scanner.nextLine();
                    String hexSum = addHexadecimal(hex1, hex2);
                    System.out.println(Constants.Result + hexSum);
                    scanner.close();

                case 17:
                    // Binary Subtraction
                    System.out.println(Constants.Task_17);
                    System.out.println(Constants.First);
                    scanner.nextLine();
                    String binary1 = scanner.nextLine();
                    boolean isBinaryA = checkBinary(0, binary1);
                    if (!isBinaryA) {
                        System.out.println(Constants.Invalid_Binary);
                        break;
                    }
                    System.out.println(Constants.Second);
                    String binary2 = scanner.nextLine();
                    boolean isBinaryB = checkBinary(0, binary2);
                    if (!isBinaryB) {
                        System.out.println(Constants.Invalid_Binary);
                        break;
                    }
                    String binaryDifference = subtractBinary(binary1, binary2);
                    System.out.println(Constants.Result + binaryDifference);
                    break;

                case 18:
                    // Octal Subtraction
                    System.out.println(Constants.Task_18);
                    System.out.println(Constants.First);
                    scanner.nextLine();
                    String octalSub1 = scanner.nextLine();
                    boolean isOctalA = checkOctal(0, octalSub1);
                    if (isOctalA == false) {
                        System.out.println(Constants.Invalid_Octal);
                        break;
                    }
                    System.out.println(Constants.Second);
                    String octalSub2 = scanner.nextLine();
                    boolean isOctalB = checkOctal(0, octalSub2);
                    if (isOctalB == false) {
                        System.out.println(Constants.Invalid_Octal);
                        break;
                    }
                    String octalDifference = subtractOctal(octalSub1, octalSub2);
                    System.out.println(Constants.Result + octalDifference);
                    break;
                case 19:
                    // Decimal Subtraction
                    System.out.println(Constants.Task_19);
                    System.out.println(Constants.First);
                    scanner.nextLine();
                    String decimalSub1 = scanner.nextLine();
                    boolean isDecimalC = checkDecimal(0, decimalSub1);
                    if (isDecimalC == false) {
                        break;
                    }
                    System.out.println(Constants.Second);
                    String decimalSub2 = scanner.nextLine();
                    boolean isDecimalD = checkDecimal(0, decimalSub2);
                    if (isDecimalD == false) {
                        break;
                    }
                    long decimal1 = Long.parseLong(decimalSub1);
                    long decimal2 = Long.parseLong(decimalSub2);
                    long decimalDifference = subtractDecimal(decimal1, decimal2);
                    System.out.println(Constants.Result + decimalDifference);
                    break;

                case 20:
                    // Hexadecimal Subtraction
                    System.out.println(Constants.Task_20);
                    System.out.println(Constants.First);
                    scanner.nextLine();
                    String hexSub1 = scanner.nextLine();
                    System.out.println(Constants.Second);
                    String hexSub2 = scanner.nextLine();
                    String hexDifference = subtractHexadecimal(hexSub1, hexSub2);
                    System.out.println(Constants.Result + hexDifference);
                    break;

                case 21:
                    System.out.println(Constants.Exit);    
                    isvalid = false;
                default:
                    System.out.println();
                    break;
            }
        }
        scanner.close();
    }
}
