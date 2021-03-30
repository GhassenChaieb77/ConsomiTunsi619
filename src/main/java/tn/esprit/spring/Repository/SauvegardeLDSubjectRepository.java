package tn.esprit.spring.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.Entities.SauvegardeLDSubject;

public interface SauvegardeLDSubjectRepository extends CrudRepository<SauvegardeLDSubject, Long> {
    @Query("select s.userLDsubject "
			+"from SauvegardeLDSubject s ")
	public List<Long> userlikes();
    
    @Query("select s.usersubject "
			+"from SauvegardeLDSubject s ")
	public List<Long> subjectlikes();
	
    @Modifying
    @Query("delete "
			+"from SauvegardeLDSubject s "
			+"where s.userLDsubject=:userLDsubject "
			+"and s.usersubject=:usersubject ")
	public void DeleteSauv(@Param("userLDsubject") long LDsubject,@Param("usersubject") long usersubject );
    
    @Modifying
    @Query("delete "
			+"from SauvegardeLDSubject s "
			+"where s.usersubject=:usersubject ")
	public void DeleteSubjectsauv(@Param("usersubject") long usersubject );
}
