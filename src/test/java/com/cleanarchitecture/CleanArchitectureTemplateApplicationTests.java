package com.cleanarchitecture;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {
		CleanArchitectureTemplateApplication.class,
		TestConfig.class
})
@ActiveProfiles("test")
@DisplayName("Clean Architecture Application Context Tests")
class CleanArchitectureTemplateApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	@DisplayName("Context Loads Successfully")
	void contextLoads() {
		assertThat(applicationContext).isNotNull();
	}
}
