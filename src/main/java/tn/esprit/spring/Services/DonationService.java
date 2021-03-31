package tn.esprit.spring.Services;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Entities.Donation;
import tn.esprit.spring.Entities.Event;
import tn.esprit.spring.Entities.Product;

public interface DonationService {
    
	
	
	//public Long faireDonation(Donation donation, Product p);
	
	public Donation ajouterDonation(Donation donation) ; 
	
	public void deleteDonation (Long donId);
	
	public List<Donation> getAllDonation() ; 
	
	public void affecterDonationAEvent(Long id , Long idev );

	//public Donation getCurrentDonation(HttpSession session); 

	public List<Donation> getDonationByLibelle(String libelle);

	public int getNombreDonation();
    
	public List<String> getAllName(); 
	//public void getDonatedProductNames();
	
	public Donation updateDonation(Donation d); 
	
}
