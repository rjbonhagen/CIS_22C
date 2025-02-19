
public class Counter
{

	int count;
	int min;
	int max;
	boolean rolledOver;

    public Counter()
    {
       min = 0;
       count = min;
       max = Integer.MAX_VALUE;
    }
    
    public Counter(int min, int max)
    {
    	if (min < max) {
	        this.min = min;
	        this.max = max;
	        count = this.min;
    	}
    	else throw new CounterInitializationException("Illegal maximum value, must be greater than minimum");
    }

    public boolean equals(Object otherObject)
    {
        boolean result = true;
        if (otherObject instanceof Counter)
        {
        	Counter counter = (Counter)otherObject;
            if (this.min == counter.min && this.max == counter.max && this.count == counter.count && this.rolledOver == counter.rolledOver) {
            	result = true;
            }
            else result = false;
        }
        return result;
    }
    

    public void increase() {
    	if(count < max) {
    		count++;
    		rolledOver = false;
    	}
    	else {
    		count++;
    		rolledOver = true;
    		count = min;
    	}
    }
 

    public void decrease() {
    	if(count > min) {
    		count--;
    		rolledOver = false;
    	}
    	else {
    		count--;
    		rolledOver = true;
    		count = max;
    	}
    }
    

    public int value() { return count; }
    
    public boolean rolledOver() { return rolledOver; }
    
    public String toString() {
        return "\nCount: " + count + 
        		"\nMin: " + min + 
        		"\nMax: " + max +
        		"\nRolled Over:" + rolledOver;		
    }
    
}


