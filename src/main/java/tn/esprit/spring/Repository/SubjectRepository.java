package tn.esprit.spring.Repository;


import java.util.List;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;


@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

	
	
    @Query("select s.title, s.content, s.date, s.likes, s.dislikes "
			+"from Subject s "
			+"order by date DESC ")
	public List<String> SubjectAlaUne();
    
    
    @Query("select DISTINCT s "
			+"from Subject s "
			+"where s.product.id=:id "
			+"order by rating DESC ")
     
	public List<Subject> topthree(@Param("id") long product); 
    
    
    @Query("select DISTINCT s "
			+"from Subject s "
			+"where s.product.id=:id ")
     
	public List<Subject> allsubbyprduct(@Param("id") long product); 
    
    
	
    @Query("select DISTINCT s "
			+"from Subject s "
			+"where s.product.id=:id "
			+"and s.title=:title ")
     
	public List<Subject> subBYTitle(@Param("id") long product,@Param("title") String title);
    
    
   
    
    
	
   /* @Query("Select s.product "
			+ "from Subject s "
			+ "where s.product=:product")
    public List<Subject> subjectproduct(@Param("product") Product product);*/
	
   
    
    /*@Modifying
    @Query("select count (s.comments) "
			+"from Subject s "
			+"where s.id=:id ")
	public long NBcomment(@Param("id") long idsubject);*/
}
