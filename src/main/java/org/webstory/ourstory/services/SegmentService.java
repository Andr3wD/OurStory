package org.webstory.ourstory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.webstory.ourstory.dao.SegmentDao;
import org.webstory.ourstory.model.Segment;
import org.webstory.ourstory.request.SegmentRequest;

@Service
public class SegmentService {
	
	@Autowired
	@Qualifier("segmentMongoDB")
	SegmentDao DB;
	
	public Segment save(Segment segment) {
		return DB.save(segment);
	}

	public Segment requestToSegment(SegmentRequest request) {
		Segment seg = new Segment();
		seg.setMessage(request.message);
		return seg;
	}
	
}
