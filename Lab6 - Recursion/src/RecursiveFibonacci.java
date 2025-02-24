
/**
 * A class holding different recursive metholds to compute fibonacci numbers.
 * 0, 1, 1, 2, 3, 5, 8, ...
 * 
 * @author Charles Hoot
 * @version 4.0
 */
public class RecursiveFibonacci
{


    /**
     * basic - The simple version of fibonacci.
     * 
     * @param  n   A positive integer. 
     * @return     The nth fibonacci number.
     */
    public long basic(long n)
    {
        long result = 1;
        
        if(n <= 0)
            result = 0;
        else if (n == 1)
            result = 1;
        else
            result = basic(n-1) + basic(n-2);
        
        return result;
    }
    
    
    /**
     * better - A better version of fibonacci. (Height Limited Double Recursion)
     * 
     * @param  n   A positive integer.
     * @return     The nth fibonacci number.
     */
    public long better(long n)
    {
        long result = 0;
        if (n <= 0) {
        	result = 0;
        }
        else if (n == 1) {
        	result = 1;
        }
        else if (n % 2 != 0) {
        	
        	result = 2*better(n/2)*better(n/2) + 2*better(n/2)*better(n/2 - 1) + better(n/2 - 1)*better(n/2 - 1);
        }
        else {
        	result = better(n/2)*better(n/2) + 2*better(n/2)*better(n/2 - 1);
        }
        return result;
    }


    /**
     * tailRecursive - A tail recursive version of fibonacci. 
     *              (Height limited, Two problems per level)
     * 
     * @param  n   A positive integer. 
     * @return     Tge nth fibonacci number.
     */
    public long tailRecursive(long n) {
        // Base cases
        if (n == 0) return 0;
        if (n == 1) return 1;

        // Check the second most significant bit
        if (secondMSB(n)) {
            // If the 2nd MSB is 1, reduce n by removing the 2nd MSB
            long reducedN = reduceBy2ndMSB(n);
            // Compute Fibonacci using the reduced n
            return tailRecursive(reducedN);
        } else {
            // If the 2nd MSB is 0, proceed with iterative computation
            long a = 0; // F(0)
            long b = 1; // F(1)
            long result = 0;

            for (long i = 2; i <= n; i++) {
                result = a + b; // F(i) = F(i-1) + F(i-2)
                a = b;          // Update F(i-2) to F(i-1)
                b = result;     // Update F(i-1) to F(i)
            }

            return result;
        }
    } 
    

    /**
     * secondMSB - Determine the value of the second most significant bit.
     * 
     * @param  n   A positive integer 
     * @return     True if the second most significant bit is 1, false otherwise.
     */    
    public boolean secondMSB(long n) {
    	boolean result = false;
    	
    	String binaryString = Long.toBinaryString(n);
    	
    	
    	if (binaryString.length() > 1 && binaryString.charAt(1) == 1) {
    		result = true;
    	}	
    	
    	return result;
    }


    /**
     * reduceBy2ndMSB - Reduce the number by removing the second most significant bit
     * from the representation.
     * 
     * @param  n   A positive integer > 1
     * @return     The integer value equivalent to removing the 2nd most significant bit
     *              from n.
     */    
    public long reduceBy2ndMSB(long n)
    {
        long result = 1;
        
        String binaryString = Long.toBinaryString(n); 
        binaryString = binaryString.substring(0, 1) + binaryString.substring(2);
        
        result = binaryToInt(binaryString);
   
        return result;
    }
    
    private static int binaryToInt (String binary){
        char []cA = binary.toCharArray();
        int result = 0;
        for (int i = cA.length-1;i>=0;i--){
            //111 , length = 3, i = 2, 2^(3-3) + 2^(3-2)
            //                    0           1  
            if(cA[i]=='1') result+=Math.pow(2, cA.length-i-1);
        }
        return result;
    }
    
    
}
