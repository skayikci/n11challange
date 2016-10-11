package com.n11;

import com.n11.config.RepositoryConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.n11.*")
@AutoConfigureAfter(value = {RepositoryConfig.class})
public class ConferenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceApplication.class, args);
	}
}
