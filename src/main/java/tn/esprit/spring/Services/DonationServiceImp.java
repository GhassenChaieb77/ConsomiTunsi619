package tn.esprit.spring.Services;

import java.util.ArrayList;
import java.util.List;
//import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Entities.Donation;
import tn.esprit.spring.Entities.Event;
//import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Repository.DonationRepository;
import tn.esprit.spring.Repository.EventRepository;

@Service
public class DonationServiceImp implements DonationService {

	@Autowired 
	DonationRepository dr ; 
	
	//ProductRepository pr; 
	@Autowired
	EventRepository es ; 
	
	//@Override
	//public Long faireDonation(Donation donation, Product p) {
		
		//List<String> prodDon = new ArrayList<>();
		//prodDon = dr.collectprod();  
		//int a = prodDon.size();
		
		
		//return dr.save(donation).getId();
		
		
//	}
	
	@Override
	@Transactional
	public Donation ajouterDonation(Donation donation) {
		return dr.save(donation); 
	}
	@Override
	public void deleteDonation(Long donId) {
		
		 dr.deleteById(donId); 
		 
	}

	@Override
	public void affecterDonationAEvent(Long id, Long idev) {
		
		Donation d = dr.findById(id).get();
		Event e = es.findById(idev).get();
		d.setEvent(e);
		es.save(e);
			
	}

	@Override
	public List<Donation> getDonationByLibelle(String libelle) {
		
		return dr.donationByName(libelle);
	
	}

	@Override
	public List<Donation> getAllDonation() {
		List<Donation> listev = (List<Donation>) dr.findAll();
		
		return listev ; 
	}
	
	@Override
	public int getNombreDonation() {
		return dr.Nbdon();
	}

	@Override
	@Transactional
	public Donation updateDonation(Donation d) {
		
		return dr.save(d);
	}
	
	@Override
	public List<String> getAllName() {
		
		return dr.getallnames(); 		
	}
	
}
