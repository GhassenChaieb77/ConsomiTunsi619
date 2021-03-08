package tn.esprit.spring.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.Rating;
import tn.esprit.spring.Entities.Subject;
import tn.esprit.spring.Entities.User;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long> {
	  
	List<Rating> findByRating( Long rating);

	 List<Rating> findBySubject( Subject subject);

	 List<Rating> findByUser(User user);  
	
		
}
	
