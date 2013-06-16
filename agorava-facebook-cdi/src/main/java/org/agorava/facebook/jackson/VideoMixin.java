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


package org.agorava.facebook.jackson;

import java.util.Date;
import java.util.List;

import org.agorava.facebook.model.Reference;
import org.agorava.facebook.model.Tag;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

/**
 * Annotated mixin to add Jackson annotations to Video.
 *
 * @author Craig Walls
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class VideoMixin {

    @JsonCreator
    VideoMixin(@JsonProperty("id") String id, @JsonProperty("from") Reference from, @JsonProperty("picture") String picture,
               @JsonProperty("embed_html") String embedHtml, @JsonProperty("icon") String icon,
               @JsonProperty("source") String source, @JsonProperty("created_time") Date createdTime,
               @JsonProperty("updated_time") Date updatedTime) {
    }

    @JsonProperty("tags")
    @JsonDeserialize(using = TagListDeserializer.class)
    List<Tag> tags;

    @JsonProperty("name")
    String name;

    @JsonProperty("description")
    String description;

}
