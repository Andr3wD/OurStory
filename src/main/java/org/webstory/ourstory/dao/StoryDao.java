package org.webstory.ourstory.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.webstory.ourstory.model.Story;

/**
 * This interface DAO (Data Access Object) is a blueprint for any object that should access the data.
 * For example, every different type of database that could be implemented should include a 'save' and 'delete' method.
 *
 */
public interface StoryDao {
	
	Story save(Story story);
	Story insert(Story story);
	void delete(Story story);
	long size();
	
	
	Optional<Story> findById(ObjectId id);
	List<Story> findByStoryType(Story.StoryType storyType);
	List<Story> findByTitle(String title);
	List<Story> getAll();
	
}
