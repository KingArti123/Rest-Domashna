package main.java.user.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Korisnik {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
  
private String username;

private String password;

private String email;

private Date createdOn;

private Boolean isAdmin;

private Boolean signedIn;

private Boolean verified;

public Korisnik() {
	super();
}

public Korisnik(String username, String password, String email, Date createdOn, Boolean isAdmin, Boolean signedIn,
		Boolean verified) {
	super();
	this.username = username;
	this.password = password;
	this.email = email;
	this.createdOn = createdOn;
	this.isAdmin = isAdmin;
	this.signedIn = signedIn;
	this.verified = verified;
}



public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Date getCreatedOn() {
	return createdOn;
}

public void setCreatedOn(Date createdOn) {
	this.createdOn = createdOn;
}

public Boolean getIsAdmin() {
	return isAdmin;
}

public void setIsAdmin(Boolean isAdmin) {
	this.isAdmin = isAdmin;
}

public Boolean getSignedIn() {
	return signedIn;
}

public void setSignedIn(Boolean signedIn) {
	this.signedIn = signedIn;
}

public Boolean getVerified() {
	return verified;
}

public void setVerified(Boolean verified) {
	this.verified = verified;
}

@Override
public String toString() {
	return "Korisnik [username=" + username + "]";
}


	


}
