package tn.esprit.spring.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface DeliveryAgentRepository extends CrudRepository<DeliveryAgent, Long> {
	
	@Transactional
	@Modifying
	@Query("update DeliveryAgent da set da.isavailable = :notavailable where da.id = :id")
	public int updateDeliveryAgentAvailabilityById(@Param("notavailable") boolean notavailable , @Param("id") Long
			id);
	
	@Transactional
	@Modifying
	@Query("update Order o set o.deliveryagent.id = :deliveryagentid where o.id = :orderid")
	public int affectDeliveryAgenttoorder(@Param("deliveryagentid") Long deliveryagentid , @Param("orderid") Long
			orderid);
	
	@Transactional
	@Modifying
	@Query("update DeliveryAgent da set da.balance = :bonus where da.id = :daid")
	public int updatebonustodeliveryagent(@Param("daid") Long id , @Param("bonus") double d);
	
	@Transactional
	@Modifying
	@Query("update DeliveryAgent da set da.traveledpath = :traveledpath where da.id = :daid")
	public int updatedistanceofdeliveryagent(@Param("daid") Long id , @Param("traveledpath") double traveledpath);
}
