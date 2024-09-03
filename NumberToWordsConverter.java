/***
 * Task 4: Number to Words Converter.
 * Author Name : Tanishq Dosaya
 * Date : 02/09/2024
 */

import java.util.Scanner;

public class NumberToWordsConverter {

    private static final String[] LESS_THAN_20 = {
        "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
        "eighteen", "nineteen"
    };
    
    private static final String[] TENS = {
        "zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    private static final String[] THOUSANDS = {"", "thousand"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number between 1 and 1000:");
        int number = scanner.nextInt();

        if (number < 1 || number > 1000) {
            System.out.println("Number out of range.");
        } else {
            System.out.println(convertToWords(number));
        }

        scanner.close();
    }

    private static String convertToWords(int num) {
        if (num == 1000) {
            return "one thousand";
        }
        StringBuilder words = new StringBuilder();
        if (num >= 100) {
            words.append(LESS_THAN_20[num / 100]).append(" hundred");
            num %= 100;
            if (num > 0) {
                words.append(" ");
            }
        }
        if (num >= 20) {
            words.append(TENS[num / 10]);
            num %= 10;
            if (num > 0) {
                words.append(" ");
            }
        }
        if (num > 0) {
            words.append(LESS_THAN_20[num]);
        }
        return words.toString().trim();
    }
}