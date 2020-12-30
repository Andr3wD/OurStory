package org.webstory.ourstory.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.webstory.ourstory.model.Story;
import org.webstory.ourstory.repositories.StoryRepository;

/**
 * 
 * This is an implementation of StoryDao.
 * So, this is just an instance of a database for stories, but implemented in MongoDB.
 *
 */
@Repository("storyMongoDB")
public class StoryMongoDB implements StoryDao {
	
	@Autowired
	StoryRepository repo;

	@Override
	public Story save(Story story) {
		return repo.save(story);
	}

	@Override
	public Story insert(Story story) {
		return repo.insert(story);
	}

	@Override
	public void delete(Story story) {
		repo.delete(story);
	}

	@Override
	public long size() {
		return repo.count();
	}

	@Override
	public Optional<Story> findById(ObjectId id) {
		return repo.findById(id);
	}

	@Override
	public List<Story> getAll() {
		return repo.findAll();
	}

	@Override
	public List<Story> findByGlobal(Boolean global) {
		return repo.findByGlobal(global);
	}

}
