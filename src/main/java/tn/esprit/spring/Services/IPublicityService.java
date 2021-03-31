package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Publicity;
import tn.esprit.spring.Entities.User;

public interface IPublicityService {

	public Publicity addPublicity(Publicity p);
	public List<Publicity> dispalyPublicities();
	public void deletePublicity(long id);
	public Publicity updatePublicity(Publicity p);
	
	
	public Publicity getPublicityById(long id);
	public void deletePub();
}
