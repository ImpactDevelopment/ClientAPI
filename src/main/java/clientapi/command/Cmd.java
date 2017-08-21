/*
 * Copyright 2017 ImpactDevelopment
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

package clientapi.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Brady
 * @since 5/31/2017 8:55 AM
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Cmd {

	/**
	 * Provides the command class with the headers
	 *
	 * @see ICommand#headers()
	 * @return Command headers
	 */
	String[] headers();

	/**
	 * Provides the command class with the description
	 *
	 * @see ICommand#description()
	 * @return Command description
	 */
	String description();

	/**
	 * Provides the command class with the syntax
	 *
	 * @see ICommand#syntax()
	 * @return Command syntax
	 */
	String[] syntax() default {};
}
