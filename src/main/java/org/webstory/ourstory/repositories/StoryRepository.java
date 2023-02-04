package org.webstory.ourstory.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.webstory.ourstory.model.Story;

/**
 * This is actually the repository. We could 'build' off of this instead, but the DAO pattern is a little safer.
 *
 */
public interface StoryRepository extends MongoRepository<Story, ObjectId> {

	// TODO change to 'findByTitle' eventually
	List<Story> findByStoryType(Story.StoryType storyType); // MongoDB just does its magic here to turn this into an actual working method behind the scenes.
	List<Story> findByTitle(String title);

}
