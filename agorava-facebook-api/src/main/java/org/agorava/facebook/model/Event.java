/*
 * Copyright 2012 Agorava
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


/**
 * Model class representing an event.
 *
 * @author Craig Walls
 */
public class Event {

    private final String id;

    private final String name;

    private String description;

    private final Reference owner;

    private final Privacy privacy;

    private final Date startTime;

    private final Date endTime;

    private String location;

    private final Date updatedTime;

    public Event(String id, String name, Reference owner, Privacy privacy, Date startTime, Date endTime, Date updatedTime) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.privacy = privacy;
        this.startTime = startTime;
        this.endTime = endTime;
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Reference getOwner() {
        return owner;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public static enum Privacy {OPEN, SECRET, CLOSED}

}
