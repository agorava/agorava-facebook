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
package org.agorava;

import org.agorava.core.api.oauth.OAuthService;
import org.agorava.core.spi.TierService;
import org.agorava.facebook.Facebook;

import javax.inject.Inject;

/**
 * @author Antoine Sabot-Durand
 */
public class FacebookBaseService extends TierService {

    @Inject
    @Facebook
    private OAuthService service;

    @Override
    public OAuthService getService() {
        return service;
    }
}
