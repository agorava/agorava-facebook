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


package org.agorava.facebook;

import org.agorava.facebook.model.*;

import java.util.List;

/**
 * Defines operations for retrieving data about groups and group members.
 *
 * @author Craig Walls
 */
public interface GroupService {

    /**
     * Retrieve data for a specified group.
     *
     * @param groupId the ID of the group
     * @return a {@link Group} object
     * @throws ApiException if there is an error while communicating with Facebook.
     */
    Group getGroup(String groupId);

    /**
     * Retrieves a group's image as an array of bytes. Returns the image in Facebook's "normal" type.
     *
     * @param groupId the group ID
     * @return an array of bytes containing the group's image.
     * @throws ApiException if there is an error while communicating with Facebook.
     */
    byte[] getGroupImage(String groupId);

    /**
     * Retrieves a group's image as an array of bytes.
     *
     * @param groupId   the group ID
     * @param imageType the image type (eg., small, normal, large. square)
     * @return an array of bytes containing the group's image.
     * @throws ApiException if there is an error while communicating with Facebook.
     */
    byte[] getGroupImage(String groupId, ImageType imageType);

    /**
     * Retrieves the members of the specified group.
     *
     * @param groupId the ID of the group
     * @return a list of {@link Reference}s, one for each member of the group.
     * @throws ApiException                  if there is an error while communicating with Facebook.
     * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
     */
    List<GroupMemberReference> getMembers(String groupId);

    /**
     * Retrieves the profiles for the members of the specified group.
     *
     * @param groupId the ID of the group
     * @return a list of {@link FacebookProfile}s, one for each member of the group.
     * @throws ApiException                  if there is an error while communicating with Facebook.
     * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
     */
    List<FacebookProfile> getMemberProfiles(String groupId);

    /**
     * Retrieves a list of group memberships for the authenticated user. Requires "user_groups" permission.
     *
     * @return a list of {@link GroupMembership}s, one for each group the user is a member of.
     * @throws ApiException                  if there is an error while communicating with Facebook.
     * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
     * @throws InsufficientPermissionException
     *                                       if the user has not granted "user_groups" permission.
     */
    List<GroupMembership> getMemberships();

    /**
     * Retrieves a list of group memberships for a specific user. Requires "user_groups" or "friends_groups" permission.
     *
     * @param userId the user ID to retrieve memberships for.
     * @return a list of {@link GroupMembership}s, one for each group the user is a member of.
     * @throws ApiException                  if there is an error while communicating with Facebook.
     * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
     * @throws InsufficientPermissionException
     *                                       if the user has not granted "user_groups" or "friends_groups" permission.
     */
    List<GroupMembership> getMemberships(String userId);

    /**
     * Search for groups. Returns up to 25 groups matching the query.
     *
     * @param query the search query (e.g., "Spring User Group")
     * @return a list of {@link Group}s matching the search query
     * @throws ApiException if there is an error while communicating with Facebook.
     */
    List<Group> search(String query);

    /**
     * Search for groups.
     *
     * @param query  the search query (e.g., "Spring User Group")
     * @param offset the offset into the matching groups list
     * @param limit  the maximum number of groups to return
     * @return a list of {@link Group}s matching the search query
     * @throws ApiException if there is an error while communicating with Facebook.
     */
    List<Group> search(String query, int offset, int limit);

}
