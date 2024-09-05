import java.util.Scanner;

public class StringOperations {
    private String currentString;

    public StringOperations(String initialString) {
        this.currentString = initialString;
    }

    public void append(String newString) {
        currentString = currentString + newString;
        System.out.println("Updated String: " +  currentString);
    }

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

    public boolean isPalindrome() {
        int len = currentString.length();
        for (int i = 0; i < len / 2; i++) {
            if (currentString.charAt(i) != currentString.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

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
            StringOperations stringOps = new StringOperations(inputString);
    
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
                    case "sort":
                        stringOps.sort();
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
    