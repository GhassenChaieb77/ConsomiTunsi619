package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entities.Jackpot;

public interface JackpotService {

	public Jackpot ajoutJackpot(Jackpot jackpot); 
	public Jackpot update(Jackpot j) ; 
	public void delete(Long donId); 
	public void accederJackpot(Long id , Long userId);
	public List<Jackpot> getAllJackpot(); 
	public int getNombreJackpot(); 
	public float sumAmount();  
	public void affecterJackpotAEvent(Long potId, Long evId); 
	public Jackpot ajouterArgent(Long jacId, float amount); 
	public List<Jackpot> sortamount(); 
}
