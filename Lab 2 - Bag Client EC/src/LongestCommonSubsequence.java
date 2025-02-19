
import java.io.*;
import java.util.*;

/**
 * LongestCommonSubsequence is a program that will determine the longest string that is 
 * a subsequence of two input strings. This program applies a brute force solution technique.
 * 
 * @author Charles Hoot
 * @version 4.0
 */
public class LongestCommonSubsequence {

    public static void main(String args[]) {
        BagInterface<String> toCheckContainer = null;

        Scanner input;
        input = new Scanner(System.in);

        System.out.println("This program determines the longest string that is a subsequence of two input string.");
        System.out.println("Please enter the first string:");
        String first = input.next();

        System.out.println("Please enter the second string:");
        String second = input.next();



        // ADD CODE HERE TO CREATE THE BAG WITH THE INITIAL STRING
        toCheckContainer = new ArrayBag<String>();
        toCheckContainer.add(first);
        
        

        System.out.println("The strings to check are: " + toCheckContainer);
        String bestSubsequence = new String("");


        // ADD CODE HERE TO CHECK THE STRINGS IN THE BAG
        while(!toCheckContainer.isEmpty()) {
            String test = toCheckContainer.remove();
            if (bestSubsequence.length() < test.length()) {
            	if (isSubsequence(test, second)) {
            		bestSubsequence = test;
            	}
            	else if (test.length() >= (bestSubsequence.length() + 2)) {
            		for (int i = 0; i < test.length(); i++) {
            			String substr;
            			substr = test.substring(0, i) + test.substring(i + 1);
            			toCheckContainer.add(substr);
            		}
            	}
            }
            System.out.println(toCheckContainer.toString());
        }
        
        
        System.out.println("Found " + bestSubsequence + " for the longest common subsequence");

    }

    /**
     * Determine if one string is a subsequence of the other.
     *
     * @param check See if this is a subsequence of the other argument.
     * @param other The string to check against. 
     * @return     A boolean if check is a subsequence of other. 
     */
    
    public static boolean isSubsequence(String check, String against) {
    	boolean result = false;
    	if (against.length() < check.length() ) { return result; }
    	
		int againstStartIndex = 0;
		boolean found;
    	for (int i = 0; i < check.length(); i++) {
    		found = false;
    		for (int j = againstStartIndex; j < against.length(); j++) {
    			if (against.charAt(j) == check.charAt(i)) {
    				found = true;
    				againstStartIndex = j + 1;
    				break;
    			}
    		}
    		if (found) {
    			result = true;
    		}
    		else return result = false;
    	}
    	return result;
    }
}