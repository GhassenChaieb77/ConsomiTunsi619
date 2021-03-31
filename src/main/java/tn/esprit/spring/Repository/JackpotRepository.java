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
public interface JackpotRepository extends CrudRepository<Jackpot, Long>{

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO users_jackpots (id, id) VALUES (:user_Id, :id)", nativeQuery = true)
	void acceder( @Param("user_Id") Long userId, @Param("id") Long id);
	
	
	@Query("SELECT COUNT(*) FROM Jackpot")
	int NbJackpot();
	
	@Query(value = "Select SUM(amount) From Jackpot", nativeQuery= true)
	public float total();
	
	@Query(value = "Select * from Jackpot where amount > 20 ORDER BY amount DESC", nativeQuery= true)
	public List<Jackpot> sort();
}
