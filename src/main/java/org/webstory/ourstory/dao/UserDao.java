package org.webstory.ourstory.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.webstory.ourstory.model.User;

public interface UserDao {

	User save(User story);
	User insert(User story);
	void delete(User story);
	long size();
	
	
	Optional<User> findById(ObjectId id);
	List<User> getAll();
	List<User> findByUsername(String username);
	List<User> findByIp(String ip);
}
