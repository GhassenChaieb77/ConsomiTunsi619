package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface LayerRepository extends CrudRepository<Layer, Long> {

	public Layer findById(long id);
	public Layer findByCategoryId(long id);

	
}
