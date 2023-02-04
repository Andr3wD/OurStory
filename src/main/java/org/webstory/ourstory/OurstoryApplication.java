package org.webstory.ourstory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.webstory.ourstory.model.Story;
import org.webstory.ourstory.model.User;
import org.webstory.ourstory.services.StoryService;
import org.webstory.ourstory.services.UserService;

@SpringBootApplication
public class OurstoryApplication {
	
	@Autowired
	StoryService storyService;
	
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(OurstoryApplication.class, args);
	}
	
	
	// Keep even for deployment.
	@EventListener(ApplicationReadyEvent.class)
	public void makeFrontPageStory() {
		if (storyService.findByTitle("front-page") == null) {
			System.out.println("Adding front-page to DB");
			Story frontPageStory = new Story(Story.StoryType.FRONT_PAGE, "front-page");
			storyService.save(frontPageStory);
		}
	}

	// TODO remove for deployment
	@EventListener(ApplicationReadyEvent.class)
	public void makeAdminAccount() {
		try {
			User user = (User) userService.loadUserByUsername("Admin");
			userService.delete(user);
			user = new User();
			user.addRole("ROLE_ADMIN");
			user.setUsername("Admin");
			user.setPassword("Admin");
			userService.save(user);
		} catch (UsernameNotFoundException e) {
			User user = new User();
			user.addRole("ROLE_ADMIN");
			user.setUsername("Admin");
			user.setPassword("Admin");
			userService.save(user);
		}
	}
}
