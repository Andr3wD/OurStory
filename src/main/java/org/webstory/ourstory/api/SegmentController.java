package org.webstory.ourstory.api;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.webstory.ourstory.model.Segment;
import org.webstory.ourstory.model.User;
import org.webstory.ourstory.request.SegmentRequest;
import org.webstory.ourstory.response.SegmentResponse;
import org.webstory.ourstory.services.SegmentService;
import org.webstory.ourstory.services.StoryService;
import org.webstory.ourstory.services.UserService;

@RequestMapping("/segment")
@RestController
public class SegmentController {
	
	
	@Autowired
	StoryService storyService;
	
	@Autowired
	SegmentService segmentService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/addSegment") 
	public ResponseEntity<?> addSegment(@RequestBody SegmentRequest requestSegment) {
		// TODO Validate input.
		
		Segment seg = segmentService.requestToSegment(requestSegment);
		segmentService.save(seg);
		
		return new ResponseEntity<String>("Added your segment named: " + seg.getMessage(), HttpStatus.OK);
	}
	
	@PostMapping("/editSegment")
	public ResponseEntity<?> editSegment(@RequestBody SegmentRequest requestSegment) {
		// TODO Validate input.
		
		ObjectId id = new ObjectId(requestSegment.hexObjectId);
		String segNew = requestSegment.message;
		Segment seg = segmentService.findById(id);
		
		seg.setMessage(segNew);
		segmentService.save(seg);
		
		return new ResponseEntity<String>("Edited your segment named: " + seg.getMessage(), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteSegment")
	public ResponseEntity<?> deleteSegment(@RequestBody SegmentRequest requestSegment) {
		// TODO Validate input.
		
		ObjectId id = new ObjectId(requestSegment.hexObjectId);
		Segment seg = segmentService.findById(id);
		
		segmentService.delete(seg);
		
		return new ResponseEntity<String>("Deleted segment with message: " + seg.getMessage(), HttpStatus.OK);
	}
	
	@GetMapping("/getSegment")
	public ResponseEntity<?> getSegment(@RequestBody SegmentRequest segmentRequest) {
		ObjectId segId = new ObjectId(segmentRequest.hexObjectId);
			SegmentResponse response = segmentService.convertToResponse(segId);
			
		return new ResponseEntity<SegmentResponse>(response, HttpStatus.OK);
		
	}
}