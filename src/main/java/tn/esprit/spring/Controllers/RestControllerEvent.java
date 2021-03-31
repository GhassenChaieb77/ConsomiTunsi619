package tn.esprit.spring.Controllers;

import java.util.List;

import javax.xml.ws.RequestWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Entities.Donation;
import tn.esprit.spring.Entities.Event;
import tn.esprit.spring.Entities.Jackpot;
import tn.esprit.spring.Entities.Order;
import tn.esprit.spring.Entities.Publicity;
import tn.esprit.spring.Services.DonationServiceImp;
import tn.esprit.spring.Services.EventService;
import tn.esprit.spring.Services.EventServiceImp;
import tn.esprit.spring.Services.JackpotService;
import tn.esprit.spring.Services.JackpotServiceImp;
//import tn.esprit.spring.Services.OrderServiceImp;


@RestController 
@RequestMapping("/charity")
public class RestControllerEvent {

	@Autowired 
	EventServiceImp serviceEvt ; 
	
	@Autowired
	JackpotServiceImp serviceJac; 
	
	@Autowired
	DonationServiceImp serviceDon;
	
	
	
	
	@PostMapping("/addEvent")
	@ResponseBody
	public Event addEvent(@RequestBody Event ev)
	{
		return serviceEvt.ajouterEvent(ev);
		
	}
	
	@GetMapping(value = "/listeDesEvents")
    @ResponseBody
	public List<Event> getAllEvents()
	{
		
		return serviceEvt.getAllEvent() ;
	}
	
	@DeleteMapping("/deleteEvent/{evId}") 
	@ResponseBody 
	public void deleteEvent(@PathVariable("evId")Long evId) {
		serviceEvt.deleteE(evId);
	}

	@PutMapping(value = "/UpdateEvent") 
	@ResponseBody
	public Event updateEvent(@RequestBody Event ev) {
		
		return serviceEvt.updateEvent(ev); 
		
	} 
	
	
	
	@GetMapping(value = "/getAllEventByName/{name}")
    @ResponseBody
	public List<Event> getAllEventsByName(@PathVariable("name") String name) {
		
		return serviceEvt.getEventbyName(name); } 
	
	
	@GetMapping(value = "/getAllEventByPlace/{place}")
    @ResponseBody
	public List<Event> getAllEventsByPlace(@PathVariable("place") String place) {
		
		return serviceEvt.getEventbyPlace(place); } 
	

	@GetMapping(value = "/getEventById/{id}")
    @ResponseBody
	public Event getEventById(@PathVariable("id") Long id) {
		
		return serviceEvt.getEventById(id); 
	}
	
	
	@GetMapping("/getNombreEvent")
	public int getNombreEvent()
	{
		return this.serviceEvt.getNombreEvent(); 
	}
	
	
	@PutMapping(value = "/affectDonationaEvent/{evId}/{donId}") 
	 public Donation AffecterDonaEv(@PathVariable("evId") Long evId, @PathVariable("donId") Long donId)
  {
  	return this.serviceEvt.affecterEventADonation(evId, donId);
  	//System.out.println("affecté");
  	
  	
  }
	@GetMapping(value = "/listeDonOfEv/{evId}")
    @ResponseBody
	public List<Donation> listDonOfEv(@PathVariable("evId")Long evId){
		return serviceEvt.listerDonationByEV(evId); 
	}
	
	@GetMapping(value = "/listEventNoDon")
    @ResponseBody
	public List<Event> listEvNoDon(){
		return serviceEvt.listEvNoDon();  
	}
	
	@PutMapping(value = "/ajouterParticipant/{evId}") 
	public Event addParticipant(@PathVariable("evId")Long evId){
		return serviceEvt.ajouterParticipant(evId); 
		
	}
	
	@DeleteMapping("/LateEvent") 
	@ResponseBody 
	public void LateEvent() {
		serviceEvt.lateEvent(); 
	}
	
	@GetMapping(value = "/EventToday")
    @ResponseBody
	public List<Event> evtoday(){
		return serviceEvt.eventToday();  
	}
	
	
	@PutMapping(value = "/Donner/{evId}/{money}") 
	public Event ajouterDon(@PathVariable("evId")Long evId, @PathVariable("money")float money){
		return serviceEvt.Donner(evId, money) ; 
		
	}
	
	@PutMapping(value = "/affectDonationaEvent/{evId}/{pubId}") 
	 public Publicity AffecterPubaEv(@PathVariable("evId") Long evId, @PathVariable("pubId") Long pubId)
 {
 	return this.serviceEvt.affecterEventAPublicity(evId, pubId); 
 	//System.out.println("affecté");
 	
 	
 }
	
	@GetMapping(value = "/Start")
    @ResponseBody
	public List<Event> evtstart(){
		return serviceEvt.eventStarted(); 
	}
	
	@PostMapping("/EventStarted")
	
	public ResponseEntity<String> getevStart() {
        List<Event> list = evtstart(); 
        String result=""; 
        for(int i=0 ; i < list.size(); i++){
	    result = "{\"event\":\"" + list.get(i).getName() + "\"}";
	    }
	    
        return  ResponseEntity.ok().body(result);
        
        
	}
	@PostMapping("/donnez/{evId}/{money}")
	public ResponseEntity<String> donate(@PathVariable("evId")Long evId, @PathVariable("money")float money) {
        Event ev = ajouterDon(evId, money); 
        String result=""; 
	    result = "{\" nouveau don \":\"" + ev.getDon() + "\"}";
	    
	    
        return  ResponseEntity.ok().body(result);
	}
	//jackpot ------------------------------------------------------------------------------
	
	
	
	
	@PostMapping("/addJackpot")
	@ResponseBody
	public Jackpot addJackpot(@RequestBody Jackpot j)
	{
		return serviceJac.ajoutJackpot(j);
		
	}
	
	@GetMapping(value = "/listeDesJackpots")
    @ResponseBody
	public List<Jackpot> getAllJackpots()
	{
		
		return serviceJac.getAllJackpot() ;
	}
	
	@DeleteMapping("/deleteJackpot/{jacId}") 
	@ResponseBody 
	public void deleteJackpot(@PathVariable("jacId") Long jacId) {
		serviceJac.delete(jacId);
	}
	
	@PutMapping(value = "/UpdateJackpot") 
	@ResponseBody
	public Jackpot updateJackpot(@RequestBody Jackpot jac) {
		
		return serviceJac.update(jac) ; 
		
	} 
	
	@GetMapping("/getNombreJackpot")
	public int getNombreJackpot()
	{
		return this.serviceJac.getNombreJackpot(); 
	}
	
	@GetMapping("/getTotalAmount")
	public float getTotAmount()
	{
		return this.serviceJac.sumAmount();  
	}
	
	
	@PutMapping(value = "/affectJackpotaEvent/{potId}/{evId}") 
	 public void AffecterJackpotaEv(@PathVariable("potId") Long potId, @PathVariable("evId") Long evId)
   {
   	this.serviceJac.affecterJackpotAEvent(potId, evId);
   	System.out.println("affecté");
   	
   }
	
	@PutMapping(value = "/ajouterArgent/{jacId}/{amount}") 
	public Jackpot ajouterAmount(@PathVariable("jacId")Long jacId, @PathVariable("amount")float amount){
		return serviceJac.ajouterArgent(jacId, amount); 
		
	}
	
	@GetMapping("/superJackpot")
	public List<Jackpot> sortbyAmount(){
		return serviceJac.sortamount(); 
	}
	
	//Donation ----------------------------------------------------------------------------
	
	
	@PostMapping("/addDonation")
	@ResponseBody
	public Donation addDonation(@RequestBody Donation don)
	{
		return serviceDon.ajouterDonation(don); 
		
	}
	
	@GetMapping(value = "/listeDesDonations")
    @ResponseBody
	public List<Donation> getAllDonations()
	{
		
		return serviceDon.getAllDonation(); 
		
	}
	
	@DeleteMapping("/deleteDonation/{donId}") 
	@ResponseBody 
	public void deleteDonation(@PathVariable("donId")Long donId) {
		serviceDon.deleteDonation(donId);
	}
	
	@PutMapping(value = "/UpdateDonation") 
	@ResponseBody
	public Donation updateDonation(@RequestBody Donation don) {
		
		return serviceDon.updateDonation(don);  
		
	} 
	
	@GetMapping(value = "/getAllDonationByLibelle/{libelle}")
    @ResponseBody
	public List<Donation> getAllDonationByLibelle(@PathVariable("libelle") String libelle) {
		
		return serviceDon.getDonationByLibelle(libelle);  } 
	
	
	
	@GetMapping("/getNombreDonation")
	public int getNombreDonation()
	{
		return this.serviceDon.getNombreDonation(); 
	}




}

