package com.ascenda.hotels_data_merge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class HotelsDataMergeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelsDataMergeApplication.class, args);
	}

}
