package tn.esprit.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Entities.Donation;
import tn.esprit.spring.Entities.Event;
//import tn.esprit.spring.Entities.Event;
import tn.esprit.spring.Entities.Jackpot;
import tn.esprit.spring.Repository.EventRepository;
import tn.esprit.spring.Repository.JackpotRepository;

@Service 
public class JackpotServiceImp implements JackpotService  {

	@Autowired
	JackpotRepository jr ; 
	
	@Autowired
	EventRepository es ; 
	
	@Override
	public Jackpot ajoutJackpot(Jackpot jackpot) {
		return jr.save(jackpot);
	}
	

	@Override
	public Jackpot update(Jackpot j) {
		//return jr.save(jr.findById(id).get());
		
		return jr.save(j);
	}

	@Override
	public void delete(Long jId) {
		jr.deleteById(jId);
	}

	@Override
	public void accederJackpot(Long id, Long userId) {
		jr.acceder(id, userId);
		
	}
	
	@Override
	public void affecterJackpotAEvent(Long potId, Long evId) {
		
		Event d = es.findById(evId).get();
		Jackpot j = jr.findById(potId).get();
		d.setJackpot(j);
		es.save(d);
		
		
	}

	@Override
	public List<Jackpot> getAllJackpot() {
		List<Jackpot> listev = (List<Jackpot>) jr.findAll();
		
		return listev ; 
	}

	@Override
	public int getNombreJackpot() {
		return jr.NbJackpot(); 
	}

	@Override
	public float sumAmount() {
		return jr.total(); 
	}


	@Override
	public Jackpot ajouterArgent(Long jacId, float amount) {
		Jackpot j = jr.findById(jacId).get();
		j.ajouterAmount(amount);
		jr.save(j); 
		return j ; 
		}


	@Override
	public List<Jackpot> sortamount() {
		List<Jackpot> listesuper = jr.sort();
		return listesuper; 
	} 
		
	



}
