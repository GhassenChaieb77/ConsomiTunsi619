package tn.esprit.spring.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface DeliveryAgentRepository extends CrudRepository<DeliveryAgent, Long> {

}