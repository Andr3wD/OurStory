package org.webstory.ourstory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.webstory.ourstory.dao.StoryDao;
import org.webstory.ourstory.model.Story;
import org.webstory.ourstory.response.SegmentResponse;
import org.webstory.ourstory.response.StoryResponse;

@Service("storyService")
public class StoryService {

	@Autowired
	SegmentService segmentService;

	@Autowired
	@Qualifier("storyMongoDB") // This is what links our choice of DB to our use of it.
	StoryDao DB;

	public Story save(Story story) {
		return DB.save(story);
	}
	
	public Story findByTitle(String title) {
		List<Story> temp = DB.findByTitle(title);
		if (!temp.isEmpty()) {
			return temp.get(0); // TODO handle multiple flaged as global
		} else {
			return null;
		}
	}

	public boolean delete(Story story) {
		try {
			DB.delete(story);
			return true;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Story findById(ObjectId id) {
		Optional<Story> temp = DB.findById(id);
		if (!temp.isEmpty()) {
			return temp.get();
		} else {
			return null;
		}
	}
	
	public Story findByGlobal(Boolean bool) {
		List<Story> temp = DB.findByGlobal(bool);
		if (!temp.isEmpty()) {
			return temp.get(0); // TODO handle multiple flaged as global
		} else {
			return null;
		}
	}

	public StoryResponse convertToResponse(ObjectId id) {
		Story story = findById(id);
		List<ObjectId> segments = story.getSegments();
		List<SegmentResponse> responses = new ArrayList<SegmentResponse>();
		List<String> entries = new ArrayList<String>();
		List<String> participants = new ArrayList<String>();
		StoryResponse storyR = new StoryResponse();

		for (ObjectId seg : segments) {
			SegmentResponse response = segmentService.convertToResponse(seg);
			responses.add(response);
			entries.add(response.getMessage());
			participants.add(response.getUsername());

		}
		storyR.setSegments(responses);
		storyR.setEntries(entries);
		storyR.setParticipants(participants);
		return storyR;
	}
	
	public List<SegmentResponse> convertToSegmentResponse(ObjectId id) {
		Story story = findById(id);
		List<ObjectId> segments = story.getSegments();
		List<SegmentResponse> responses = new ArrayList<>();
		for (ObjectId seg : segments) {
			SegmentResponse response = segmentService.convertToResponse(seg);
			responses.add(response);
		}
		return responses;
	}

	/**
	 * USE SPARINGLY
	 * 
	 * @return List of all Stories in the DB.
	 */
	public List<Story> getAll() {
		return DB.getAll();
	}

}
