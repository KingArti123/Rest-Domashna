package main.java.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private Integer age;
	
	private String interests;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Address address;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Korisnik korisnik;
	
	

	public Profile() {
		super();
	}

    public Profile(String firstName, String lastName, Integer age, String interests, Address address,
			Korisnik korisnik) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.interests = interests;
		this.address = address;
		this.korisnik = korisnik;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Integer getAge() {
		return age;
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	public String getInterests() {
		return interests;
	}



	public void setInterests(String interests) {
		this.interests = interests;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public Korisnik getKorisnik() {
		return korisnik;
	}



	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}



	
	
	
}
