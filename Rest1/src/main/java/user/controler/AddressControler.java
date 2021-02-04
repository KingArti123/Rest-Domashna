package main.java.user.controler;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import main.java.user.entity.Address;
import main.java.user.services.AllServices;

@Path("/address")
public class AddressControler {
	//KREIRANJE NA ADRESI
	@POST
	@Path("createAddress")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createKorisnikAddress(Address address) {
		
		AllServices.createKorisnikAddress(address);
		
		return "The address with Id: " + address.getId() + " was created";
		
	}
	
	//UPDATE NA ADRESI
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateKorisnikAddress(@PathParam("id")Integer id,Address address) {
		
		AllServices.updateKorisnikAddress(id, address);
		
		return "The Address with ID: " + address.getId()+ " was updated";
		
	}

}
