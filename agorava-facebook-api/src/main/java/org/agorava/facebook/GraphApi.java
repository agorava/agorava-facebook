/*
 * Copyright 2013-2016 Agorava
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

package org.agorava.facebook;

import org.agorava.facebook.model.ImageType;

import java.util.List;
import java.util.Map;

/**
 * Defines low-level operations against Facebook's Graph API
 *
 * @author Craig Walls
 * @author Werner Keil
 */
public interface GraphApi {

    /**
     * Fetches an object, extracting it into the given Java type Requires appropriate permission to fetch the object.
     *
     * @param objectId the Facebook object's ID
     * @param type     the Java type to fetch
     * @return an Java object representing the requested Facebook object.
     */
    <T> T fetchObject(String objectId, Class<T> type);

    /**
     * Fetches an object, extracting it into the given Java type Requires appropriate permission to fetch the object.
     *
     * @param objectId        the Facebook object's ID
     * @param type            the Java type to fetch
     * @param queryParameters query parameters to include in the request
     * @return an Java object representing the requested Facebook object.
     */
    <T> T fetchObject(String objectId, Class<T> type, Map<String, String> queryParameters);

    /**
     * Fetches connections, extracting them into a collection of the given Java type Requires appropriate permission to fetch
     * the object connection.
     *
     * @param objectId       the ID of the object to retrieve the connections for.
     * @param connectionName the connection name.
     * @param type           the Java type of each connection.
     * @param fields         the fields to include in the response.
     * @return a list of Java objects representing the Facebook objects in the connections.
     */
    <T> List<T> fetchConnections(String objectId, String connectionName, Class<T> type, String... fields);

    /**
     * Fetches connections, extracting them into a collection of the given Java type Requires appropriate permission to fetch
     * the object connection.
     *
     * @param objectId        the ID of the object to retrieve the connections for.
     * @param connectionName  the connection name.
     * @param type            the Java type of each connection.
     * @param queryParameters query parameters to include in the request
     * @return a list of Java objects representing the Facebook objects in the connections.
     */
    <T> List<T> fetchConnections(String objectId, String connectionName, Class<T> type, Map<String, String> queryParameters);

    /**
     * Fetches an image as an array of bytes.
     *
     * @param objectId       the object ID
     * @param connectionName the connection name
     * @param imageType      the type of image to retrieve (eg., small, normal, large, or square)
     * @return an image as an array of bytes.
     */
    byte[] fetchImage(String objectId, String connectionName, ImageType imageType);

    /**
     * Publishes data to an object's connection. Requires appropriate permission to publish to the object connection.
     *
     * @param objectId       the object ID to publish to.
     * @param connectionName the connection name to publish to.
     * @param data           the data to publish to the connection.
     * @return the ID of the newly published object.
     */
    String publish(String objectId, String connectionName, Map<String, Object> data);

    /**
     * Publishes data to an object's connection. Requires appropriate permission to publish to the object connection. This
     * differs from publish() only in that it doesn't attempt to extract the ID from the response. This is because some publish
     * operations do not return an ID in the response.
     *
     * @param objectId       the object ID to publish to.
     * @param connectionName the connection name to publish to.
     * @param data           the data to publish to the connection.
     */
    void post(String objectId, String connectionName, Map<String, String> data);

    /**
     * Deletes an object. Requires appropriate permission to delete the object.
     *
     * @param objectId the object ID
     */
    void delete(String objectId);

    /**
     * Deletes an object connection. Requires appropriate permission to delete the object connection.
     *
     * @param objectId       the object ID
     * @param connectionName the connection name
     */
    void delete(String objectId, String connectionName);
    
    /**
     * Returns the API base URL
     *
     * @return the API base URL
     */
    public String getApiBaseUrl();
    
    static final String API_VERSION = "2.5";

 	static final String GRAPH_API_URL = "https://graph.facebook.com/v" + API_VERSION + "/";
}
