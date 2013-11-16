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

/**
 *
 */
package org.agorava.facebook.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.agorava.FacebookBaseService;
import org.agorava.api.exception.AgoravaException;
import org.agorava.api.service.StringUtils;
import org.agorava.facebook.GraphApi;
import org.agorava.facebook.model.ImageType;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Antoine Sabot-Durand
 */
public class GraphApiImpl extends FacebookBaseService implements GraphApi {

    @Inject
    private ObjectMapper objectMapper;

    @Override
    public <T> T fetchObject(String objectId, Class<T> type) {
        String uri = GRAPH_API_URL + objectId;
        return getService().get(uri, type);
    }

    @Override
    public <T> T fetchObject(String objectId, Class<T> type, Map<String, String> queryParameters) {
        String uri = buildUri(GRAPH_API_URL + objectId, queryParameters);
        return getService().get(uri, type);
    }

    @Override
    public <T> List<T> fetchConnections(String objectId, String connectionType, Class<T> type, String... fields) {
        Map<String, String> queryParameters = new HashMap();
        if (fields.length > 0) {
            String joinedFields = StringUtils.join(fields, ',');
            queryParameters.put("fields", joinedFields);
        }
        return fetchConnections(objectId, connectionType, type, queryParameters);
    }

    @Override
    public <T> List<T> fetchConnections(String objectId, String connectionType, Class<T> type,
                                        Map<String, String> queryParameters) {
        String connectionPath = connectionType != null && connectionType.length() > 0 ? "/" + connectionType : "";
        String uri = buildUri(GRAPH_API_URL + objectId + connectionPath, queryParameters);
        JsonNode dataNode = getService().get(uri, JsonNode.class);
        return deserializeDataList(dataNode.get("data"), type);
    }

    @Override
    public byte[] fetchImage(String objectId, String connectionType, ImageType type) {
        // String uri = GRAPH_API_URL + objectId + "/" + connectionType + "?type=" + type.toString().toLowerCase();
        // ResponseEntity<byte[]> response = getService().getForEntity(uri, byte[].class);
        // if (response.getStatusCode() == HttpStatus.FOUND) {
        // throw new UnsupportedOperationException(
        // "Attempt to fetch image resulted in a redirect which could not be followed. Add Apache HttpComponents HttpClient
        // to the classpath "
        // + "to be able to follow redirects.");
        // }
        // return response.getBody();
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    @SuppressWarnings("unchecked")
    public String publish(String objectId, String connectionType, Map<String, Object> data) {
        String uri = GRAPH_API_URL + objectId + "/" + connectionType;
        Map<String, Object> response = getService().post(uri, data, Map.class);
        return (String) response.get("id");
    }

    @Override
    public void post(String objectId, String connectionType, Map<String, String> data) {
        String uri = GRAPH_API_URL + objectId + "/" + connectionType;
        getService().post(uri, data, String.class);
    }

    @Override
    public void delete(String objectId) {
        Map<String, String> deleteRequest = new HashMap();
        deleteRequest.put("method", "delete");
        String uri = GRAPH_API_URL + objectId;
        getService().post(uri, deleteRequest, String.class);
    }

    @Override
    public void delete(String objectId, String connectionType) {
        Map<String, String> deleteRequest = new HashMap();
        deleteRequest.put("method", "delete");
        String uri = GRAPH_API_URL + objectId + "/" + connectionType;
        getService().post(uri, deleteRequest, String.class);
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> deserializeDataList(JsonNode jsonNode, final Class<T> elementType) {
        try {
            CollectionType listType = TypeFactory.defaultInstance().constructCollectionType(List.class, elementType);
            return (List<T>) objectMapper.readValue(jsonNode.textValue(), listType);
        } catch (IOException e) {
            throw new AgoravaException("Error deserializing data from Facebook: " + e.getMessage(), e);
        }
    }

}
