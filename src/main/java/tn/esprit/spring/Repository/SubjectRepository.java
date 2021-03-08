package tn.esprit.spring.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

	
	/*@Query("Select s.likes "
			+ "from Subject s "
		    + "join  s.Comment c "
		    + "join  c.User u "
		    + "where u.id=id ")
	
    public int Getlikes();*/

	@Query("select "
			+"DISTINCT s from Subject s "
			+"order by date ASC ")
	public List<Subject> SubjectAlaUne();
	
}
