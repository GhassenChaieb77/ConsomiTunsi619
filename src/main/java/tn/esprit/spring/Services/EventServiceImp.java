package tn.esprit.spring.Services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Entities.Donation;
import tn.esprit.spring.Entities.Event;
import tn.esprit.spring.Entities.Jackpot;
import tn.esprit.spring.Entities.Publicity;
import tn.esprit.spring.Repository.DonationRepository;
import tn.esprit.spring.Repository.EventRepository;
import tn.esprit.spring.Repository.JackpotRepository;
import tn.esprit.spring.Repository.PublicityRepository;

@Service 
public class EventServiceImp implements EventService {

	@Autowired
	EventRepository es ; 
	@Autowired
	JackpotRepository jr ;
	
	@Autowired
	DonationRepository dr ;
	
	@Autowired
	PublicityRepository pr ; 
	
	@Override
	@Transactional
	public Event ajouterEvent(Event event) {
		return es.save(event); 
	}

	

	@Override
	@Transactional
	public Event updateEvent(Event e) {
		
		return es.save(e);
	}
	
	

	@Override
	@Transactional
	public void deleteE(Long evId){
		
		   es.deleteById(evId);
	       
		
	}

	@Override
	public List<Event> getAllEvent() {
		List<Event> listev = (List<Event>) es.findAll();
		
		return listev ; 
	}
     
	@Override
	public List<String> getAllName() {
		
		return es.getallnames(); 		
	}
	@Override
	public Event getEventById(Long id) {
		return es.findById(id).get();
		
	}
	
	@Override
	public List<Event> getEventbyName(String name){
		return es.EventByName(name); 
		
	}

	@Override
	public int getNombreEvent() {
		return es.Nbevent(); 
	}
	
	
	@Override
	public Donation affecterEventADonation(Long evId, Long donId) {
		
		Donation d = dr.findById(donId).get();
		Event j = es.findById(evId).get();
		d.setEvent(j);
		dr.save(d);
		return d ; 
		

}



	@Override
	public List<Donation> listerDonationByEV(Long evId) {
		Event j = es.findById(evId).get();
		List<Donation> listDon = j.getDonations(); 
		return listDon; 
	}



	@Override
	public List<Event> listEvNoDon() {
		List<Event> ici = new ArrayList();
		List<Event> listev = (List<Event>) es.findAll(); 
        for (int i=0 ; i <listev.size() ; i++){
                     Event e = listev.get(i); 
                     List<Donation> listDon = e.getDonations(); 
                     if(listDon.isEmpty()){
                                
                                ici.add(e); 
                                  }
}                                return ici; 
	}



	@Override
	public Event ajouterParticipant(Long evId) {
		Event e = es.findById(evId).get();
		e.ajouterParti();
		es.save(e); 
		return e ; 
	}



	@Override
	public void lateEvent() {
		java.util.Date date = new java.util.Date();  
		//LocalDate d = java.time.LocalDate.now();
		List<Event> evlist = (List<Event>) es.findAll(); 
		for (Event i : evlist){
			if (i.getDate().before(date)) {
				es.deleteById(i.getId());
			}
		}
		
	}



	@Override
	public List<Event> eventToday() {
		List<Event> list = new ArrayList();
		
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd"); 
		Date d = new Date(System.currentTimeMillis());
		//LocalDate date = LocalDate.now(); 
		List<Event> listev = (List<Event>) es.findAll(); 
		System.out.println(d);
		System.out.println("boucle :");
        for (int i=0 ; i <listev.size() ; i++){
        	System.out.println(f.format(listev.get(i).getDate()).equals(f.format(d)));
        	//System.out.println("c bon2 "+ listev.get(i).getDate().equals(Calendar.getInstance().getTime()));
        	//System.out.println("c bon2 "+ d.equals(listev.get(i).getDate()));
        	System.out.println("c bon2 "+ listev.get(i).getDate());
        	Event e = listev.get(i); 
        	if(f.format(e.getDate()).equals(f.format(d))){
        		System.out.println("c bon3 ");
        		list.add(e); 
        		System.out.println("c bon4 ");
        	}
        }     return list; 
		
		
	}



	@Override
	public Event Donner(Long evId, float money) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd"); 
		Date d = new Date(System.currentTimeMillis());
		Event e = es.findById(evId).get();
		if(f.format(e.getDate()).equals(f.format(d))){
		e.ajouterDon(money);
		es.save(e); 
		
		}
		return e ; 
	}
		
	
	@Override
	public Publicity affecterEventAPublicity(Long evId, Long pubId) {
		Publicity p = pr.findById(pubId).get();
		Event e = es.findById(evId).get();
		if(e.getParticipants()<10){
		p.setEvent(e);
		pr.save(p); 
		}
		return p ; 
		
	}



	@Override
	public List<Event> getEventbyPlace(String place) {
		return es.EventByPlace(place); 
	}



	@SuppressWarnings("deprecation")
	@Override
	
	public List<Event> eventStarted() {
		List<Event> list = new ArrayList();
		List<String> names = new ArrayList();
		//String s=""; 
		//DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       //LocalDateTime now = LocalDateTime.now();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String formatted = formatter.format(System.currentTimeMillis());
		System.out.println(formatted + " icii  " + LocalDateTime.now().getHour());
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date d = new Date(System.currentTimeMillis());
		//int x = d.getHours(); 
		
		//System.out.println(LocalDateTime.now().getHour()); 
		
		List<Event> listev = (List<Event>) es.findAll(); 
		System.out.println("c bon00");
        for (int i=0 ; i <listev.size() ; i++){
        	Event e = listev.get(i);
        	System.out.println("c bon11");
        	//System.out.println(f);
        	//if(e.getDate().getHours()== x){
        	if((e.getDate().getHours()) == (LocalDateTime.now().getHour())){
        		//String time = new SimpleDateFormat("HH:mm").format(d); 
        		list.add(e);
        		System.out.println("c bon22");
        		//System.out.println(e.getName());
        		
        	}
        	}	
             
        return list; 
        
        
	}
	
	
	
}
		
	
	
	
	
	
	
	
	

