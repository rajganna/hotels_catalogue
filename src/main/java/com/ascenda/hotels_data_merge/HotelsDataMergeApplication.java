package com.ascenda.hotels_data_merge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HotelsDataMergeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelsDataMergeApplication.class, args);
	}

}
