package tn.esprit.spring.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	
	
	public User findByEmail(String email);
	public User findById(long id);
	public User getUserByEmailAndPassword(String login, String password);
	 public User findByResetToken(String resetToken);
	 
	 @Transactional
		@Modifying
		@Query("update User u set u.balance = :balance where u.id = :uid")
		public int updateuserbalance(@Param("uid") Long id , @Param("balance") double balance);


}
