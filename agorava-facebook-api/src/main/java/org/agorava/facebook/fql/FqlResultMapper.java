/*
 * Copyright 2013-2014 Agorava
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

package org.agorava.facebook.fql;


/**
 * An interface used by  {@link FqlService} to map FQL results to objects of a specific type, on a per-object basis.
 * Roughly analogous to a RowMapper used with Spring's JdbcTemplate.
 *
 * @param <T> the type of object to map FQL result data to.
 * @author habuma
 * @author Werner Keil
 * 
 * @deprecated see https://developers.facebook.com/docs/reference/fql/
 */
public interface FqlResultMapper<T> {

    T mapObject(FqlResult objectValues);

}
