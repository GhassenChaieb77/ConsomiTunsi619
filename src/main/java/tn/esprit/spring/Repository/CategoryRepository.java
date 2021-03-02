package tn.esprit.spring.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	@Query("Select "
			+ "DISTINCT cat from Category cat "
			+ "where cat.name=:name")
	public String getCategotyByNameJPQL(@Param("name") String name);
	
	
}
