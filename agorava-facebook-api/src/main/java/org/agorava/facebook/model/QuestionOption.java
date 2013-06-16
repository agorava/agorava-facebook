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

public class QuestionOption {

    private final String id;
    private final String name;
    private final Reference from;
    private final int votes;
    private final Date createdTime;

    public QuestionOption(String id, String name, Reference from, int votes, Date createdTime) {
        this.id = id;
        this.name = name;
        this.from = from;
        this.votes = votes;
        this.createdTime = createdTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Reference getFrom() {
        return from;
    }

    public int getVotes() {
        return votes;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

}
