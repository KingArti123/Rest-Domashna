package main.java.user.controler;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.user.entity.Poll;
import main.java.user.entity.Votes;
import main.java.user.services.PollService;

@Path("/votes")
public class VotesControler {
	
	@POST
	@Path("/castVote/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String castVote(@PathParam("username")String username, Votes votes ) {
		
		PollService.castVote(username, votes);
		return "One user casted its vote";
		
	}
	
	@GET
	@Path("/getVotesVotedByUser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Votes> getVotesVotedByUser(@PathParam("id")Integer id) {
		return PollService.getVotesVotedByUser(id);
		
	}
	
	@GET
	@Path("/countVotesByPoll/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Integer countVotesByPoll(@PathParam("id")Integer id) {
		
		return PollService.countVotesByPoll(id);
	}
	
/*	@GET
	@Path("/{username}/{pollId}/{choiceId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String castVotes(@PathParam("username") String username,@PathParam("pollId") Integer pollId, @PathParam("choiceId")Integer choiceId) {
		
		return PollService.castVotes(username,pollId,choiceId);
		
		
	}
	*/
	

}
