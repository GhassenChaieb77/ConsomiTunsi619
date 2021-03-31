package tn.esprit.spring.Services;

import java.util.List;

import tn.esprit.spring.Entities.Role;
import tn.esprit.spring.Entities.User;

public interface UserService {

	
	public void saveUser(User user);
	
	public boolean isUserAlreadyPresent(User user);
	
	public void DeleteUser (long id);
	
	public void Update(User user);
	
	 public List<User> getAlluser();

	public User getUserById(long id);

	
	public User findUserByEmail(String email);

}
