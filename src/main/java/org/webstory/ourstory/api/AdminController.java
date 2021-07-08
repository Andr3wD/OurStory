package org.webstory.ourstory.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/admin")
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')") // Require all requests to the admin api to have admin credentials.
public class AdminController {

	@GetMapping("/userStats")
	public ResponseEntity<?> getUserGraph() {
		// TODO implement.
		return new ResponseEntity("TEST TEXT", HttpStatus.I_AM_A_TEAPOT);
	}
}
