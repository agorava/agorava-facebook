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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.agorava.facebook.model.CheckinPost;
import org.agorava.facebook.model.Comment;
import org.agorava.facebook.model.LinkPost;
import org.agorava.facebook.model.ListAndCount;
import org.agorava.facebook.model.MusicPost;
import org.agorava.facebook.model.NotePost;
import org.agorava.facebook.model.PhotoPost;
import org.agorava.facebook.model.Post;
import org.agorava.facebook.model.Post.PostType;
import org.agorava.facebook.model.Reference;
import org.agorava.facebook.model.StatusPost;
import org.agorava.facebook.model.SwfPost;
import org.agorava.facebook.model.VideoPost;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Annotated mixin to add Jackson annotations to Post. Also defines Post subtypes to deserialize into based on the "type"
 * attribute.
 *
 * @author Craig Walls
 */
@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "postType")
@JsonSubTypes({@Type(name = "checkin", value = CheckinPost.class), @Type(name = "link", value = LinkPost.class),
        @Type(name = "note", value = NotePost.class), @Type(name = "photo", value = PhotoPost.class),
        @Type(name = "status", value = StatusPost.class), @Type(name = "video", value = VideoPost.class),
        @Type(name = "post", value = Post.class), @Type(name = "swf", value = SwfPost.class),
        @Type(name = "music", value = MusicPost.class)})
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class PostMixin {

    @JsonCreator
    PostMixin(@JsonProperty("id") String id, @JsonProperty("from") Reference from,
              @JsonProperty("created_time") Date createdTime, @JsonProperty("updated_time") Date updatedTime) {
    }

    @JsonProperty("to")
    @JsonDeserialize(using = ReferenceListDeserializer.class)
    List<Reference> to;

    @JsonProperty("message")
    String message;

    @JsonProperty("caption")
    String caption;

    @JsonProperty("picture")
    String picture;

    @JsonProperty("link")
    String link;

    @JsonProperty("subject")
    String subject;

    @JsonProperty("name")
    String name;

    @JsonProperty("description")
    String description;

    @JsonProperty("icon")
    String icon;

    @JsonProperty("application")
    Reference application;

    @JsonProperty("type")
    @JsonDeserialize(using = TypeDeserializer.class)
    PostType type;

    @JsonProperty("shares")
    @JsonDeserialize(using = CountDeserializer.class)
    int sharesCount;

    @JsonProperty("likes")
    @JsonDeserialize(using = ReferenceListAndCountDeserializer.class)
    ListAndCount<Reference> likes;

    @JsonProperty("comments")
    @JsonDeserialize(using = CommentListAndCountDeserializer.class)
    ListAndCount<Comment> comments;

    private static class TypeDeserializer extends JsonDeserializer<PostType> {
        @Override
        public PostType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return PostType.valueOf(jp.getText().toUpperCase());
        }
    }

    private static class CountDeserializer extends JsonDeserializer<Integer> {
        @Override
        public Integer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonNode tree = jp.readValueAsTree();
            return tree.has("count") ? tree.get("count").asInt() : 0;
        }
    }
}
