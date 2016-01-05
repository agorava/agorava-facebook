/*
 * Copyright 2016 Agorava
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

/**
 *
 */
package org.agorava.facebook.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.agorava.FacebookBaseService;
import org.agorava.api.exception.AgoravaException;
import org.agorava.facebook.Facebook;
import org.agorava.facebook.FeedService;
import org.agorava.facebook.GraphApi;
import org.agorava.facebook.model.FacebookLink;
import org.agorava.facebook.model.LinkPost;
import org.agorava.facebook.model.NotePost;
import org.agorava.facebook.model.Post;
import org.agorava.facebook.model.Post.PostType;
import org.agorava.facebook.model.StatusPost;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Antoine Sabot-Durand
 * @author Werner Keil
 */
@Named("facebookFeed")
@Facebook
public class FeedServiceImpl extends FacebookBaseService implements FeedService {

    @Inject
    @Facebook
    private GraphApi graphApi;

    @Inject
    private ObjectMapper objectMapper;

    @Override
    public List<Post> getFeed() {
        return getFeed("me", 0, 25);
    }

    @Override
    public List<Post> getFeed(int offset, int limit) {
        return getFeed("me", offset, limit);
    }

    @Override
    public List<Post> getFeed(String ownerId) {
        return getFeed(ownerId, 0, 25);
    }

    @Override
    public List<Post> getFeed(String ownerId, int offset, int limit) {
        JsonNode responseNode =  fetchConnectionList(graphApi.getBaseUrl() + ownerId + "/feed", offset, limit);
        return deserializeList(responseNode, null, Post.class);
    }

    @Override
    public List<Post> getHomeFeed() {
        return getHomeFeed(0, 25);
    }

    @Override
    public java.util.List<Post> getHomeFeed(int offset, int limit) {
        JsonNode responseNode = fetchConnectionList(graphApi.getBaseUrl() + "me/home", offset, limit);
        return deserializeList(responseNode, null, Post.class);
    }

    @Override
    public List<StatusPost> getStatuses() {
        return getStatuses("me", 0, 25);
    }

    @Override
    public List<StatusPost> getStatuses(int offset, int limit) {
        return getStatuses("me", offset, limit);
    }

    @Override
    public List<StatusPost> getStatuses(String userId) {
        return getStatuses(userId, 0, 25);
    }

    @Override
    public List<StatusPost> getStatuses(String userId, int offset, int limit) {
        JsonNode responseNode = fetchConnectionList(graphApi.getBaseUrl() + userId + "/statuses", offset, limit);
        return deserializeList(responseNode, "status", StatusPost.class);
    }

    @Override
    public List<LinkPost> getLinks() {
        return getLinks("me", 0, 25);
    }

    @Override
    public List<LinkPost> getLinks(int offset, int limit) {
        return getLinks("me", offset, limit);
    }

    @Override
    public List<LinkPost> getLinks(String ownerId) {
        return getLinks(ownerId, 0, 25);
    }

    @Override
    public List<LinkPost> getLinks(String ownerId, int offset, int limit) {
        JsonNode responseNode = fetchConnectionList(graphApi.getBaseUrl() + ownerId + "/links", offset, limit);
        return deserializeList(responseNode, "link", LinkPost.class);
    }

    @Override
    public List<NotePost> getNotes() {
        return getNotes("me", 0, 25);
    }

    @Override
    public List<NotePost> getNotes(int offset, int limit) {
        return getNotes("me", offset, limit);
    }

    @Override
    public List<NotePost> getNotes(String ownerId) {
        return getNotes(ownerId, 0, 25);
    }

    @Override
    public List<NotePost> getNotes(String ownerId, int offset, int limit) {
        JsonNode responseNode = fetchConnectionList(graphApi.getBaseUrl() + ownerId + "/notes", offset, limit);
        return deserializeList(responseNode, "note", NotePost.class);
    }

    @Override
    public List<Post> getPosts() {
        return getPosts("me", 0, 25);
    }

    @Override
    public List<Post> getPosts(int offset, int limit) {
        return getPosts("me", offset, limit);
    }

    @Override
    public List<Post> getPosts(String ownerId) {
        return getPosts(ownerId, 0, 25);
    }

    @Override
    public List<Post> getPosts(String ownerId, int offset, int limit) {
        JsonNode responseNode = fetchConnectionList(graphApi.getBaseUrl() + ownerId + "/posts", offset, limit);
        return deserializeList(responseNode, null, Post.class);
    }

    @Override
    public Post getPost(String entryId) {
        ObjectNode responseNode = (ObjectNode) getService().get(graphApi.getBaseUrl() + entryId,
                JsonNode.class);
        return deserializePost(null, Post.class, responseNode);
    }

    @Override
    public String updateStatus(String message) {
        return post("me", message);
    }

    @Override
    public String postLink(String message, FacebookLink link) {
        return postLink("me", message, link);
    }

    @Override
    public String postLink(String ownerId, String message, FacebookLink link) {
        Map<String, Object> map = new HashMap();
        map.put("link", link.getLink());
        map.put("name", link.getName());
        map.put("caption", link.getCaption());
        map.put("description", link.getDescription());
        map.put("message", message);
        return graphApi.publish(ownerId, "feed", map);
    }

    @Override
    public String post(String ownerId, String message) {
        Map<String, Object> map = new HashMap();
        map.put("message", message);
        return graphApi.publish(ownerId, "feed", map);
    }

    @Override
    public void deletePost(String id) {
        graphApi.delete(id);
    }

    @Override
    public List<Post> searchPublicFeed(String query) {
        return searchPublicFeed(query, 0, 25);
    }

    @Override
    public List<Post> searchPublicFeed(String query, int offset, int limit) {
        Map<String, Object> params = new HashMap();
        params.put("q", query);
        params.put("type", "post");
        params.put("offset", String.valueOf(offset));
        params.put("limit", String.valueOf(limit));
        String uri = buildUri("https://graph.facebook.com/search", params);
        JsonNode responseNode = getService().get(uri, JsonNode.class);
        return deserializeList(responseNode, null, Post.class);
    }

    @Override
    public List<Post> searchHomeFeed(String query) {
        return searchHomeFeed(query, 0, 25);
    }

    @Override
    public List<Post> searchHomeFeed(String query, int offset, int limit) {
        Map<String, Object> params = new HashMap();
        params.put("q", query);
        params.put("offset", String.valueOf(offset));
        params.put("limit", String.valueOf(limit));
        String uri = buildUri("https://graph.facebook.com/me/home", params);
        JsonNode responseNode = getService().get(uri, JsonNode.class);
        return deserializeList(responseNode, null, Post.class);
    }

    @Override
    public List<Post> searchUserFeed(String query) {
        return searchUserFeed("me", query, 0, 25);
    }

    @Override
    public List<Post> searchUserFeed(String query, int offset, int limit) {
        return searchUserFeed("me", query, offset, limit);
    }

    @Override
    public List<Post> searchUserFeed(String userId, String query) {
        return searchUserFeed(userId, query, 0, 25);
    }

    @Override
    public List<Post> searchUserFeed(String userId, String query, int offset, int limit) {
        Map<String, Object> params = new HashMap();
        params.put("q", query);
        params.put("offset", String.valueOf(offset));
        params.put("limit", String.valueOf(limit));
        String uri = buildUri(graphApi.getBaseUrl() + userId + "/feed", params);
        JsonNode responseNode = getService().get(uri, JsonNode.class);
        return deserializeList(responseNode, null, Post.class);
    }

    // private helpers

    private JsonNode fetchConnectionList(String baseUri, int offset, int limit) {
        Map<String, Object> params = new HashMap();
        params.put("offset", String.valueOf(offset));
        params.put("limit", String.valueOf(limit));
        String uri = buildUri(baseUri, params);
        JsonNode responseNode = getService().get(uri, JsonNode.class);
        return responseNode;
    }

    private <T> List<T> deserializeList(JsonNode jsonNode, String postType, Class<T> type) {
        JsonNode dataNode = jsonNode.get("data");
        List<T> posts = new ArrayList<T>();
        for (Iterator<JsonNode> iterator = dataNode.iterator(); iterator.hasNext(); ) {
            posts.add(deserializePost(postType, type, (ObjectNode) iterator.next()));
        }
        return posts;
    }

    private <T> T deserializePost(String postType, Class<T> type, ObjectNode node) {
        try {
            if (postType == null) {
                postType = determinePostType(node);
            }

            // Must have separate postType field for polymorphic deserialization. If we key off of the "type" field,
            // then it will
            // be null when trying to deserialize the type property.
            node.put("postType", postType); // used for polymorphic deserialization
            node.put("type", postType); // used to set Post's type property
            return objectMapper.reader(type).readValue(node.toString()); // TODO: EXTREMELY HACKY--TEMPORARY UNTIL I FIGURE
            // OUT HOW JACKSON 2 DOES THIS
        } catch (IOException shouldntHappen) {
            throw new AgoravaException("Error deserializing " + postType + " post", shouldntHappen);
        }
    }

    private String determinePostType(ObjectNode node) {
        if (node.has("type")) {
            try {
                String type = node.get("type").textValue();
                PostType.valueOf(type.toUpperCase());
                return type;
            } catch (IllegalArgumentException e) {
                return "post";
            }
        }
        return "post";
    }
    
	private static final String[] ALL_POST_FIELDS = {
		"id", "actions", "admin_creator", "application", "caption", "created_time", "description", "from", "icon",
		"is_hidden", "is_published", "link", "message", "message_tags", "name", "object_id", "picture", "place", 
		"privacy", "properties", "source", "status_type", "story", "to", "type", "updated_time", "with_tags", "shares"
	};
}
