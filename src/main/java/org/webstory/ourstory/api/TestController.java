package org.webstory.ourstory.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test") // This maps this whole controller to /test
@RestController // This is essentially a macro for @controller and @responsebody, which
						// basically tells Spring that this class handles backend API calls and
						// responses.
public class TestController {

	@GetMapping("/test")
	public ResponseEntity<?> testEndpoint() {
		return new ResponseEntity<String>("Return Stuff", HttpStatus.OK);
	}
}
