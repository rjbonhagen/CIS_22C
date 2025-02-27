
public class Network {
	private HashedDictionary<String, User> network;
	
	Network() {
		network = new HashedDictionary<String, User>();
	}
	
	public boolean createUser(String first, String last, String email, String password) {
		boolean created = false;
		User newUser = new User(first, last, email, password);
		
		
		if (network.add(newUser.email, newUser) == null) { // if the id does not exist
			network.add(newUser.email, newUser);
			created = true;
		}
		
		return created;	
	}	
	
	public boolean removeUser(String email, String pass) {
		boolean removed = false;
		
		User userToRemove = getUser(email);
		if (userToRemove != null) {
			if (userToRemove.password == pass) {
				User removedUser = network.remove(email);
				if (removedUser != null) {
					removed = true;
				}
			}
		}
		return removed;
	}
	
	public User getUser(String email){
		User user = network.getValue(email);
		if (user == null) {
			System.out.println("No user found with email: " + email);
		}
		return user; 
	} // returns a User or null if none found
	

	
	public void displayNetwork() { network.displayHashTable(); }

}
