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


/**
 * Model class representing a Group.
 *
 * @author Craig Walls
 */
public class Group {

    private final String id;

    private final Reference owner;

    private final String name;

    private final Privacy privacy;

    private final String icon;

    private final Date updatedTime;

    private final String email;

    private String description;

    public Group(String id, Reference owner, String name, Privacy privacy, String icon, Date updatedTime, String email) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.privacy = privacy;
        this.icon = icon;
        this.updatedTime = updatedTime;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public Reference getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public String getIcon() {
        return icon;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public static enum Privacy {OPEN, SECRET, CLOSED}

}
