package com.example.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(properties = "app.datasource.enabled=false")
class JndiDatasourceUnitTestApplicationTests {

	@Test
	void contextLoads() {
	}

}
