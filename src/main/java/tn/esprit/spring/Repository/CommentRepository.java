package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>  {
	@Query("select c.comment, c.date "
			+"from Comment c "
			+"where c.subject.id=:id "
			+"order by date DESC ")
	public List<String> RecentComment(@Param("id") long subjectId);
	
	@Query("select c.comment, c.date, c.likesComment, c.dislikesComment "
			+"from Comment c "
			+"where c.subject.id=:id "
			+"order by likesComment DESC ")
	public List<String> pertComment(@Param("id") long subjectId);
	
	
	
}
