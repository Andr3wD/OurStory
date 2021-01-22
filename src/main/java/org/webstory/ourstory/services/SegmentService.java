package org.webstory.ourstory.services;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.webstory.ourstory.dao.SegmentDao;
import org.webstory.ourstory.model.Segment;
import org.webstory.ourstory.model.Story;
import org.webstory.ourstory.model.User;
import org.webstory.ourstory.request.SegmentRequest;
import org.webstory.ourstory.response.SegmentResponse;
import org.webstory.ourstory.services.UserService;

@Service
public class SegmentService {
	@Autowired
	UserService userService;
	@Autowired
	@Qualifier("segmentMongoDB")
	SegmentDao DB;
	
	public Segment save(Segment segment) {
		return DB.save(segment);
	}
	
	public boolean delete(Segment segment) {
		try {
			DB.delete(segment);
			return true;
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Segment requestToSegment(SegmentRequest request) {
		Segment seg = new Segment();
		seg.setMessage(request.message);
		return seg;
	}
	
	public Segment findById(ObjectId id) {
		Optional<Segment> temp = DB.findById(id);
		if (!temp.isEmpty()) {
			return temp.get();
		} else {
			return null;
		}
	}
	
	public SegmentResponse convertToResponse(ObjectId id) {
		Segment seg = findById(id);
		ObjectId useId = seg.getOwner();
		User user = userService.findById(useId);

		SegmentResponse response = new SegmentResponse();
		
		response.setHexObjectId(id.toHexString());
		response.setMessage(seg.getMessage());
		response.setUsername(user.getUsername());

		return response;
	}
}
