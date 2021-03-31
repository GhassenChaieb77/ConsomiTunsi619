package tn.esprit.spring.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface ComplaintRepository extends CrudRepository<Complaint, Long>  {
	
	@Transactional
	@Modifying
	@Query("update Complaint c set c.response = :response where c.id = :cid")
	public int respondtocomplaint(@Param("cid") Long id , @Param("response") String response);
	
	@Transactional
	@Modifying
	@Query("update Complaint c set c.order.id = :orderid where c.id = :cid")
	public int affectcomplainttoorder(@Param("cid") Long id , @Param("orderid") Long orderid);
	
	public Complaint findById(long id);
}
