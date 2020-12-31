package org.webstory.ourstory.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webstory.ourstory.model.Segment;
import org.webstory.ourstory.model.Story;
import org.webstory.ourstory.services.StoryService;

@RequestMapping("/test") // This maps this whole controller to /test
@RestController // This is essentially a macro for @controller and @responsebody, which
						// basically tells Spring that this class handles backend API calls and
						// responses.
public class TestController {

	@Autowired
	StoryService storyService;

	@GetMapping("/t/{name}")
	public ResponseEntity<?> testEndpoint(@RequestBody Segment s, @PathVariable("name") String name) {
		storyService.save(new Story(true, name));

		return new ResponseEntity<String>("Return Stuff", HttpStatus.OK);
	}

	@GetMapping("/first")
	public ResponseEntity<?> testEndpointFirst() {

		return new ResponseEntity<String>(storyService.getAll().get(0).getTitle(), HttpStatus.OK);
	}
}
