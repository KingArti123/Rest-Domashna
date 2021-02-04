package main.java.user.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import main.java.user.entity.Address;
import main.java.user.entity.Choices;
import main.java.user.entity.Korisnik;
import main.java.user.entity.Poll;
import main.java.user.entity.PollChoiceRes;
import main.java.user.entity.PollChoices;
import main.java.user.entity.Profile;
import main.java.user.entity.Votes;


public class PollService {
	
static SessionFactory factory;
	
	public static Session init() {
		
		Configuration cfg = new Configuration();
		
		cfg.addAnnotatedClass(main.java.user.entity.Korisnik.class);
		cfg.addAnnotatedClass(main.java.user.entity.Address.class);
		cfg.addAnnotatedClass(main.java.user.entity.Profile.class);
		cfg.addAnnotatedClass(main.java.user.entity.Poll.class);
		cfg.addAnnotatedClass(main.java.user.entity.Choices.class);
		cfg.addAnnotatedClass(main.java.user.entity.Votes.class);
		
		cfg.configure();
		
		factory = cfg.configure().buildSessionFactory();
		
		Session session = factory.openSession();
		return session;
		
		
	}

	public static void createPoll(String username, Poll poll) {
		
       Session session =init();
		
		Transaction t = null;
		
		try {
			
			t = session.beginTransaction();
			Date date = new Date(System.currentTimeMillis());
			Query query = session.createQuery("FROM main.java.user.entity.Korisnik k WHERE k.username=:username ");
		    query.setParameter("username", username);
			
		    Korisnik korisnik = (Korisnik) query.getResultList().get(0);
		    
		    if(korisnik.getVerified() == true) {
		    
		    Poll poll1 = new Poll(poll.getTopic(), korisnik, poll.getCreatedOn());
		    poll1.setCreatedOn(date);
		    session.save(poll1);
		    }
			t.commit();
			session.close();
			
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
	}

	public static Poll getPollById(Integer id, Poll poll) {
		
        Session session =init();
		
		Transaction t = null;
		try {
			t = session.beginTransaction();
			Poll poll1 = session.get(Poll.class, id);
			
			if(poll1.getId() != null) {
			
			Query query = session.createQuery("SELECT p FROM main.java.user.entity.Poll p WHERE p.id=:id");
		    query.setParameter("id", id);
		    
		    Poll poll2 = (Poll) query.getResultList().get(0);
		    t.commit();
		    session.close();
		    
		    System.out.println(poll2);
		    return poll2;
		   }else {
				System.out.println("fsfsdfs");
			}
			
			} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		return null;
	}
	
	private static boolean isAdministrator(String username) {
		
		
	    Session session =init();
	    	
	    	Transaction t = null;
	    	
	    	try {
	    	  	
	        	t = session.beginTransaction();
	    		Query query = session.createQuery("SELECT k FROM main.java.user.entity.Korisnik k WHERE k.username = :username AND k.isAdmin=: isAdmin");
				query.setParameter("username", username);
				query.setParameter("isAdmin", true);
				
				Korisnik login = (Korisnik) query.getResultList().get(0);
				
				t.commit();
	    		
	    		session.close();
	    		
	    		if(login != null) {
	    			return true;
	    		}
				
			} catch (Exception e) {
				t.rollback();
				System.out.println(e);
			}
	
	           return false;
	}

	public static List<Poll> getAllPolls(String username) {
		
		if(isAdministrator(username))
			return allPolls(username);
		else {
			System.out.println("not admin");
		}
			return null;
		}
    private static List<Poll> allPolls(String username) {
        	Session session =init();
    		
    		Transaction t = null;
    		
    		List<Poll> allPolls = new ArrayList<>();
    		
    		try {
    			
    			t = session.beginTransaction();
    			
    			Query query = session.createQuery("FROM main.java.user.entity.Poll");
    			
    		     List<Poll> res = query.getResultList();
    		    
    		    for (Poll poll : res) {
    				
    		    	allPolls.add(poll) ;		    	
    			}
    		    t.commit();
    		    session.close();
    		    
    		    return allPolls;
    			
    		} catch (Exception e) {
    			t.rollback();
    			System.out.println(e);
    		}
    		
    		return null;
    	
		

	}

		   public static void deletePoll(Integer id, Poll poll) {
			
			Session session =init();
			Transaction t = null;
			
			try {
				
			   t = session.beginTransaction();
				
				Korisnik admin = session.get(Korisnik.class, id);
				
				if(admin.getIsAdmin() == true) {
					
					
					
					Query query = session.createQuery("DELETE FROM main.java.user.entity.Poll p WHERE p.id =: id");
					query.setParameter("id", poll.getId());
				
					query.executeUpdate();
					
					t.commit();
					session.close();
					}else {
						System.out.println("The user is admin and it cant be deleted");
					}
					
				} catch (Exception e) {
					t.rollback();
					System.out.println(e);
				}
				
			}

		public static void updatePoll(String username, Poll poll) {
			Session session =init();
			
			Transaction t = null;
			
			try {
				
				t = session.beginTransaction();
				
				Query query = session.createQuery("SELECT k FROM main.java.user.entity.Korisnik k WHERE k.username=:username");
				
				query.setParameter("username", username);
				
				Date updatedDate = new Date(System.currentTimeMillis());
				
				Korisnik poll1 = (Korisnik) query.getResultList().get(0);
				
				if(poll1.getId()== poll.getKorisnik().getId()) {
					
				Poll updatedPoll = session.get(Poll.class, poll.getId());
				
				updatedPoll.setTopic(poll.getTopic());          
				updatedPoll.setCreatedOn(updatedDate);
				
				session.update(updatedPoll);
				
				t.commit();
				session.close();
				} else {
					System.out.println("abe ajde");
				}
				
				
			} catch (Exception e) {
				t.rollback();
				System.out.println(e);
			
			
		}
			
		}

		public static List<Poll> getAllPollsCreatedByKorisnik(String username, Poll poll) {
			
			if(isAdministrator(username))
				return getAllPollsByOneKorisnik(username, poll);
			else {
				System.out.println("Ti brat ne si admin");
			}
			return null;
		}
         private static List<Poll> getAllPollsByOneKorisnik(String username, Poll poll) {
			Session session =init();
	    	Transaction t = null;
	    	List<Poll>res = new ArrayList<>();
	    	try {
	    		
	    	t = session.beginTransaction();
	    	Query query = session.createQuery("FROM main.java.user.entity.Poll");
				List<Poll>result = query.getResultList();
			    
			    for (Poll poll1 : result) {
					if(poll1.getKorisnik().getId()== poll.getId())
						res.add(poll1);
				     }
			    t.commit();	
			    session.close();
			    return res;
			    
				} catch (Exception e) {
				t.rollback();
				System.out.println(e);
			}
			return null;
		}

		public static List<Poll> getAllPollsCreatedInTheLastDay(String username) {
			
			if(isAdministrator(username))
				return getAllPollsCreatedInTheLast24h(username);
			else {
				System.out.println("Ti brat ne si admin");
			}
			return null;
			
		}

		private static List<Poll> getAllPollsCreatedInTheLast24h(String username) {
			Session session =init();
	    	Transaction t = null;
	    	List<Poll> res = new ArrayList<>();
	    	try {
	    	
	    	t = session.beginTransaction();
	    	Query query = session.createQuery("FROM main.java.user.entity.Poll p WHERE p.createdOn >= current_date - 1");
				
	    	List<Poll>result = query.getResultList();
			    
			     t.commit();	
			    session.close();
			    return result;
			    
				} catch (Exception e) {
				t.rollback();
				System.out.println(e);
			}
			
			return null;
		}
		
public static List<Poll> getAllPollsVotedByUser(Integer id) {
	
	//   if(isAdministrator(username))
	//	   return getAllPollsVotedByOneUser(id);
	//   else {
	//	   System.out.println("batka ti ne si admin");
	//   }
			
	//		return null;
	//	}
		

	//	private static List<Poll> getAllPollsVotedByOneUser(Integer id ) {
			Session session =init();
	    	Transaction t = null;
	    	List<Poll> res = new ArrayList<>();
	    	try {
	    	
	    	t = session.beginTransaction();
	    	
	    	Query query = session.createQuery("FROM main.java.user.entity.Votes");
				
	    	List<Votes>result = query.getResultList();
			    for (Votes votes : result) {
			    	if(votes.getKorisnik().getId() == id) {
			    	
					res.add(votes.getChoices().getPoll());
			    	}
				}
	    	
			    t.commit();	
			    session.close();
			    return res;
			    
				} catch (Exception e) {
				t.rollback();
				System.out.println(e);
			}
			
			
	return null;
}

		public static void castVote(String username , Votes votes) {
			
			Session session =init();
	    	
	    	Transaction t = null;
	    	
	    	try {
	    			
	    		t = session.beginTransaction();
	    		Query query = session.createQuery("SELECT k FROM main.java.user.entity.Korisnik k WHERE k.username = :username");
				query.setParameter("username", username);
				Korisnik voted = (Korisnik) query.getResultList().get(0);
				   
				Choices choice = new Choices(votes.getChoices().getChoice(), votes.getChoices().getPoll());
				session.save(choice);
				
		    	Votes vote = new Votes(voted, choice);
		    	session.save(vote);
	    		
	    		t.commit();
	    		session.close();
				
			} catch (Exception e) {
				t.rollback();
				System.out.println(e);
			}
			
		}

		public static List<Votes> getVotesVotedByUser(Integer id) {
			
           Session session =init();
	    	
	    	Transaction t = null;
	    	
	    	List<Votes>votes = new ArrayList<>();
	    	try {
	    			
	    		t = session.beginTransaction();
	    		Query query = session.createQuery("FROM main.java.user.entity.Votes ");
				
	    		List<Votes> res = query.getResultList();
				   
				  for (Votes votes2 : res) {
					
					  if(votes2.getKorisnik().getId() == id) {
						  votes.add(votes2);
					  }
				}
	    		
	    		t.commit();
	    		session.close();
	    		return votes;
				
			} catch (Exception e) {
				t.rollback();
				System.out.println(e);
			}
			
			return null;
		}

		public static Integer countVotesByPoll(Integer id) {
			
            Session session =init();
	    	
	    	Transaction t = null;
	    	
	    	List<Votes>votes = new ArrayList<>();
	    	
	    	try {
	    			
	    		t = session.beginTransaction();
	    		Query query = session.createQuery("FROM main.java.user.entity.Votes");
	    	
	    		List<Votes> res = query.getResultList();
				   
				  for (Votes votes2 : res) {
					
					  if(votes2.getChoices().getPoll().getId() == id) 
						  
						 votes.add(votes2);
					  }
				
				t.commit();
	    		session.close();
	    		return votes.size();
				
			} catch (Exception e) {
				t.rollback();
				System.out.println(e);
			}
			
			return null;
		}

		public static Integer createPoll2(String username, PollChoices request) {
			
			Session session = init();
			
			Transaction t = null;
			
			try {
				
				t = session.beginTransaction();
				Korisnik korisnik = getKorisnikByUsername(username);
				Date date = new Date(System.currentTimeMillis());
				Poll poll = new Poll(request.getPoll().getTopic(), korisnik, date);
				
				session.save(poll);
				
				for (Choices choices : request.getChoices()) {
					choices.setPoll(poll);
					session.save(choices);
				}
				
				t.commit();
				session.close();
				return poll.getId();
				
			} catch (Exception e) {
				t.rollback();
				System.out.println(e);
				return null;
			}
			
		}

		private static Korisnik getKorisnikByUsername(String username) {
			
Session session = init();
			
			Transaction t = null;
			
			try {
				
				t = session.beginTransaction();
				
				Query query = session.createQuery("FROM main.java.user.entity.Korisnik k WHERE k.username =: username");
				query.setParameter("username", username);
				
				Korisnik korisnik = (Korisnik) query.getResultList().get(0);
				
				t.commit();
				session.close();
				
				return korisnik;
				
			} catch (Exception e) {
				t.rollback();
             System.out.println(e);	
            }
			return null;
		}

		public static List<PollChoiceRes> getPolls() {
			
			Session session = init();
			
			Transaction t = null;
			
			List<PollChoiceRes> responces = new ArrayList<>();
			
			try {
				
				t = session.beginTransaction();
				
				Query query = session.createQuery("FROM main.java.user.entity.Poll p");
				
				List<Poll> result = query.getResultList();
				
				for (Poll poll : result) {
					
					Query queryQuestions = session.createQuery("FROM main.java.user.entity.Choices c JOIN c.poll p WHERE p.id =:id ");
					queryQuestions.setParameter("id", poll.getId());
					
					List<Choices> choices = queryQuestions.getResultList();
					
					PollChoiceRes responce1 = new PollChoiceRes();
					
					responce1.setPoll(poll);
					responce1.setChoices(choices);
					responce1.setCreatedBy(poll.getKorisnik().getUsername());
					
					responces.add(responce1);
					
				}
				
				t.commit();
				session.close();
				return responces;
				
				
				
			} catch (Exception e) {
				t.rollback();
				System.out.println(e);
				
			}
			return null;
			
		}

		public static String castVotes(String username, Integer pollId, Integer choiceId) {
			
			Session session = init();
			Transaction t = null;
			
			try {
				
				t = session.beginTransaction();
						
				
				Korisnik korisnik = getKorisnikByUsername(username);
				
				Poll poll = session.get(Poll.class, pollId);
				
				Choices choice = session.get(Choices.class, choiceId);
				
				
				
				Votes votes = new Votes(korisnik, choice, poll);
				
				session.save(votes);
				
				t.commit();
				session.close();
				return "Korisnik " + korisnik.getUsername() + " voted for poll " + poll.getTopic();
			} catch (Exception e) {
				t.rollback();
				System.out.println(e);
				return "Korisnik was unable to vote";
			}
			
			
			
			
		}
		
	}

