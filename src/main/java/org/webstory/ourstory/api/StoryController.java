package org.webstory.ourstory.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webstory.ourstory.response.SegmentResponse;
import org.webstory.ourstory.services.SegmentService;
import org.webstory.ourstory.services.StoryService;


@CrossOrigin(origins = "*", maxAge = 3600)
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
	public ResponseEntity<List<SegmentResponse>> getSegments(@RequestParam String title) {
		System.out.println("title requested: " + title);
		if (title.equals("global")) {
			// TODO break into 3 lines
			// TODO LOOKAT change SegmentResponse return to StoryResponse
			
			ObjectId globalStory = storyService.findByTitle("global").getId(); // Get ID of global story
			List<SegmentResponse> responses = storyService.convertToSegmentResponse(globalStory);
			return new ResponseEntity<List<SegmentResponse>>(responses, HttpStatus.OK);
		}
		return null;
			//		} else {
//			ObjectId id = new ObjectId(storyRequest.hexObjectId);
//			
//			StoryResponse storyR = storyService.convertToResponse(id);
//			List<SegmentResponse> responses = storyR.getSegments();
//			return new ResponseEntity<List<SegmentResponse>>(responses, HttpStatus.OK);
//		}
	}
}
