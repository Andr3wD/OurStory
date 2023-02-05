package org.webstory.ourstory.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.webstory.ourstory.model.Segment;

public interface SegmentRepository extends MongoRepository<Segment, ObjectId> {

}
