package main.java.user.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Votes")
public class Votes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	
	private Korisnik korisnik;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	
	private Poll poll;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Choices choices;

	public Votes() {
		super();
	}

	public Votes(Korisnik korisnik, Choices choices, Poll poll) {
		super();
		this.korisnik = korisnik;
		this.choices = choices;
		this.poll = poll;
	}
	
	
	
	

	public Votes(Korisnik korisnik, Choices choices) {
		super();
		this.korisnik = korisnik;
		this.choices = choices;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Choices getChoices() {
		return choices;
	}

	public void setChoices(Choices choices) {
		this.choices = choices;
	}

	
	
}
