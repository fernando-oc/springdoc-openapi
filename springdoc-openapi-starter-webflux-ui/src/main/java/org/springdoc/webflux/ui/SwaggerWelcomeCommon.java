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

package org.springdoc.webflux.ui;

import java.net.URI;
import java.util.Map;

import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.ui.AbstractSwaggerWelcome;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * The type Swagger welcome common.
 *
 * @author bnasslashen
 */
public abstract class SwaggerWelcomeCommon extends AbstractSwaggerWelcome {

	/**
	 * The Web jars prefix url.
	 */
	protected String webJarsPrefixUrl;


	/**
	 * Instantiates a new Abstract swagger welcome.
	 *
	 * @param swaggerUiConfig           the swagger ui config
	 * @param springDocConfigProperties the spring doc config properties
	 */
	protected SwaggerWelcomeCommon(SwaggerUiConfigProperties swaggerUiConfig, SpringDocConfigProperties springDocConfigProperties) {
		super(swaggerUiConfig, springDocConfigProperties);
		this.webJarsPrefixUrl = springDocConfigProperties.getWebjars().getPrefix();
	}

	/**
	 * Redirect to ui mono.
	 *
	 * @param request  the request
	 * @param response the response
	 * @return the mono
	 */
	protected Mono<Void> redirectToUi(ServerHttpRequest request, ServerHttpResponse response) {
		SwaggerUiConfigParameters swaggerUiConfigParameters = new SwaggerUiConfigParameters(swaggerUiConfig);
		this.buildFromCurrentContextPath(swaggerUiConfigParameters, request);
		String sbUrl = this.buildUrl(swaggerUiConfigParameters.getContextPath(), swaggerUiConfigParameters.getUiRootPath() + springDocConfigProperties.getWebjars().getPrefix() + getSwaggerUiUrl());
		UriComponentsBuilder uriBuilder = getUriComponentsBuilder(swaggerUiConfigParameters, sbUrl);
		response.setStatusCode(HttpStatus.FOUND);
		response.getHeaders().setLocation(URI.create(uriBuilder.build().encode().toString()));
		return response.setComplete();
	}

	/**
	 * Gets swagger ui config.
	 *
	 * @param request the request
	 * @return the swagger ui config
	 */
	protected Map<String, Object> getSwaggerUiConfig(ServerHttpRequest request) {
		SwaggerUiConfigParameters swaggerUiConfigParameters = new SwaggerUiConfigParameters(swaggerUiConfig);
		this.buildFromCurrentContextPath(swaggerUiConfigParameters, request);
		return swaggerUiConfigParameters.getConfigParameters();
	}

	/**
	 * From current context path string.
	 *
	 * @param swaggerUiConfigParameters the swagger ui config parameters
	 * @param request                   the request
	 * @return the string
	 */
	void buildFromCurrentContextPath(SwaggerUiConfigParameters swaggerUiConfigParameters, ServerHttpRequest request) {
		super.init(swaggerUiConfigParameters);
		swaggerUiConfigParameters.setContextPath(request.getPath().contextPath().value());
		String url = UriComponentsBuilder.fromHttpRequest(request).toUriString();
		if (!AntPathMatcher.DEFAULT_PATH_SEPARATOR.equals(request.getPath().toString()))
			url = url.replace(request.getPath().toString(), "");
		buildConfigUrl(swaggerUiConfigParameters, UriComponentsBuilder.fromUriString(url));
	}

}
