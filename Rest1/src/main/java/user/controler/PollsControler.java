package main.java.user.controler;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import main.java.user.entity.Poll;
import main.java.user.entity.PollChoiceRes;
import main.java.user.entity.PollChoices;
import main.java.user.services.PollService;

@Path("/poll")
public class PollsControler {
	
	@POST
	@Path("/createPoll/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createPoll( @PathParam("username") String username, Poll poll) {
		
		PollService.createPoll(username,poll);
		
		return "Poll was created";
		
	}
	
	@GET
	@Path("/getPollById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Poll getPollById(@PathParam("id") Integer id , Poll poll) {
		
		
		
		
		return PollService.getPollById(id,poll);
		
	}
	
	@GET
	@Path("/getAllPolls/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Poll> getAllPolls(@PathParam("username")String username){
		return PollService.getAllPolls(username);
		
	}
	
	@DELETE
	@Path("/deletePoll/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deletePoll(@PathParam("id")Integer id, Poll poll) {
		
		PollService.deletePoll(id,poll);
		
		return "The poll was deleted";
		
	}
	@PUT
	@Path("/updatePoll/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String updatePoll(@PathParam("username") String username, Poll poll) {
		
		PollService.updatePoll(username, poll);
		
		return "The poll with id: "+ poll.getId()+" was updated";
		
	}
	
	@GET
	@Path("/getAllPollsCreatedByKorisnik/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Poll>getAllPollsCreatedByKorisnik(@PathParam("username")String username ,Poll poll){
		
		return PollService.getAllPollsCreatedByKorisnik(username,poll);
	}
	@GET
	@Path("/getAllPollsCreatedInTheLastDay/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Poll>getAllPollsCreatedInTheLastDay(@PathParam("username")String username){
		
		return PollService.getAllPollsCreatedInTheLastDay(username );
		
	}
	
	@GET
	@Path("/getAllPollsVotedByUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Poll>getAllPollsVotedByUser(@PathParam("id")Integer id){
		
		return PollService.getAllPollsVotedByUser(id);
		
	}
	
	@POST
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public String createPoll2(@PathParam("username") String username, PollChoices request) {
		Integer pollId = PollService.createPoll2(username, request);
		
		return "Poll with id: " + pollId + " IS CREATED";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PollChoiceRes> getPolls() {
		return PollService.getPolls();
		
	}
	
	
}
	

