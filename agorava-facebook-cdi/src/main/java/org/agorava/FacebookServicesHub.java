/*
 * Copyright 2012 Agorava
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

import java.lang.annotation.Annotation;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.agorava.core.api.event.OAuthComplete;
import org.agorava.core.api.event.SocialEvent.Status;
import org.agorava.core.cdi.AbstractSocialNetworkServicesHub;
import org.agorava.facebook.impl.UserServiceImpl;
import org.jboss.solder.logging.Logger;

/**
 * @author Antoine Sabot-Durand
 */
public class FacebookServicesHub extends AbstractSocialNetworkServicesHub {

    @Inject
    Instance<FacebookBaseService> services;

    @Inject
    Logger log;

    @Override
    public Annotation getQualifier() {
        return FacebookLiteral.INSTANCE;
    }

    public void initMyProfile(@Observes @Facebook OAuthComplete oauthComplete) {
        log.debug("Initializing Facebook profile");
        if (oauthComplete.getStatus() == Status.SUCCESS)
            oauthComplete.getEventData().setUserProfile(services.select(UserServiceImpl.class).get().getUserProfile());
    }

    @Override
    public String getVerifierParamName() {
        return "code";
    }

}
