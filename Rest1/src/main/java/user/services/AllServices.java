package main.java.user.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import main.java.user.entity.Address;
import main.java.user.entity.Korisnik;
import main.java.user.entity.Profile;
import main.java.user.entity.SignInRequest;
import main.java.user.entity.UserResponse1;
import main.java.user.entity.UsernameAddress;

public class AllServices {
	
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
	
	//KREIRANJE NA KORISNICI

	public static void signUp(Korisnik korisnik) {
		
		Session session =init();
		
		Transaction t = null;
		
		try {
			
			
			t = session.beginTransaction();
			session.save(korisnik);
			t.commit();
			session.close();
			
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
	}

	//UPDATE NA KORISNICI
		
	public static void updateKorisnik(Integer id, Korisnik korisnik) {
		
		Session session =init();
		
		Transaction t = null;
		
		try {
			
		
			t = session.beginTransaction();
			
			Korisnik newData = session.get(Korisnik.class, id);
			
			newData.setPassword(korisnik.getPassword());
			newData.setEmail(korisnik.getEmail());
			
			session.update(newData);
			t.commit();
			session.close();
			
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
	}

//LOGIN

	public static void signIn(SignInRequest request) {
		
		Session session =init();
		
		Korisnik korisnik = signInKorisnik(request);
		
		if (korisnik != null) {
			Transaction t = null;
			
			try {
				
			
				t = session.beginTransaction();
				
				Korisnik login = session.get(Korisnik.class, korisnik.getId());
				login.setSignedIn(true);
				
				session.update(login);
				t.commit();
				session.close();
				
			} catch (Exception e) {
				t.rollback();
				System.out.println(e);
			}
		}else {
			System.out.println("Invalid username or password");
		}
		
	}

//LOGIN

	private static Korisnik signInKorisnik(SignInRequest request) {
		
		Session session =init();
		
		Transaction t = null;
		
		try {
			
			
			t = session.beginTransaction();
			
			Query query = session.createQuery("SELECT k FROM main.java.user.entity.Korisnik k WHERE k.username = :username AND k.password = :password");
			query.setParameter("username", request.getUsername());
			query.setParameter("password", request.getPassword());
			
			Korisnik login = (Korisnik) query.getResultList().get(0);
			
			t.commit();
			session.close();
			return login;
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
		return null;
	}
	
	//SIGNOUT

	public static void signOut(String username) {
		
		Session session =init();
		Transaction t = null;
		
		try {
		
			
			t = session.beginTransaction();
			
			Query query = session.createQuery("FROM main.java.user.entity.Korisnik k WHERE k.username=:username");
			
			query.setParameter("username", username);
			
			Korisnik logout = (Korisnik) query.getResultList().get(0);
			logout.setSignedIn(false);
			t.commit();
			session.close();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
	}

	//KREIRANJE NA ADRESI

    public static void createKorisnikAddress(Address address) {
		
    	Session session =init();
    	
    	Transaction t = null;
    	
    	try {
    		
    		t = session.beginTransaction();
    		session.save(address);
    		
    		t.commit();
    		session.close();
    		
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
	}

   //UPDATE NA ADRESI

	public static void updateKorisnikAddress(Integer id, Address address) {
		
		Session session =init();
		Transaction t = null;
		
		try {
			
			
			
			t = session.beginTransaction();
			
			Address newAddress = session.get(Address.class, id);
			newAddress.setStreet(address.getStreet());
			newAddress.setCity(address.getCity());
			newAddress.setZipCode(address.getZipCode());
			
			session.update(newAddress);
			
			t.commit();
			session.close();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
		
	}
	
	
	
    

    //UPDATE NA PROFILI

	public static void updateKorisnikProfile(String username, Profile profile) {
		
		Session session =init();
		
		Transaction t = null;
		
		try {
			
			t = session.beginTransaction();
			
			
			Query query = session.createQuery("FROM main.java.user.entity.Korisnik k WHERE k.username=:username");
			
			query.setParameter("username", username);
			
			Korisnik korisnik = (Korisnik) query.getResultList().get(0);
			
			if(korisnik.getId()== profile.getKorisnik().getId()) {
				
			
			
			Profile updatedProfile = session.get(Profile.class, profile.getId());
			updatedProfile.setLastName(profile.getLastName());
			updatedProfile.setInterests(profile.getInterests());
			
			Address updateAddress = session.get(Address.class, profile.getAddress().getId() );
			
			updateAddress.setCity(profile.getAddress().getCity());
			updateAddress.setZipCode(profile.getAddress().getZipCode());
			
			session.update(updateAddress);
			session.update(updatedProfile);
			}
			t.commit();
			session.close();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
	}
	
	//VERIFIKUVANJE NA KORISNICI
	
    public static void verifyKorisnik(Integer id,Korisnik korisnik) {
		
    	Session session =init();
    	Transaction t = null;
    	
    	try {
    		
    		t = session.beginTransaction();
    		
    		Korisnik admin = session.get(Korisnik.class, id);
    		
    		if(admin.getIsAdmin() == true) {
    		
    		
    			Query query = session.createQuery("UPDATE main.java.user.entity.Korisnik k set k.verified=:verified WHERE k.korisnikId=:korisnikId");	
    			query.setParameter("verified", true);
    			query.setParameter("korisnikId", korisnik.getId());
    		   query.executeUpdate();
    		t.commit();
    		
    		session.close();
			
    		}else {
    			System.out.println("The korisnik isn't admin");
    		}
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
		
	}
    
    //BRISHENJE NA KORISNICI 
    
	public static void deleteKorisnik(Integer id ,Korisnik korisnik) {
		
		Session session =init();
		Transaction t = null;
		
		try {
			
		   t = session.beginTransaction();
			
			Korisnik admin = session.get(Korisnik.class, id);
			
			if(admin.getIsAdmin() == true) {
				
				
				
				Query query = session.createQuery("DELETE FROM main.java.user.entity.Korisnik k WHERE k.id =: id");
				query.setParameter("id", korisnik.getId());
			
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
	
	//SITE KORISNICI
	
    public static List<Korisnik> getAllKorisnik(Integer id, Korisnik korisnik) {
		
    	Session session = init();
		List<Korisnik>allKor = new ArrayList<>();
		Transaction t = null;
		
		try {
			
			t = session.beginTransaction();
			Korisnik admin = session.get(Korisnik.class, id);
			if(admin.getIsAdmin() == true) {
			List<Korisnik> all = session.createQuery("SELECT k FROM main.java.user.entity.Korisnik k").list();
			for (Korisnik korisnik2 : all) {
				
				allKor.add(korisnik2);
			}
			
			System.out.println(allKor);
			
			t.commit();
			session.close();
			}else {
				System.out.println("Ne me biva");
			}
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		return allKor;
		}
    
    // NEVERIFIKUVANI KORISNICI

	public static List<Korisnik> getAllUnverifiedKorisniks(Integer id, Korisnik korisnik) {
		Session session = init();
		List<Korisnik>allUnverified = new ArrayList<>();
		Transaction t = null;
		
		try {
			
			t = session.beginTransaction();
			Korisnik admin = session.get(Korisnik.class, id);
			if(admin.getIsAdmin() == true) {
				
			List<Korisnik> all = session.createQuery("SELECT k FROM main.java.user.entity.Korisnik k WHERE k.verified = false").list();
			for (Korisnik korisnik2 : all) {
				
				allUnverified.add(korisnik2);
			}
			
			System.out.println(allUnverified);
			
			t.commit();
			session.close();
			}else {
				System.out.println("Ne me biva");
			}
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		return allUnverified;
		
	}

// VERIFIKUVANI KORISNICI

	public static List<Korisnik> getAllVerifiedKorisniks(Integer id, Korisnik korisnik) {
		
		Session session = init();
		List<Korisnik>allVerified = new ArrayList<>();
		Transaction t = null;
		
		try {
			
			t = session.beginTransaction();
			Korisnik admin = session.get(Korisnik.class, id);
			if(admin.getIsAdmin() == true) {
			List<Korisnik> all = session.createQuery("SELECT k FROM main.java.user.entity.Korisnik k WHERE k.verified = true").list();
			for (Korisnik korisnik2 : all) {
				
				allVerified.add(korisnik2);
			}
			
			System.out.println(allVerified);
			
			t.commit();
			session.close();
			}else {
				System.out.println("Ne me biva");
			}
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		return allVerified;

	
	
	}

	public static void createKorisnikProfile(String username, Profile profile) {
    Session session =init();
    	
    	Transaction t = null;
    	
    	try {
    		
    	
    		
    		t = session.beginTransaction();
    		Query query = session.createQuery("SELECT k FROM main.java.user.entity.Korisnik k WHERE k.username = :username");
			query.setParameter("username", username);
			
			
			Korisnik login = (Korisnik) query.getResultList().get(0);
			
			Address address = new Address( profile.getAddress().getStreet(), profile.getAddress().getCity(), profile.getAddress().getZipCode());
    		 session.save(address);
			Profile profile1 = new Profile( profile.getFirstName(), profile.getLastName(), profile.getAge(), profile.getInterests(),  address , login);
    		
    		session.save(profile1);
    		
    		t.commit();
    		
    		session.close();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
	}

	public static List<Korisnik> getUnverified(String username) {
		
		if(isAdministrator(username)) {
			
			return getUnverifiedUsers();
			
		}else {
			System.out.println("user is not administrator");
		}
		
		return null;
	}

	private static List<Korisnik> getUnverifiedUsers() {
		
		Session session =init();
    	
    	Transaction t = null;
    	
    	try {
    		
    	t = session.beginTransaction();
    	
    		Query query = session.createQuery("SELECT k FROM main.java.user.entity.Korisnik k WHERE k.verified=:verified");
		
			query.setParameter("verified", false);
			
		    List<Korisnik>result = query.getResultList();
		    
		    t.commit();	
		    session.close();
		    return result;
			
			
			
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

	public static List<UserResponse1> getList(String username) {
		
		if(isAdministrator(username))
			return getUnverifiedUsernames();
		
		return null;
	}

	private static List<UserResponse1> getUnverifiedUsernames() {
        Session session =init();
    	
    	Transaction t = null;
    	
    	List<UserResponse1> result = new ArrayList<>();
    	try {
    		
    	t = session.beginTransaction();
    	
    		Query query = session.createQuery("SELECT k FROM main.java.user.entity.Korisnik k WHERE k.verified=:verified");
		
			query.setParameter("verified", false);
			
		    List<Korisnik>res = query.getResultList();
		    
		    for (Korisnik korisnik : res) {
				
		    	UserResponse1 response1 = new UserResponse1();
		    	response1.setId(korisnik.getId());
		    	response1.setUsername(korisnik.getUsername());
		    	
		    	
		    	result.add(response1);
		    	
			}
		    
		    
		    
		    t.commit();	
		    session.close();
		    
			return result;
			
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
		return null;
	}

	public static List<Profile> listProfiles(String username) {
		
		if(isAdministrator(username))
			return getlistProfiles(username);
		else {
			System.out.println("not admin");
		}
		return null;
	}

	private static List<Profile> getlistProfiles(String username) {
        Session session =init();
    	
    	Transaction t = null;
    	
    	
    	try {
    		
    	t = session.beginTransaction();
    	
    		Query query = session.createQuery("SELECT k FROM main.java.user.entity.Korisnik k WHERE k.verified=false");
		
    		 List<Korisnik>res = query.getResultList();
    		 
    		 for (Korisnik korisnik : res) {
				korisnik.setVerified(true);
			
    		 
		    
		    t.commit();	
		    session.close();
    		 }
		   // return res;
		    
    	} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
		return null;
	}

	public static List<Korisnik> verifyAllUnverified(String username) {
		
		if(isAdministrator(username))
			return allAreVerified(username);
		else {
			System.out.println("not admin");
		}
		return null;
	}

	private static List<Korisnik> allAreVerified(String username) {
		
		    Session session =init();
	    	
	    	Transaction t = null;
	    	
	    	try {
	    		
	    	t = session.beginTransaction();
	    	
	    	Query query = session.createQuery("SELECT k FROM main.java.user.entity.Korisnik k WHERE k.verified=false");
			
			List<Korisnik>res = query.getResultList();
				 
	    		  for (Korisnik korisnik : res) {
					korisnik.setVerified(true);
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

	public static List<Korisnik> signOutAll(String username) {
		
		if(isAdministrator(username))
			return allAreSignedOut(username);
		else {
			System.out.println("not admin");
		}
		return null;
	}

	private static List<Korisnik> allAreSignedOut(String username) {
		
		    Session session =init();
	    	
	    	Transaction t = null;
	    	
	    	try {
	    		
	    	t = session.beginTransaction();
	    	
	    	Query query = session.createQuery("SELECT k FROM main.java.user.entity.Korisnik k WHERE k.signedIn = true");
			
			List<Korisnik>res = query.getResultList();
				 
	    		  for (Korisnik korisnik : res) {
					korisnik.setSignedIn(false);
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

	public static List<UsernameAddress> userAddress(String username) {
		
		if(isAdministrator(username))
		return allUsernameAddresses(username);
		else {
			System.out.println("Odi setaj");
		}
		return null;
	}

	private static List<UsernameAddress> allUsernameAddresses(String username) {
		
		Session session = init();
		
		Transaction t = null;
		
		List<UsernameAddress> result = new ArrayList<>();
		
		try {
			
			t= session.beginTransaction();
			
			Query query = session.createQuery("FROM main.java.user.entity.Profile");
			
			List<Profile> res = query.getResultList();
			
			for (Profile profile2 : res) {
				
			
			UsernameAddress userAddress = new UsernameAddress();
			userAddress.setAddress(profile2.getAddress().getCity());
			userAddress.setUsername(profile2.getKorisnik().getUsername());
			
			result.add(userAddress);
			}
			
			System.out.println(result);
			t.commit();
			session.close();
			
			return result;
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		return null;
	}

	
	
	
	
}
	


