package org.webstory.ourstory.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.webstory.ourstory.dao.StoryDao;
import org.webstory.ourstory.model.Segment;
import org.webstory.ourstory.model.Story;
import org.webstory.ourstory.request.SegmentRequest;

@Service("storyService")
public class StoryService {
	
	@Autowired
	@Qualifier("storyMongoDB") // This is what links our choice of DB to our use of it.
	StoryDao DB;
	
	public Story save(Story story) {
		return DB.save(story);
	}
	
	public boolean delete(Story story) {
		try {
			DB.delete(story);
			return true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Story findById(ObjectId id) {
		Optional<Story> temp = DB.findById(id);
		if (!temp.isEmpty()) {
			return temp.get();
		} else {
			return null;
		}
	}
	
	/**
	 * USE SPARINGLY
	 * @return List of all Stories in the DB.
	 */
	public List<Story> getAll() {
		return DB.getAll();
	}

}
