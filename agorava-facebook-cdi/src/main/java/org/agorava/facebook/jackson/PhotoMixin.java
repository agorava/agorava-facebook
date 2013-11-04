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


package org.agorava.facebook.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.agorava.facebook.model.Photo.Image;
import org.agorava.facebook.model.Reference;
import org.agorava.facebook.model.Tag;

import java.util.Date;
import java.util.List;

/**
 * Annotated mixin to add Jackson annotations to Photo.
 *
 * @author Craig Walls
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class PhotoMixin {

    @JsonCreator
    PhotoMixin(@JsonProperty("id") String id, @JsonProperty("from") Reference from, @JsonProperty("link") String link,
               @JsonProperty("icon") String icon, @JsonProperty("created_time") Date createdTime,
               @JsonProperty("images") List<Image> images) {
    }

    @JsonProperty("name")
    String name;

    @JsonProperty("position")
    Integer position;

    @JsonProperty("updated_time")
    Date updatedTime;

    @JsonProperty("tags")
    @JsonDeserialize(using = TagListDeserializer.class)
    List<Tag> tags;

    public static abstract class ImageMixin {
        @JsonCreator
        public ImageMixin(@JsonProperty("source") String source, @JsonProperty("width") int width,
                          @JsonProperty("height") int height) {
        }
    }
}
