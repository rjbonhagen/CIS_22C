public class User {
	String firstName;
	String lastName;
	String email;
	String phone;
	protected String password;
	LinkedList<User> friendList;
	int friends;
	

	User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		email = "";
		phone = "";
		friendList = new LinkedList<User>();
		friends = friendList.getLength();
	}
	
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	public boolean setPassword(String oldPass, String newPass) {
		boolean changed = false;
		if (this.password == oldPass) {
			this.password = newPass;
			changed = true;
		}
		return changed;
	}
	
	public void setEmail(String email) { this.email = email; }
	
	public void setPhone(String phone) { this.phone = phone; }
	
	
	public void addFriend(User friend) {
		
		if (friend != null) {
			friendList.add(friend);
			friends = friendList.getLength();
		}
		else {
			System.out.println("User not found");
		}
	}
	public boolean removeFriend(User friend) {
		boolean removed = false;
		if (friend != null) {
			int pos = -1;
			for (int i = 0; i < friendList.getLength(); i++) {
				if (friendList.getEntry(i) == friend) {
					pos = i;
				}
			}
			
			if (pos != -1) {
				friendList.remove(pos);
				removed = true;
			}
			friends = friendList.getLength();
		}
		else {
			System.out.println("User not found");
		}
		
		return removed;
	}
	
	
	
	@Override
	public String toString() {
		return "User: " + firstName + " " + lastName + " | Email: " + email + " | Phone: " + phone +  " | Friends: " + friends;
	}
	
}