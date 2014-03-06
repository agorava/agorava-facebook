/*
 * Copyright 2014 Agorava
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

import org.agorava.facebook.model.FacebookProfile;
import org.agorava.facebook.model.ImageType;
import org.agorava.facebook.model.Reference;
import org.agorava.spi.UserProfileService;

import java.util.List;

/**
 * Operations on User API
 *
 * @author Werner Keil
 */
public interface UserService extends UserProfileService {

    /**
     * Retrieves the profile for the authenticated user.
     *
     * @return the user's profile information.
     * @throws AgoravaException                  if there is an error while communicating with Facebook.
     * @throws OAuthParametersMissingException if Facebook connection was not created with an access token.
     */
    FacebookProfile getUserProfile();

    /**
     * Retrieves the profile for the specified user.
     *
     * @param userId the Facebook user ID to retrieve profile data for.
     * @return the user's profile information.
     * @throws AgoravaException if there is an error while communicating with Facebook.
     */
    FacebookProfile getUserProfile(String userId);

    /**
     * Retrieves the user's profile image. Returns the image in Facebook's "normal" type.
     *
     * @return an array of bytes containing the user's profile image.
     * @throws AgoravaException                  if there is an error while communicating with Facebook.
     * @throws OAuthParametersMissingException if Facebook connection was not created with an access token.
     */
    byte[] getUserProfileImage();

    /**
     * Retrieves the user's profile image. Returns the image in Facebook's "normal" type.
     *
     * @param userId the Facebook user ID.
     * @return an array of bytes containing the user's profile image.
     * @throws AgoravaException if there is an error while communicating with Facebook.
     */
    byte[] getUserProfileImage(String userId);

    /**
     * Retrieves the user's profile image.
     *
     * @param imageType the image type (eg., small, normal, large. square)
     * @return an array of bytes containing the user's profile image.
     * @throws AgoravaException                  if there is an error while communicating with Facebook.
     * @throws OAuthParametersMissingException if Facebook connection was not created with an access token.
     */
    byte[] getUserProfileImage(ImageType imageType);

    /**
     * Retrieves the user's profile image.
     *
     * @param userId    the Facebook user ID.
     * @param imageType the image type (eg., small, normal, large. square)
     * @return an array of bytes containing the user's profile image.
     * @throws AgoravaException if there is an error while communicating with Facebook.
     */
    byte[] getUserProfileImage(String userId, ImageType imageType);

    /**
     * Retrieves a list of permissions that the application has been granted for the authenticated user.
     *
     * @return the permissions granted for the user.
     * @throws AgoravaException                  if there is an error while communicating with Facebook.
     * @throws OAuthParametersMissingException if Facebook connection was not created with an access token.
     */
    List<String> getUserPermissions();

    /**
     * Searches for users.
     *
     * @param query the search query (e.g., "Michael Scott")
     * @return a list of {@link Reference}s, each representing a user who matched the given query.
     * @throws AgoravaException                  if there is an error while communicating with Facebook.
     * @throws OAuthParametersMissingException if Facebook connection was not created with an access token.
     */
    List<Reference> search(String query);
}
