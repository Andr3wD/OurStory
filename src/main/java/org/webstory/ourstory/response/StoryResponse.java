package org.webstory.ourstory.response;

import java.util.List;

import lombok.Data;

public @Data class StoryResponse {
	
	private List<SegmentResponse> segments;
	private String title;

}
