package com.transport.drones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.MapInfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DronesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DronesApplication.class, args);
	}
	@Bean
	InfoContributor getInfoContributor() {
		Map<String, Object> details = new HashMap<>();
		details.put("nameApp", "Drones");
		details.put("developer", "Elena Moreva");
		Map<String, Object> wrapper = new HashMap<>();
		wrapper.put("info", details);
		return new MapInfoContributor(wrapper);
	}
}
