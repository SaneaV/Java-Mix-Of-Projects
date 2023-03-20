package com.example.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = {AppDatasourceConfig.class})
public class AppJdbcDatasourceConfigTest {

  @Test
  void shouldCreateJdbcDatasource() {

  }
}
