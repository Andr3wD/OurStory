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
import org.webstory.ourstory.model.User;
import org.webstory.ourstory.request.SegmentRequest;
import org.webstory.ourstory.services.UserService;


@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
}
