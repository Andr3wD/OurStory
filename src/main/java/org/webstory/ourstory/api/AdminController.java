package org.webstory.ourstory.api;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webstory.ourstory.model.HttpRequestInstance;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/admin")
@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')") // Require all requests to the admin api to have admin credentials.
public class AdminController {
	
	public static List<HttpRequestInstance> requestHistory = new LinkedList<>();

	@SuppressWarnings("unchecked")
	@GetMapping("/userStats")
	public ResponseEntity<?> getUserGraph() {
		// TODO process requestHistory into labels for each hour and send.
		return new ResponseEntity(requestHistory, HttpStatus.ACCEPTED);
	}
}
