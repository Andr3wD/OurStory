package org.webstory.ourstory.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
/**
 * This class is for users and the parts they submit.
 * Users can be individuals without accounts, so we need to block via IP and fingerprinting.
 *
 */
//This annotation just tells MongoDB what 'collection' name these instances should be saved to.
@Document(collection = "users")
//The @Data annotation removes all boilerplate. Getters, setters, equals, hashcode, tostring, and default constructor are automatically added behind the scenes.
public @Data class User {
	
	private ObjectId id;
	private String ip; // HttpServletRequest.getRemoteAddr() returns String ip
	private String message;
	private String name = "Anonymous";
	
}
