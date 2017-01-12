package com.rhc;

import io.vertx.core.AbstractVerticle;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class VertxApp extends AbstractVerticle {

	@Override
	public void start() {
		LocalDateTime time = LocalDateTime.now( ZoneId.of( ZoneId.SHORT_IDS.get("PST")));

		vertx.createHttpServer()
				.requestHandler(req -> req.response().end(String.format("Hello World from Vert.x %s!%n", time.toString())))
				.listen(8082);
	}

}