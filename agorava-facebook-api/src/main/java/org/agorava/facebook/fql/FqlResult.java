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

package org.agorava.facebook.fql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Represents a single result item from an FQL query.
 * Given to an {@link FqlResultMapper}, in a way that is analogous to how a ResultSet is given to a RowMapper in Spring's JdbcTemplate.
 *
 * @author habuma
 * @author Werner Keil
 */
public class FqlResult {

    private final Map<String, Object> resultMap;

    /**
     * Constructs an FqlResult instance from a map.
     */
    public FqlResult(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    /**
     * Returns the value of the identified field as a String.
     *
     * @param fieldName the name of the field
     * @return the value of the field as a String
     */
    public String getString(String fieldName) {
        return resultMap.containsKey(fieldName) ? String.valueOf(resultMap.get(fieldName)) : null;
    }

    /**
     * Returns the value of the identified field as an Integer.
     *
     * @param fieldName the name of the field
     * @return the value of the field as an Integer
     * @throws FqlException if the field cannot be expressed as an Integer
     */
    public Integer getInteger(String fieldName) {
        try {
            return resultMap.containsKey(fieldName) ? Integer.valueOf(String.valueOf(resultMap.get(fieldName))) : null;
        } catch (NumberFormatException e) {
            throw new FqlException("Field '" + fieldName + "' is not a number.", e);
        }
    }

    /**
     * Returns the value of the identified field as a Long.
     *
     * @param fieldName the name of the field
     * @return the value of the field as a Long
     * @throws FqlException if the field cannot be expressed as an Long
     */
    public Long getLong(String fieldName) {
        try {
            return resultMap.containsKey(fieldName) ? Long.valueOf(String.valueOf(resultMap.get(fieldName))) : null;
        } catch (NumberFormatException e) {
            throw new FqlException("Field '" + fieldName + "' is not a number.", e);
        }
    }

    /**
     * Returns the value of the identified field as a Float.
     *
     * @param fieldName the name of the field
     * @return the value of the field as a Float
     * @throws FqlException if the field cannot be expressed as an Float
     */
    public Float getFloat(String fieldName) {
        try {
            return resultMap.containsKey(fieldName) ? Float.valueOf(String.valueOf(resultMap.get(fieldName))) : null;
        } catch (NumberFormatException e) {
            throw new FqlException("Field '" + fieldName + "' is not a number.", e);
        }
    }

    /**
     * Returns the value of the identified field as a Boolean.
     *
     * @param fieldName the name of the field
     * @return the value of the field as a Boolean
     */
    public Boolean getBoolean(String fieldName) {
        return resultMap.containsKey(fieldName) ? Boolean.valueOf(String.valueOf(resultMap.get(fieldName))) : null;
    }

    /**
     * Returns the value of the identified field as a Date.
     * Time fields returned from an FQL query are expressed in terms of seconds since midnight, January 1, 1970 UTC.
     *
     * @param fieldName the name of the field
     * @return the value of the field as a Date
     * @throws FqlException if the field's value cannot be expressed as a long value from which a Date object can be constructed.
     */
    public Date getTime(String fieldName) {
        try {
            long timeInMilliseconds = Long.valueOf(String.valueOf(resultMap.get(fieldName))) * 1000;
            return resultMap.containsKey(fieldName) ? new Date(timeInMilliseconds) : null;
        } catch (NumberFormatException e) {
            throw new FqlException("Field '" + fieldName + "' is not a time.", e);
        }
    }

    /**
     * Returns the value of the identified field as a simple Object.
     *
     * @param fieldName the name of the field
     * @return the value of the field as an Object
     */
    public Object getObject(String fieldName) {
        return resultMap.get(fieldName);
    }

    /**
     * Returns the value of the identified field as an object mapped by a given {@link FqlResultMapper}.
     *
     * @param fieldName the name of the field
     * @param mapper    an {@link FqlResultMapper} used to map the object date into a specific type.
     * @return the value of the field as an object of a type the same as the parameterized type of the given {@link FqlResultMapper}.
     * @throws FqlException if the value of the field is not a nested object.
     */
    public <T> T getObject(String fieldName, FqlResultMapper<T> mapper) {
        if (!resultMap.containsKey(fieldName)) {
            return null;
        }
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> value = (Map<String, Object>) resultMap.get(fieldName);
            return mapper.mapObject(new FqlResult(value));
        } catch (ClassCastException e) {
            throw new FqlException("Field '" + fieldName + "' is not an object.", e);
        }
    }

    /**
     * Returns the value of the identified field as an object mapped by a given {@link FqlResultMapper}.
     *
     * @param fieldName the name of the field
     * @param mapper    an {@link FqlResultMapper} used to map the object date into a specific type.
     * @return the value of the field as list of objects whose type is the same as the parameterized type of the given {@link FqlResultMapper}.
     * @throws FqlException if the value of the field is not a list.
     */
    public <T> List<T> getList(String fieldName, FqlResultMapper<T> mapper) {
        if (!resultMap.containsKey(fieldName)) {
            return null;
        }
        try {
            List<T> response = new ArrayList<T>();
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> arrayItems = (List<Map<String, Object>>) resultMap.get(fieldName);
            for (Map<String, Object> arrayItem : arrayItems) {
                response.add(mapper.mapObject(new FqlResult(arrayItem)));
            }
            return response;
        } catch (ClassCastException e) {
            throw new FqlException("Field '" + fieldName + "' is not a list.", e);
        }
    }

}
