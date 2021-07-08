package org.webstory.ourstory.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webstory.ourstory.services.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping()
	@PreAuthorize("permitAll()")
	public ResponseEntity<?> testUsers() {
		return new ResponseEntity<String>("Test", HttpStatus.I_AM_A_TEAPOT);
	}

	
}
