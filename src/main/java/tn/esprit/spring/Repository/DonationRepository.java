package tn.esprit.spring.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface DonationRepository extends CrudRepository<Donation, Long> {
	
	//@Transactional
	//@Modifying
	//@Query("update Donation d set d.event.id=:evId where c.id=:donId")
	//void affecterDonAEv(@Param("id") Long dontId, @Param("id") Long evId);
	
	@Query("SELECT libelle FROM Donation")
	List<String> getallnames();
	
	@Query("SELECT COUNT(*) FROM Donation")
	int Nbdon();
	
	@Query("SELECT name FROM Product ")
	List<String> collectprod();
	
	@Query("Select don from Donation don where don.libelle=:libelle")
    public List<Donation> donationByName(@Param("libelle") String libelle);
	
	
}
