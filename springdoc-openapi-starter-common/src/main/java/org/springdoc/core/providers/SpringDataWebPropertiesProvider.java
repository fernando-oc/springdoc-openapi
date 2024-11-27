/*
 *
 *  *
 *  *  *
 *  *  *  *
 *  *  *  *  *
 *  *  *  *  *  * Copyright 2019-2024 the original author or authors.
 *  *  *  *  *  *
 *  *  *  *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  *  *  *  * you may not use this file except in compliance with the License.
 *  *  *  *  *  * You may obtain a copy of the License at
 *  *  *  *  *  *
 *  *  *  *  *  *      https://www.apache.org/licenses/LICENSE-2.0
 *  *  *  *  *  *
 *  *  *  *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  *  *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  *  *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  *  *  *  * See the License for the specific language governing permissions and
 *  *  *  *  *  * limitations under the License.
 *  *  *  *  *
 *  *  *  *
 *  *  *
 *  *
 *  
 */
package org.springdoc.core.providers;

import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

/**
 * The type Spring data web properties provider.
 *
 * @author bnasslahsen
 */
public class SpringDataWebPropertiesProvider {


	/**
	 * The Optional spring data web properties.
	 */
	private final Optional<SpringDataWebProperties> optionalSpringDataWebProperties;

	/**
	 * Instantiates a new Spring data web properties provider.
	 *
	 * @param optionalSpringDataWebProperties the optional spring data web properties
	 */
	public SpringDataWebPropertiesProvider(Optional<SpringDataWebProperties> optionalSpringDataWebProperties) {
		this.optionalSpringDataWebProperties = optionalSpringDataWebProperties;
	}

	/**
	 * Gets spring data web properties.
	 *
	 * @return the spring data web properties
	 */
	public SpringDataWebProperties getSpringDataWebProperties() {
		return optionalSpringDataWebProperties.orElse(null);
	}

	/**
	 * Is spring data web properties present boolean.
	 *
	 * @return the boolean
	 */
	public boolean isSpringDataWebPropertiesPresent() {
		return optionalSpringDataWebProperties.isPresent();
	}
}
