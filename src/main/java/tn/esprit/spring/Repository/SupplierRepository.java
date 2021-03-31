package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.Supplier;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long>  {
	
	
	Supplier findById(long id);

}

