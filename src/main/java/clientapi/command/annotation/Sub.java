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

package clientapi.command.annotation;

import clientapi.command.executor.ExecutionContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;

/**
 * Annotation for marking sub-command argument handler methods.
 * Method parameters should begin with a {@link ExecutionContext}
 * parameter, followed by a reflection of the target sub-command
 * arguments. Usage is as follows...
 *
 * {@link Optional} may be used to mark arguments
 * as "optional", but only if the argument is the last.
 *
 * @author Brady
 * @since 10/17/2017
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Sub {

    /**
     * The handles for this sub-command. If no handles are
     * defined it is implied that the handle method should
     * take in anything if it matches the target arguments,
     * regardless of the first argument (Defining argument),
     * and there shouldn't be any other handle methods in the class
     * that have handles.
     *
     * @return Handles for the sub command.
     */
    String[] handles() default {};

    /**
     * Returns the names of the arguments. Will be automatically
     * filled if not specified. Intended for use with obfuscation.
     *
     * @return Arguments for the sub command.
     */
    String[] arguments() default {};

    /**
     * A basic description of the usage for this
     * sub-command argument handler. If undefined,
     * any reference to this sub-command's description
     * will point back to the parent command's description.
     *
     * @return Description of the sub command
     */
    String description() default "";
}
