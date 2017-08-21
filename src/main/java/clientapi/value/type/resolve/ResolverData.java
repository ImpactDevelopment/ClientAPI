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

package clientapi.value.type.resolve;

/**
 * Holds the data for a TypeResolver and its resolvable types
 *
 * @author Brady
 * @since 5/22/2017 5:14 PM
 */
public final class ResolverData {

	/**
	 * Resolver
	 */
	private final TypeResolver resolver;

	/**
	 * Array of the resolvable types
	 */
	private final Class<?>[] resolvableTypes;

	private ResolverData(TypeResolver resolver, Class<?>... resolvableTypes) {
		this.resolver = resolver;
		this.resolvableTypes = resolvableTypes;
	}

	/**
	 * @return The type resolver belonging to this object
	 */
	public final TypeResolver getResolver() {
		return this.resolver;
	}

	/**
	 * @return The types supported by the resolver
	 */
	public final Class<?>[] getResolvableTypes() {
		return this.resolvableTypes;
	}

	/**
	 * Checks a type to see if it is compatible with the resolver belonging to
	 * this data object.
	 *
	 * @param type The type being checked
	 * @return Whether or not the specified type is compatible
	 */
	public final boolean isResolvable(Class<?> type) {
		// If there aren't any defined types, accept anything
		if (resolvableTypes.length == 0) return true;

		// Otherwise, find a type that matches
		for (Class<?> rType : resolvableTypes)
			if (rType == type) return true;

		return false;
	}

	/**
	 * Instiates a new {@code ResolverData} type
	 *
	 * @param resolver The resolver for the target type
	 * @param resolvableTypes The supported types for the resolver
	 * @return The new ResolverData
	 */
	public static ResolverData create(TypeResolver resolver,
	    Class<?>... resolvableTypes) {
		return new ResolverData(resolver, resolvableTypes);
	}
}
