package org.webstory.ourstory.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * This model is for stories as a whole, putting together all the individual
 * parts.
 *
 */
// This annotation just tells MongoDB what 'collection' name these instances should be saved to.
@Document(collection = "stories")
// The @Data annotation removes all boilerplate. Getters, setters, equals, hashcode, tostring, and default constructor are automatically added behind the scenes.
public @Data class Story {

	public enum StoryType {
		FRONT_PAGE,
		GLOBAL,
		PUBLIC,
		PRIVATE
	}

	@Id
	private ObjectId id;
	private List<ObjectId> contributors = new ArrayList<ObjectId>();
	private List<ObjectId> segments = new ArrayList<ObjectId>();
	private StoryType storyType; // Default, new stories are not global.
	private String title;
	
	public Story(StoryType storyType, String title) {
		this.storyType = storyType;
		this.title = title;
	}
	
	public boolean addSegment(ObjectId seg) {
		return segments.add(seg);
	}

}
