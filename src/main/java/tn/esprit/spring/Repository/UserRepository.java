package tn.esprit.spring.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entities.*;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	
	
	public User findByEmail(String email);
	public User findById(long id);
	public User getUserByEmailAndPassword(String login, String password);
	 public User findByResetToken(String resetToken);

}
