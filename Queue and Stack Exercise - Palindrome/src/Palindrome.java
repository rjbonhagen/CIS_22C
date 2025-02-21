import java.util.Scanner;
public class Palindrome {
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Input a a string to check if its a palindrome: ");
		String str = scanner.nextLine();
		str = str.toLowerCase();
		System.out.println("Palindrome?: " + checkPalindrome(str));
		
		
		
		scanner.close();
	}
	
	public static boolean checkPalindrome(String str) {
		boolean result = false;
		StackInterface<Character> stack = new LinkedStack<Character>();
		QueueInterface<Character> queue = new LinkedQueue<Character>();
		
		for (char c : str.toCharArray()) {
			stack.push(c);
			queue.enqueue(c);
		}
		
		while (!stack.isEmpty()) {
			char c  = stack.pop();
			char q = queue.dequeue();
			if (c != q) {
				result = false;
				break;
			}
			else 
				result = true;
			
		}
		
		return result;
	}
}
