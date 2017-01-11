package com.rhc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAutoConfiguration
@Controller
public class SpringApp {

	long serviceID = System.currentTimeMillis();

	public static void main(String[] args) {
		SpringApplication.run(SpringApp.class, args);

	}

	@RequestMapping("/")
	@ResponseBody
	String helloWorld(){
		String vertxUrl = System.getProperty("vertxUrl");
		if ( vertxUrl == null || vertxUrl.isEmpty() ){
			vertxUrl = "http://localhost:8081";
		}
		RestTemplate rest = new RestTemplate();
		String response = rest.getForObject( vertxUrl, String.class );
		return String.format("Hello World from Spring %d. I'm going to call Vert.x at %s. Here is Vert.x's response: %n%n%s", serviceID, vertxUrl, response);
	}
}
