/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rhc;


import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class EEApp {


    @GET
    public String getHelloWorldJSON() throws Exception {

        String springUrl = System.getProperty("spring.url");
        if (springUrl == null || springUrl.isEmpty()) {
            springUrl = "http://localhost:8082";
        }

        ClientRequest request = new ClientRequest(springUrl);

        // Request has been made, now let's get the response
        ClientResponse<String> response = request.get(String.class);
        String value = response.getEntity();

        // Check the HTTP status of the request
        // HTTP 200 indicates the request is OK
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed request with HTTP status: " + response.getStatus());
        }

        return String.format("Hello World from EE! I'm going to call Spring at %s. Here is Spring's response:%n%n%s", springUrl, value);
    }

}
