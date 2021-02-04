package main.java.user.controler;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


import main.java.user.entity.Profile;
import main.java.user.services.AllServices;

@Path("/profile")
public class ProfileControler {
    //KREIRANJE NA PROFILI
	@POST
	@Path("createProfile/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createKorisnikProfile(@PathParam("username")String username, Profile profile) {
		
		AllServices.createKorisnikProfile(username,profile);
		
		return "The prolile for korisnik profile was completed!!!";
		
	}
	
	//UPDATE NA PROFILI
	@POST
	@Path("/updateProfile/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateKorisnikProfile(@PathParam("username")String username,Profile profile) {
		
		AllServices.updateKorisnikProfile(username , profile);
		
		return "The korisniks profile with id: " + profile + " was updated";
		
	}
	
	
}
