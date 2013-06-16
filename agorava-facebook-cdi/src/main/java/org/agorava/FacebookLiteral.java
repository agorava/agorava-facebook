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
package org.agorava;

import javax.enterprise.util.AnnotationLiteral;

/**
 * @author antoine
 */
public class FacebookLiteral extends AnnotationLiteral<Facebook> implements Facebook {

    private static final long serialVersionUID = 6670724512843515808L;
    public static Facebook INSTANCE = new FacebookLiteral();

}
