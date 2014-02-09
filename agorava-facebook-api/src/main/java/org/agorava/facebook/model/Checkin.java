/*
 * Copyright 2013 Agorava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.agorava.facebook.model;

import java.util.Date;
import java.util.List;

import org.agorava.api.function.Identifiable;


/**
 * Model class representing a user checkin on Facebook Places.
 *
 * @author Craig Walls
 * @author Werner Keil
 */
public class Checkin implements Identifiable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -222873117836236212L;

	private final String id;

    private final Page place;

    private final Reference from;

    private final Reference application;

    private final Date createdTime;

    private String message;

    private ListAndCount<Comment> comments;

    private ListAndCount<Reference> likes;

    private List<Reference> tags;

    private Checkin(String id, Page place, Reference from, Reference application, Date createdTime) {
        this.id = id;
        this.place = place;
        this.from = from;
        this.application = application;
        this.createdTime = createdTime;
    }

    public String getId() {
        return id;
    }

    public Page getPlace() {
        return place;
    }

    public Reference getFrom() {
        return from;
    }

    public Reference getApplication() {
        return application;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getMessage() {
        return message;
    }

    public List<Comment> getComments() {
        return comments.getList();
    }

    public List<Reference> getLikes() {
        return likes.getList();
    }

    public List<Reference> getTags() {
        return tags;
    }

}
