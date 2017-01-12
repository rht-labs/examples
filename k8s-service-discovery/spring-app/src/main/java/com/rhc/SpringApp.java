package com.rhc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootApplication
@EnableAutoConfiguration
@Controller
public class SpringApp {
	LocalDateTime time = LocalDateTime.now( ZoneId.of( ZoneId.SHORT_IDS.get("PST")));

	public static void main(String[] args) {
		SpringApplication.run(SpringApp.class, args);

	}

	@RequestMapping("/")
	@ResponseBody
	String helloWorld(){
		String vertxUrl = System.getProperty("SERVICE_URL");
		if ( vertxUrl == null || vertxUrl.isEmpty() ){
			vertxUrl = "http://localhost:8082";
		}
		RestTemplate rest = new RestTemplate();
		String response = rest.getForObject( vertxUrl, String.class );
		return String.format("Hello World from Spring %s. I'm going to call Vert.x at %s. Here is Vert.x's response: %n%n%s", time.toString(), vertxUrl, response);
	}
}
