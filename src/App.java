/**	
 * @author: Prithviraj Deshmane
 * 
 * Problem Source:
 * Site: https://adventofcode.com
 * Year: 2023
 * Day: 1
 * Problem: #2
 * Problem Statement: https://adventofcode.com/2023/day/1 (part 2)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Advent of Code 2023, Day 1, Problem #2 solution
 * This program reads a file line by line, extracts the first and last digits 
 * (either numeric or word-based) from each line, forms a number from those digits, 
 * and sums all the numbers extracted.
 */
public class App {

    private static int firstDigit;
    private static int lastDigit;

    public static void main(String[] args) {

        int sum = 0;

        try (Scanner sc = new Scanner(new File("puzzleInput.txt"))) {
            
        	// Loop through each line of the file
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();  // Clean up any leading/trailing spaces

                // Reset first and last digits for each new line
                firstDigit = -1;
                lastDigit = -1;

                // Process each character in the line
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);

                    // If the character is a digit, directly process it
                    if (Character.isDigit(c)) {
                        setDigits(Character.getNumericValue(c));
                    } else {
                        // Check for words that represent numbers
                        processWord(c, i, line);
                    }
                }

                // If valid digits were found, calculate the number formed and add it to the sum
                if (firstDigit != -1 && lastDigit != -1) {
                    int numberExtracted = (firstDigit * 10) + lastDigit;
                    // System.out.println("Number extracted: " + numberExtracted);
                    sum += numberExtracted;
                }
            }
        } catch (FileNotFoundException e) {
        	System.out.println("Input file not found at the provided path");
            e.printStackTrace();  // Handle file not found error
        }

        // Display final sum of all extracted numbers
        System.out.println("Total sum: " + sum);
    }

    /**
     * Helper method to process word-based numbers and set the digits.
     * @param c Character at the current position.
     * @param i Current index in the line.
     * @param line The current line being processed.
     */
    private static void processWord(char c, int i, String line) {
        switch (c) {
            case 'o':
                // Check for the word "one"
                if (i + 3 <= line.length() && line.substring(i, i + 3).equals("one")) {
                    setDigits(1);
                }
                break;
                
            case 't':
                // Check for "two" or "three"
                if (i + 5 <= line.length() && line.substring(i, i + 5).equals("three")) {
                    setDigits(3);
                } else if (i + 3 <= line.length() && line.substring(i, i + 3).equals("two")) {
                    setDigits(2);
                }
                break;
                
            case 'f':
                // Check for "four" or "five"
                if (i + 4 <= line.length()) {
                    String subStr = line.substring(i, i + 4);
                    if (subStr.equals("four")) {
                        setDigits(4);
                    } else if (subStr.equals("five")) {
                        setDigits(5);
                    }
                }
                break;
            
            case 's':
                // Check for "six" or "seven"
                if (i + 5 <= line.length() && line.substring(i, i + 5).equals("seven")) {
                    setDigits(7);
                } else if (i + 3 <= line.length() && line.substring(i, i + 3).equals("six")) {
                    setDigits(6);
                }
                break;
            
            case 'e':
                // Check for "eight"
                if (i + 5 <= line.length() && line.substring(i, i + 5).equals("eight")) {
                    setDigits(8);
                }
                break;
            
            case 'n':
                // Check for "nine"
                if (i + 4 <= line.length() && line.substring(i, i + 4).equals("nine")) {
                    setDigits(9);
                }
                break;
            
            case 'z':
                // Check for "zero"
                if (i + 4 <= line.length() && line.substring(i, i + 4).equals("zero")) {
                    setDigits(0);
                }
                break;
            
            default:
                // Ignore unknown characters
                break;
        }
    }

    /**
     * Helper method to set the first and last digits.
     * @param n The digit to be set.
     */
    private static void setDigits(int n) {
        // Set first digit if it's the first number encountered
        if (firstDigit == -1) firstDigit = n;

        // Always update the last digit encountered
        lastDigit = n;
    }
}
