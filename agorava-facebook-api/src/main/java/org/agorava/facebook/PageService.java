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

import org.agorava.facebook.model.Account;
import org.agorava.facebook.model.FacebookLink;
import org.agorava.facebook.model.Page;
import org.agorava.facebook.model.PageAdministrationException;

import java.util.List;

/**
 * Interface defining operations that can be performed on a Facebook pages.
 *
 * @author Craig Walls
 */
public interface PageService {

    /**
     * Retrieves data for a page.
     *
     * @param pageId the page ID.
     * @return a {@link Page}
     */
    Page getPage(String pageId);

    /**
     * Checks whether the logged-in user for this session is an admin of the page with the given page ID. Requires
     * "manage_pages" permission.
     *
     * @param pageId the page ID
     * @return true if the authenticated user is an admin of the specified page.
     * @throws AgoravaException                  if there is an error while communicating with Facebook.
     * @throws InsufficientPermissionException
     *                                       if the user has not granted "manage_pages" permission.
     * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
     */
    boolean isPageAdmin(String pageId);

    /**
     * Retrieves a list of Account objects for the pages that the authenticated user is an administrator. Requires
     * "manage_pages" permission.
     */
    List<Account> getAccounts();

    /**
     * Posts a message to a page's feed as a page administrator. Requires that the application is granted "manage_pages"
     * permission and that the authenticated user be an administrator of the page. To post to the page's feed as the
     * authenticated user, use {@link FeedService#post(String, String)} instead.
     *
     * @param pageId  the page ID
     * @param message the message to post
     * @return the ID of the new feed entry
     * @throws AgoravaException                  if there is an error while communicating with Facebook.
     * @throws InsufficientPermissionException
     *                                       if the user has not granted "manage_pages" permission.
     * @throws PageAdministrationException   if the user is not a page administrator.
     * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
     */
    String post(String pageId, String message);

    /**
     * Posts a link to the page's feed as a page administrator. Requires that the application is granted "manage_pages"
     * permission and that the authenticated user be an administrator of the page. To post a link to the page's feed as the
     * authenticated user, use {@link FeedService#postLink(String, String, FacebookLink)} instead.
     *
     * @param pageId  the page ID
     * @param message a message to send with the link.
     * @param link    the link details
     * @return the ID of the new feed entry.
     * @throws AgoravaException                  if there is an error while communicating with Facebook.
     * @throws InsufficientPermissionException
     *                                       if the user has not granted "manage_pages" permission.
     * @throws PageAdministrationException   if the user is not a page administrator.
     * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
     */
    String post(String pageId, String message, FacebookLink link);

    /**
     * Posts a photo to a page's album as the page administrator. Requires that the application is granted "manage_pages"
     * permission and that the authenticated user be an administrator of the page.
     *
     * @param pageId the page ID
     * @param albumId the album ID
     * @param photo A {@link Resource} for the photo data. The given Resource must implement the getFilename() method (such as
     *        {@link FileSystemResource} or {@link ClassPathResource}).
     * @return the ID of the photo.
     * @throws AgoravaException if there is an error while communicating with Facebook.
     * @throws InsufficientPermissionException if the user has not granted "manage_pages" permission.
     * @throws PageAdministrationException if the user is not a page administrator.
     * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
     */
    // TODO:String postPhoto(String pageId, String albumId, Resource photo);

    /**
     * Posts a photo to a page's album as the page administrator. Requires that the application is granted "manage_pages"
     * permission and that the authenticated user be an administrator of the page.
     *
     * @param pageId the page ID
     * @param albumId the album ID
     * @param photo A {@link Resource} for the photo data. The given Resource must implement the getFilename() method (such as
     *        {@link FileSystemResource} or {@link ClassPathResource}).
     * @param caption A caption describing the photo.
     * @return the ID of the photo.
     * @throws AgoravaException if there is an error while communicating with Facebook.
     * @throws InsufficientPermissionException if the user has not granted "manage_pages" permission.
     * @throws PageAdministrationException if the user is not a page administrator.
     * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
     */
    // TODO:String postPhoto(String pageId, String albumId, Resource photo, String caption);

}
