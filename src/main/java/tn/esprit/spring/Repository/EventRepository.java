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
public interface EventRepository extends CrudRepository<Event, Long> {

	@Query("SELECT name FROM Event")
	List<String> getallnames();
	
	@Query("Select ev from Event ev where ev.name=:name")
    public List<Event> EventByName(@Param("name") String name);
	
	@Query("Select ev from Event ev where ev.place=:place")
    public List<Event> EventByPlace(@Param("place") String place);
	
	

	//@Transactional
	//@Modifying
	//@Query("update Event e set e.name=:name , e.place=:place , e.participants=:participants where e.id=:id")
	//public Event updateEv( @Param("name") String name, @Param("place") String place, @Param("participants") int participants, @Param("id") Long id);
	
	
	
	@Query("SELECT COUNT(*) FROM Event")
	int Nbevent();
	

}
