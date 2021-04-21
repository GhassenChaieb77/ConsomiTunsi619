package tn.esprit.configuration;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import net.minidev.json.annotate.JsonIgnore;
import tn.esprit.spring.Entities.User;


public class MyUserDetails implements UserDetails {
	

	private User user;
	
	private Long id;


	private String email;

	@JsonIgnore
	private String password;
	

	public MyUserDetails(User user) {
        this.user = user;
    }
 
	
	private GrantedAuthority authorities;

	public MyUserDetails(Long id, String email, String password,
			GrantedAuthority authorities) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static MyUserDetails build(User user) {
		GrantedAuthority authorities = new SimpleGrantedAuthority(user.getRole().getAuthority());

				

		return new MyUserDetails(user.getId(), user.getEmail(), user.getPassword(), authorities);
	}

	



    public  GrantedAuthority getAuthoritie() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getAuthority());
        return authority;
    }
 
    
    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
    public String getPassword() {
        return user.getPassword();
    }
 
    @Override
    public String getUsername() {
        return user.getEmail();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}