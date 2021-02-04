package main.java.user.controler;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.user.entity.Korisnik;
import main.java.user.entity.Profile;
import main.java.user.entity.UserResponse1;
import main.java.user.entity.UsernameAddress;
import main.java.user.services.AllServices;

@Path("/admin")
public class AdminControler {
	
	
	//VERIFIKACIJA
	@PUT
	@Path("/verify/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String verifyKorisnik(@PathParam("id")Integer id , Korisnik korisnik) {
		
		AllServices.verifyKorisnik(id,korisnik);
		
		return "The korisnik with Id: " + korisnik.getId() + " was verified";
		
	}
	
	//BRISENJE
	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteKorisnik(@PathParam("id")Integer id, Korisnik korisnik) {
		
		AllServices.deleteKorisnik(id,korisnik); 
		
		return "The korisnik was deleted";
		
	}
    //SITE KORISNICI
	@GET
	@Path("/korisnici/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Korisnik> getAllKorisnik(@PathParam("id")Integer id, Korisnik korisnik) {
		
		return AllServices.getAllKorisnik(id, korisnik);
		}	
	
	
	//NEVERIFIKUVANI
	@GET
	@Path("/uvkorisnici/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Korisnik> getAllUnverifiedKorisniks(@PathParam("id")Integer id, Korisnik korisnik) {
		
		return AllServices.getAllUnverifiedKorisniks(id, korisnik);
		
	}
	
	//NEVERIFIKUVANI2
		@GET
		@Path("/getUnverified/{username}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Korisnik> getUnverified(@PathParam("username") String username){
			return AllServices.getUnverified(username);
			
		}
		
		
		@GET
		@Path("/getUnverifiedList/{username}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<UserResponse1> getList(@PathParam("username") String username){
			return AllServices.getList(username);
	
		}
	//VERIFIKUVANI
	@GET
	@Path("/vkorisnici/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Korisnik> getAllVerifiedKorisniks(@PathParam("id")Integer id, Korisnik korisnik) {
		
		return AllServices.getAllVerifiedKorisniks(id, korisnik);
		
	}
	@GET
	@Path("/getUsersWithProfiles/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Profile> listProfiles(@PathParam("username") String username){
		return AllServices.listProfiles(username);
		
		
	}
	
	@PUT
	@Path("/verifyAll/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Korisnik> verifyAllUnverified(@PathParam("username") String username) {
		
		return AllServices.verifyAllUnverified(username);
		
		
	}
	
	@PUT
	@Path("/signOutAll/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Korisnik>signOutAll(@PathParam("username") String username) {
		
		return AllServices.signOutAll(username);
		
	}
	
	@GET
	@Path("/userAddress/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UsernameAddress> userAddress(@PathParam("username") String username){
		return AllServices.userAddress(username);
		
	}
	
	
}

