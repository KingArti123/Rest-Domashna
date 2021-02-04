package main.java.user.controler;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.user.entity.Korisnik;
import main.java.user.entity.SignInRequest;
import main.java.user.services.AllServices;

@Path("/korisnik")
public class KorisnikControler {
	
	//KREIRANJE NA KORISNIK
	@POST
	@Path("/signUp")
	@Consumes(MediaType.APPLICATION_JSON)
	public String signUp(Korisnik korisnik) {
		
		AllServices.signUp(korisnik);
		
		return "Korisnik with id: " + korisnik.getId() + " has signed up!!!";
		
	}
	//UPDATE NA KORISNIK
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateKorisnik(@PathParam("id") Integer id, Korisnik korisnik) {
		
		AllServices.updateKorisnik(id,korisnik);
		
		return "The korisnik with id: " + korisnik.getId() + " was updated!!!";
		
	}
	//LOGIRANJE NA KORISNIK
	@POST
	@Path("/signIn")
	@Consumes(MediaType.APPLICATION_JSON)
	public String signIn(SignInRequest request) {
		
		AllServices.signIn(request);
		
		return "The korisnik was signed in";
		 
	}
	//SIGNOUT
	@GET
	@Path("/signOut/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String signOut(@PathParam("username") String username) {
		
		AllServices.signOut(username);
		
		return "The korisnik with username " + username + " was logged out";
	}
	
}
