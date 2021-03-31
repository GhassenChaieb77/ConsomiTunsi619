package tn.esprit.spring.Services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

import tn.esprit.spring.Entities.Donation;
import tn.esprit.spring.Entities.Event;
import tn.esprit.spring.Entities.Jackpot;
import tn.esprit.spring.Entities.Publicity;

public interface EventService {
	
	public Event ajouterEvent(Event event);
	
	//public void affecterJackpotAEvent(Long potId , Long evId);
	
	public Event updateEvent(Event e); 
	
	public Donation affecterEventADonation(Long evId, Long donId); 
	
	//public Event updateAnouv(String name, String place, int participants , Long Id); 
	
	public void deleteE(Long id); 
	
	public List<Event> getAllEvent();
	
	public List<Event> getEventbyName(String name);
	
	public List<Event> getEventbyPlace(String place);

	public List<String> getAllName();

	public Event getEventById(Long id); 
	
	public int getNombreEvent(); 
	
	public List<Donation> listerDonationByEV (Long evId);
	
	public List<Event> listEvNoDon();
	
	public Event ajouterParticipant(Long evId); 
	
	public void lateEvent(); 
	
	public List<Event> eventToday (); 
	
	public Event Donner(Long evId , float money);
	
	public Publicity affecterEventAPublicity(Long evId, Long pubId); 
	
	public List<Event> eventStarted(); 
	
	
}
