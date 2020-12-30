package org.webstory.ourstory.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "segments")
public @Data class Segment {

	@Id
	private ObjectId id;

	private String message;

	private ObjectId owner;

	private Date created = new Date();

	// TODO add ratings and whatnot later.

}
