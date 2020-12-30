package org.webstory.ourstory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.webstory.ourstory.dao.UserDao;
import org.webstory.ourstory.model.User;

@Service("userService")
public class UserService implements UserDetailsService {
	
	@Autowired
	@Qualifier("userMongoDB")
	UserDao DB;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> found = DB.findByUsername(username);
		if (found.size() > 1 && !found.get(0).getUsername().equals("Anonymous")) {
			throw new UsernameNotFoundException("Duplicate Usernames!");
		}
		return found.get(0);
	}

}
