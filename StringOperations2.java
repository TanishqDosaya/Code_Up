/***
 * Assignment => 2
 * Author Name => Tanishq Dosaya
 * Date => 05/09/2024
 */


import java.util.Scanner;

public class StringOperations2 {
    private String currentString;

    public StringOperations2(String initialString) {
        this.currentString = initialString;
    }

    // Method 1. append()
    public void append(String newString) {
        currentString = currentString + newString;
        System.out.println("Updated String: " +  currentString);
    }
    
    // Method 2. countWords()
    public int countWords() {
        int count = 0;
        boolean isWord = false;
        for (int i = 0; i < currentString.length(); i++) {
            if (currentString.charAt(i) != ' ') {
                isWord = true;
            } else if (isWord) {
                count++;
                isWord = false;
            }
        }
        if (isWord) {
            count++;
        }
        return count;
    }

    // Method 3. replace()
    public void replace(String a, String b) {
          String newString = "";
          int aLength = a.length();
    
          for (int i = 0; i < currentString.length(); i++) {
              boolean matchFound = true;

              for (int j = 0; j < aLength; j++) {
                  if (i + j >= currentString.length() || currentString.charAt(i + j) != a.charAt(j)) {
                      matchFound = false;
                      break;
                  }
              }
              if (matchFound) {
                  newString += b;
                  i += aLength - 1; 
              } else {
                  newString += currentString.charAt(i);
              }
           }
           currentString = newString;
           System.out.println("Updated String: " + currentString);
    }

    // Method 4. isPalindrome()
    public boolean isPalindrome() {
        int len = currentString.length();
        for (int i = 0; i < len / 2; i++) {
            if (currentString.charAt(i) != currentString.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    // Method 5. splice()
    public void splice(int start, int length) {
        String newString = "";
        for (int i = 0; i < currentString.length(); i++) {
            if (i < start || i >= start + length) {
                newString += currentString.charAt(i);
            }
        }
        currentString = newString;
        System.out.println("Updated String: " + currentString);
    }

    // Method 6. split()
    public String[] split(char pattern) {
        int wordCount = 1;
        for (int i = 0; i < currentString.length(); i++) {
            if (currentString.charAt(i) == pattern) {
                wordCount++;
            }
        }
    
        String[] words = new String[wordCount];
        char[] currentWord = new char[currentString.length()];
        int wordIndex = 0, charIndex = 0;
    
        for (int i = 0; i < currentString.length(); i++) {
            if (currentString.charAt(i) == pattern) {
                words[wordIndex] = new String(currentWord, 0, charIndex);
                wordIndex++;
                charIndex = 0;
            } else {
                currentWord[charIndex] = currentString.charAt(i);
                charIndex++;
            }
        }
    
        words[wordIndex] = new String(currentWord, 0, charIndex);
    
        return words;
    }

    // Method 7. maxRepeat()
    public char maxRepeat() {
        int[] charCount = new int[256];
        for (int i = 0; i < currentString.length(); i++) {
            charCount[currentString.charAt(i)]++;
        }

        int max = -1;
        char result = ' ';
        for (int i = 0; i < currentString.length(); i++) {
            if (charCount[currentString.charAt(i)] > max) {
                max = charCount[currentString.charAt(i)];
                result = currentString.charAt(i);
            }
        }
        return result;
    }

    // Method 8. sort()
    public void sort() {
        char[] chars = new char[currentString.length()];
        
        for (int i = 0; i < currentString.length(); i++) {
            chars[i] = currentString.charAt(i);
        }
    
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] > chars[j]) {
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
            }
        }
        String sortedString = "";
        for (int i = 0; i < chars.length; i++) {
            sortedString += chars[i];
        }
        currentString = sortedString;
        System.out.println("Sorted String: " + currentString);
    }
    
    // Method 9. shift()
    public void shift(int n) {
        int len = currentString.length();
        n = n % len;
        String shiftedString = "";

        for (int i = n; i < len; i++) {
            shiftedString += currentString.charAt(i);
        }

        for (int i = 0; i < n; i++) {
            shiftedString += currentString.charAt(i);
        }
        currentString = shiftedString;
        System.out.println("Shifted String: " + currentString);
    }
    
    // Method 10. reverse()
    public void reverse() {
        char[] reversedChars = new char[currentString.length()];
        int len = currentString.length();
        
        for (int i = 0; i < len; i++) {
            reversedChars[i] = currentString.charAt(len - 1 - i);
        }
    
        String reversedString = "";
        for (int i = 0; i < len; i++) {
            reversedString += reversedChars[i];
        }
    
        currentString = reversedString;
        System.out.println("Reversed String: " + currentString);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        while (true) {
            System.out.println("Enter a string:");
            String inputString = scanner.nextLine();
            StringOperations2 stringOps = new StringOperations2(inputString);
    
            while (true) {
                System.out.println("\nChoose an operation: append, countWords, replace, isPalindrome, splice, split, maxRepeat, sort, shift, reverse, exit");
                String choice = scanner.nextLine();
    
                switch (choice) {
                    case "append":
                        System.out.println("Enter the string to append:");
                        String appendStr = scanner.nextLine();
                        stringOps.append(appendStr);
                        break;
                    case "countWords":
                        int wordCount = stringOps.countWords();
                        System.out.println("Word count: " + wordCount);
                        break;
                    case "replace":
                        System.out.println("Enter the string to replace:");
                        String replaceStrA = scanner.nextLine();
                        System.out.println("Enter the replacement string:");
                        String replaceStrB = scanner.nextLine();
                        stringOps.replace(replaceStrA, replaceStrB);
                        break;
                    case "isPalindrome":
                        boolean isPal = stringOps.isPalindrome();
                        System.out.println("Is palindrome: " + isPal);
                        break;
                    case "splice":
                        System.out.println("Enter the start index:");
                        int startIndex = scanner.nextInt();
                        System.out.println("Enter the length:");
                        int length = scanner.nextInt();
                        scanner.nextLine();
                        stringOps.splice(startIndex, length);
                        break;
                    case "split":
                        System.out.println("Enter the character to split on:");
                        char splitChar = scanner.next().charAt(0);
                        scanner.nextLine();
                        String[] words = stringOps.split(splitChar);
                        System.out.println("Split result: ");
                        for (String word : words) {
                            System.out.println(word);
                        }
                        break;
                    case "maxRepeat":
                        char maxChar = stringOps.maxRepeat();
                        System.out.println("Max repeating character: " + maxChar);
                        break;
                    case "sort":
                        stringOps.sort();
                        break;
                    case "shift":
                        System.out.println("Enter the index:");
                        int n = scanner.nextInt();
                        scanner.nextLine();
                        stringOps.shift(n);
                        break;                    
                    case "reverse":
                        stringOps.reverse();
                        break;
                    case "exit":
                        System.out.println("Exiting program.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid operation. Try again.");
                }
    
                System.out.println("\nDo you want to perform another operation on the same string? (yes/no)");
                String continueChoice = scanner.nextLine();
                if (!continueChoice.equalsIgnoreCase("yes")) {
                    break;
                }
            }
        }
    
    
    }
}
    