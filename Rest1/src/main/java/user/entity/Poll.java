package main.java.user.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

public class Poll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String topic;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Korisnik korisnik;
	
	private Date createdOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Poll() {
		super();
	}

	public Poll(String topic, Korisnik korisnik, Date createdOn) {
		super();
		this.topic = topic;
		this.korisnik = korisnik;
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "Poll [id=" + id + ", topic=" + topic + ", korisnik=" + korisnik + ", createdOn=" + createdOn + "]";
	}

	
	
	
}
