package org.webstory.ourstory.model;

import java.util.List;

import org.bson.types.ObjectId;
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

	private ObjectId id;
	private List<User> contributors;

}