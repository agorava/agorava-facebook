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

package org.agorava.facebook.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.agorava.FacebookBaseService;
import org.agorava.facebook.Facebook;
import org.agorava.facebook.GraphApi;
import org.agorava.facebook.UserService;
import org.agorava.facebook.model.FacebookProfile;
import org.agorava.facebook.model.ImageType;
import org.agorava.facebook.model.Reference;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Antoine Sabot-Durand
 * @author Werner Keil
 */
@Facebook
@Named
public class UserServiceImpl extends FacebookBaseService implements UserService {

    @Inject
    @Facebook
    private GraphApi graphApi;

    @Override
    public FacebookProfile getUserProfile() {
        return getUserProfile("me");
    }

    @Override
    public FacebookProfile getUserProfile(String facebookId) {
        return graphApi.fetchObject(facebookId, FacebookProfile.class);
    }

    @Override
    public byte[] getUserProfileImage() {
        ;
        return getUserProfileImage("me", ImageType.NORMAL);
    }

    @Override
    public byte[] getUserProfileImage(String userId) {
        return getUserProfileImage(userId, ImageType.NORMAL);
    }

    @Override
    public byte[] getUserProfileImage(ImageType imageType) {
        ;
        return getUserProfileImage("me", imageType);
    }

    @Override
    public byte[] getUserProfileImage(String userId, ImageType imageType) {
        return graphApi.fetchImage(userId, "picture", imageType);
    }

    @Override
    public List<String> getUserPermissions() {
        JsonNode responseNode = getService().get(graphApi.getBaseUrl() + "me/permissions", JsonNode.class);
        return deserializePermissionsNodeToList(responseNode);
    }

    @Override
    public List<Reference> search(String query) {
        Map<String, String> queryMap = new HashMap();
        queryMap.put("q", query);
        queryMap.put("type", "user");
        return graphApi.fetchConnections("search", null, Reference.class, queryMap);
    }

    private List<String> deserializePermissionsNodeToList(JsonNode jsonNode) {
        JsonNode dataNode = jsonNode.get("data");
        List<String> permissions = new ArrayList<String>();
        for (Iterator<JsonNode> elementIt = dataNode.elements(); elementIt.hasNext(); ) {
            JsonNode permissionsElement = elementIt.next();
            for (Iterator<String> fieldNamesIt = permissionsElement.fieldNames(); fieldNamesIt.hasNext(); ) {
                permissions.add(fieldNamesIt.next());
            }
        }
        return permissions;
    }

}
