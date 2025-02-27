import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {
		Network socialNetwork = new Network();
		
		System.out.println("Welcome to the Social Network, type '!help' for a list of commands or 'exit' to exit" );
		
		input(socialNetwork);
		
	}
	
	public static void input(Network socialNetwork) {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			 String input = scanner.nextLine();
			 
			 if (input.equals("exit")) {
				 System.out.println("Exiting...");
				 break;
			 }
			 if (input.equals("!help")) {
				 System.out.println("/join - creates a new profile");
				 System.out.println("/leave - deletes your new profile");
				 System.out.println("/view- shows all users");
				 System.out.println("/add- add a friend!");
				 
				 
			 }
			 
			 if (input.equals("/join")) { 
				 System.out.println("Enter first name: ");
				 String first = scanner.nextLine();
				 System.out.println("Enter last name: ");
				 String last = scanner.nextLine();
				 System.out.println("Enter email: ");
				 String email = scanner.nextLine();
				 System.out.println("Enter password: ");
				 String pass = scanner.nextLine();
				 if (socialNetwork.createUser(first, last, email, pass)) {
					 System.out.println("User joined successfuly");
				 }
				 else {
					 System.out.println("Invalid Arguments");
				 }
			 }
			 
			 if (input.equals("/leave")) { 
				 System.out.println("Enter email: ");
				 String email = scanner.nextLine();
				 System.out.println("Enter password: ");
				 String pass = scanner.nextLine();
				 if (socialNetwork.removeUser(email, pass)) {
					 System.out.println("User left successfuly");
				 }
				 else {
					 System.out.println("Invalid Arguments");
				 }
			 }
			 
			 
			 if (input.equals("/view")) { 
				 socialNetwork.displayNetwork();
			 }
			 
		}
		scanner.close();
	}
	

}
