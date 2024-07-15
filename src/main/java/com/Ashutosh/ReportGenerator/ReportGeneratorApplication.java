package com.Ashutosh.ReportGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ReportGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportGeneratorApplication.class, args);
	}

}
