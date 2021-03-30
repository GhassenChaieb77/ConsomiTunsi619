package tn.esprit.spring.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.Product;
import tn.esprit.spring.Entities.Reduction;


@Repository
public interface ReductionRepository extends CrudRepository<Reduction, Long>{
	
	 @Query("Select "
				+ "DISTINCT redu from Reduction redu "
				+ "where redu.product.id=:id ")
	    public List<Reduction> getAllReductionByProductId(@Param("id") long id);
	 
	 @Query("Select "
				+ "DISTINCT redu from Reduction redu "
				+ "where redu.product.id=:id ")
	    public Reduction getReductionByProductId(@Param("id") long id);
	 
	 @Query("Select "
			 + "DISTINCT r from Reduction r ")
	    public List<Reduction> getReductionByDate();
	
	 @Query("Select "
			 + "DISTINCT r from Reduction r ")
	    public Reduction getReduction();
	 
	 @Query("Select "
				+ "DISTINCT redu from Reduction redu "
				+ "where redu.product.id=:id ")
	    public List<Reduction> getReductionsByProductId(@Param("id") long id);

}
