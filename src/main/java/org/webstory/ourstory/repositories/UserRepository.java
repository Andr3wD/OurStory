package org.webstory.ourstory.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.webstory.ourstory.model.User;

public interface UserRepository extends MongoRepository<User, ObjectId> {

	List<User> findByIp(String ip);
	List<User> findByUsername(String username);
}
