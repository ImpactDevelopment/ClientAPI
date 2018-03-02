/*
 * Copyright 2018 ImpactDevelopment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package clientapi.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Basic label for fields. Allows them to have a name,
 * id, parent field, description, and any aliases.
 *
 * @author Brady
 * @since 2/11/2017 12:00 PM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Label {

    /**
     * Should return the name of the field or a simplified
     * descriptor of the field's function.
     *
     * @return The name
     */
    String name();

    /**
     * Returns the parent field's id. If the value
     * has a length of 0, that is an indication
     * that there isn't a parent field.
     *
     * @return The parent field id
     */
    String parent() default "";

    /**
     * Returns the ID of the marked field. There should only
     * be one field with the given ID per class.
     *
     * @return The ID of the field
     */
    String id();

    /**
     * Returns a description of the marked field. Should
     * be used to explain the usage of whatever that
     * field is responsible for.
     *
     * @return The description
     */
    String description();
}
