package org.webstory.ourstory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.webstory.ourstory.model.Story;
import org.webstory.ourstory.services.StoryService;

@SpringBootApplication
public class OurstoryApplication {
	
	@Autowired
	StoryService storyService;

	public static void main(String[] args) {
		SpringApplication.run(OurstoryApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void makeGlobalStory() {
		if (storyService.findByTitle("global") == null) {
			Story globalStory = new Story(true, "global");
			storyService.save(globalStory);
		}
	}

}
