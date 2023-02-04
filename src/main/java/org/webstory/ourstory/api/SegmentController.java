package org.webstory.ourstory.api;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webstory.ourstory.model.Segment;
import org.webstory.ourstory.model.Story;
import org.webstory.ourstory.model.User;
import org.webstory.ourstory.request.SegmentRequest;
import org.webstory.ourstory.response.SegmentResponse;
import org.webstory.ourstory.services.SegmentService;
import org.webstory.ourstory.services.StoryService;
import org.webstory.ourstory.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
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
	public ResponseEntity<?> addSegment(@RequestBody SegmentRequest requestSegment, HttpServletRequest request, UsernamePasswordAuthenticationToken p) {
		// TODO Validate input.
		System.out.println(requestSegment.storyTitle);
		Story story = storyService.findByTitle(requestSegment.storyTitle);
		System.out.println(story.getTitle());

		// Get the client IP and validate if the user recently added to the story or not.
		String clientIp = request.getRemoteAddr();
		User user;
		if (p == null) {
			user = userService.findByIp(clientIp);
		} else {
			user = (User) p.getPrincipal();
		}
		
		Date threshold = new Date((new Date()).getTime() - 1000 * 10); // Get the threshold date. 1000ms/s * 60s/min * 60min/hr = 1 hour before right now.
		if (user != null) { // User exists
			Segment mostRecent = userService.getRecentSegmentByPost(user);
			if (mostRecent == null || mostRecent.getCreated().before(threshold)) { // Most recent post was before threshold (1 hour ago)
				Segment newSeg = segmentService.requestToSegment(requestSegment); // Convert request to segment.
				newSeg.setOwner(user.getId());
				newSeg.setParent(story.getId());
				newSeg = segmentService.save(newSeg); // Save new segment

				story.addSegment(newSeg.getId());
				storyService.save(story);

				return new ResponseEntity<String>("Added old user's segment message: " + newSeg.getMessage(), HttpStatus.OK);
			} else {
				System.out.println("Attempt to post too early from IP: " + clientIp);
				return new ResponseEntity<String>("You posted too recently! Post again in: " + (threshold.getTime() - mostRecent.getCreated().getTime()) + "ms.", HttpStatus.FORBIDDEN);
			}
		} else { // User doesn't exist
			System.out.println("User without account added segment");
			Segment newSeg = segmentService.requestToSegment(requestSegment); // Convert request to segment.
			newSeg.setParent(story.getId());
			newSeg = segmentService.save(newSeg); // Save brand new segment

			// Initialize a new User
			User newUser = new User();
			newUser.setIp(clientIp);
			System.out.println(newSeg.getId());
			newUser.addSegment(newSeg.getId());

			newUser = userService.save(newUser); // Save new user in DB.
			newSeg.setOwner(newUser.getId());

			story.addSegment(newSeg.getId());
			storyService.save(story);

			segmentService.save(newSeg); // Re-Save segment edited with owner id (this will update the segment with the owner's id)
			return new ResponseEntity<String>("Added new user's segment message: " + newSeg.getMessage(), HttpStatus.OK);
		}
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