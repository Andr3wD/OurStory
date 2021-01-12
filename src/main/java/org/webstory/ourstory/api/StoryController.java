package org.webstory.ourstory.api;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webstory.ourstory.model.Segment;
import org.webstory.ourstory.request.SegmentRequest;
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
}
