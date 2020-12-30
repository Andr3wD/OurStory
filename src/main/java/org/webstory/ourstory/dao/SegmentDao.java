package org.webstory.ourstory.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.webstory.ourstory.model.Segment;

public interface SegmentDao {
	
	Segment save(Segment segment);
	Segment insert(Segment segment);
	void delete(Segment segment);
	long size();
	
	
	Optional<Segment> findById(ObjectId id);
	List<Segment> getAll();

}
