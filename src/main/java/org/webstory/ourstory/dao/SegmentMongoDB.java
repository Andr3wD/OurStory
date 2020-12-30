package org.webstory.ourstory.dao;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.webstory.ourstory.model.Segment;
import org.webstory.ourstory.repositories.SegmentRepository;

@Repository("segmentMongoDB")
public class SegmentMongoDB implements SegmentDao {
	
	@Autowired
	SegmentRepository repo;

	@Override
	public Segment save(Segment segment) {
		return repo.save(segment);
	}

	@Override
	public Segment insert(Segment segment) {
		return repo.insert(segment);
	}

	@Override
	public void delete(Segment segment) {
		repo.delete(segment);
	}

	@Override
	public long size() {
		return repo.count();
	}

	@Override
	public Optional<Segment> findById(ObjectId id) {
		return repo.findById(id);
	}

	@Override
	public List<Segment> getAll() {
		return repo.findAll();
	}

}
