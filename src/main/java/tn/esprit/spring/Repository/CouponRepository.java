package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, Long>{
	
	
	
	@Query("Select "
			+ "DISTINCT code from Coupon c "
			+ "where c.percentage=:per ")
 	public String getCodeCouponByPercentage(@Param("per") float per);
	
	 
	 	
}
