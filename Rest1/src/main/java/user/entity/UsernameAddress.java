package main.java.user.entity;

public class UsernameAddress {
	
	private String username;
	
	private String address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UsernameAddress [username=" + username + ", address=" + address + "]";
	}

	
	

}
