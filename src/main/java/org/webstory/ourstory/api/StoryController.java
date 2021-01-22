package org.webstory.ourstory.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webstory.ourstory.request.StoryRequest;
import org.webstory.ourstory.response.SegmentResponse;
import org.webstory.ourstory.response.StoryResponse;
import org.webstory.ourstory.services.SegmentService;
import org.webstory.ourstory.services.StoryService;


@RequestMapping("/story") // Global story.
@RestController
public class StoryController {

	@Autowired
	StoryService storyService;
	
	@Autowired
	SegmentService segmentService;

	@PostMapping("/add")
	public ResponseEntity<?> appendGlobalSegment(HttpServletRequest requestInfo) {

		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@GetMapping("/getSegments")
	public ResponseEntity<?> getSegments(@RequestBody StoryRequest storyRequest) {
		
		ObjectId id = new ObjectId(storyRequest.hexObjectId);
		
		StoryResponse storyR = storyService.convertToResponse(id);
		List<SegmentResponse> responses = storyR.getSegments();
		
		return new ResponseEntity<List<SegmentResponse>>(responses, HttpStatus.OK);
	}
}
