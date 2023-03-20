package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.naming.NamingException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;

public class AppJndiDatasourceConfigInitialContextTest {

  private final Environment environment = mock(Environment.class);
  private final AppDatasourceConfig appDatasourceConfig = new AppDatasourceConfig(environment);

  @Test
  public void shouldCreateJndiDatasource() throws NamingException {
    final String jndiName = "java:comp/env/jdbc/myDatasource";

    System.setProperty("java.naming.factory.initial", "com.example.app.ContextFactoryMockConfig");

    when(environment.getProperty(anyString())).thenReturn(jndiName);

    final DataSource result = appDatasourceConfig.appJndiDatasource();
    assertThat(result).isInstanceOf(DataSource.class);
  }
}
