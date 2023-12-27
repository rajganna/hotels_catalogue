package com.ascenda.hotels_data_merge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestHotelsDataMergeApplication {

	public static void main(String[] args) {
		SpringApplication.from(HotelsDataMergeApplication::main).with(TestHotelsDataMergeApplication.class).run(args);
	}

}
