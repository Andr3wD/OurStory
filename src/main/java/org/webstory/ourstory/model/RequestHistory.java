package org.webstory.ourstory.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "request_history")
public @Data class RequestHistory {

    @Id
    private ObjectId id;
    private Date created;
    private String relativePath;
    private String ip;
    private String requestPayload;

}
