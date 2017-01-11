package com.rhc;

import io.vertx.core.AbstractVerticle;

public class VertxApp extends AbstractVerticle {

	@Override
	public void start() {
		long serviceID = System.currentTimeMillis();

		vertx.createHttpServer()
				.requestHandler(req -> req.response().end(String.format("Hello World from Vert.x %d!%n", serviceID)))
				.listen(8081);
	}

}