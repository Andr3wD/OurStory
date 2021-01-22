package org.webstory.ourstory.services;

import java.util.Date;
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
import org.webstory.ourstory.model.Segment;
import org.webstory.ourstory.model.User;

@Service("userService")
public class UserService implements UserDetailsService {
	
	@Autowired
	@Qualifier("userMongoDB")
	UserDao DB;
	
	@Autowired
	SegmentService segmentService;
	
	public User save(User user) {
		return DB.save(user);
	}

	
	public User findByIp(String ip) {
		List<User> list = DB.findByIp(ip);
		if (list.size() == 0) {
			return null;
		} else { 
			return list.get(0); // TODO handle multiple of the same IP.
		}
	}
	
	public User findById(ObjectId id) {
		Optional<User> temp = DB.findById(id);
		if (!temp.isEmpty()) {
			return temp.get();
		} else {
			return null;	
		}
	}
	
	public Segment getRecentSegmentByPost(User user) {
		List<ObjectId> segList = user.getSegments();
		if (segList.size() > 1) {
			ObjectId id = segList.get(0);
			Date recent = segmentService.findById(id).getCreated();
			for (ObjectId segID : user.getSegments()) {
				Date loopSegDate = segmentService.findById(segID).getCreated();
				if (recent.compareTo(loopSegDate) > 0) {
					recent = loopSegDate;
					id = segID;
				}
			}
			return segmentService.findById(id);
		} else if (segList.size() == 1) {
			return segmentService.findById(segList.get(0));
		} else { // No post has been made
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
