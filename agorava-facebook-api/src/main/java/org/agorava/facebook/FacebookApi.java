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
package org.agorava.facebook;

import static org.agorava.facebook.GraphApi.GRAPH_API_URL;
import static org.agorava.facebook.GraphApi.API_VERSION;

import org.agorava.api.oauth.application.OAuthAppSettings;
import org.agorava.api.service.OAuthEncoder;
import org.agorava.api.service.Preconditions;
import org.agorava.spi.ProviderConfigOauth20;
import org.agorava.spi.ProviderConfigOauth20Final;

/**
 * @author Antoine Sabot-Durand
 * @author Werner Keil
 */

@Facebook
public class FacebookApi extends ProviderConfigOauth20Final {

    private static final String AUTHORIZE_URL = "https://www.facebook.com/v" + API_VERSION + 
    		"/dialog/oauth?client_id=%s&client_secret=%s&redirect_uri=%s";

    private static final String SCOPED_AUTHORIZE_URL = AUTHORIZE_URL + "&scope=%s";
    
    private static final String MEDIA_NAME = "Facebook";

    @Override
    public String getAccessTokenEndpoint() {
        return GRAPH_API_URL + "oauth/access_token";
    }

    @Override
    public String getAuthorizationUrl(OAuthAppSettings config) {
        Preconditions.checkValidUrl(config.getCallback(), "Must provide a valid url as callback. Facebook does not support " +
                "OOB");

        // Append scope if present
        if (config.hasScope()) {
            return String.format(SCOPED_AUTHORIZE_URL, config.getApiKey(), config.getApiSecret(), 
            		OAuthEncoder.encode(config.getCallback()), OAuthEncoder.encode(config.getScope()));
        } else {
            return String.format(AUTHORIZE_URL, config.getApiKey(), config.getApiSecret(), 
            		OAuthEncoder.encode(config.getCallback()));
        }
    }

    @Override
    public String getProviderName() {
        return MEDIA_NAME;
    }
}
