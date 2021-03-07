package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>  {
	@Query("select "
			+"DISTINCT c from Comment c "
			+"order by date DESC ")
	public List<Comment> RecentComment();
}
