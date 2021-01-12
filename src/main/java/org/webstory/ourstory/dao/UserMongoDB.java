package org.webstory.ourstory.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.webstory.ourstory.model.User;
import org.webstory.ourstory.repositories.UserRepository;

@Repository("userMongoDB")
public class UserMongoDB implements UserDao {
	
	@Autowired
	UserRepository repo;

	@Override
	public User save(User user) {
		return repo.save(user);
	}

	@Override
	public User insert(User user) {
		return repo.insert(user);
	}

	@Override
	public void delete(User user) {
		repo.delete(user);
	}

	@Override
	public long size() {
		return repo.count();
	}

	@Override
	public Optional<User> findById(ObjectId id) {
		return repo.findById(id);
	}

	@Override
	public List<User> getAll() {
		return repo.findAll();
	}

	@Override
	public List<User> findByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public List<User> findByIp(String ip) {
		return repo.findByIp(ip);
	}

}
