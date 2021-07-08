package org.webstory.ourstory.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.webstory.ourstory.model.User;

public interface UserDao {

	User save(User user);
	User insert(User user);
	void delete(User user);
	long size();
	
	
	Optional<User> findById(ObjectId id);
	List<User> getAll();
	List<User> findByUsername(String username);
	List<User> findByIp(String ip);
}
