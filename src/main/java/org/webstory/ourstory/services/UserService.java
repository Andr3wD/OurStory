package org.webstory.ourstory.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
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
	
	public User findByIp(String ip) {
		return DB.findByIp(ip).get(0); // TODO handle multiple of the same IP.
	}
	
	public User findById(ObjectId id) {
		Optional<User> temp = DB.findById(id);
		if (!temp.isEmpty()) {
			return temp.get();
		} else {
			return null;	
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> found = DB.findByUsername(username);
		if (found.size() > 1 && !found.get(0).getUsername().equals("Anonymous")) {
			throw new UsernameNotFoundException("Duplicate Usernames!");
		}
		return found.get(0);
	}

}
