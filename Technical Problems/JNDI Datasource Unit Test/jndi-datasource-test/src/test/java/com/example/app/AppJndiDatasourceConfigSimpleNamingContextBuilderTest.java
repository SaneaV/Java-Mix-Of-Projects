package com.example.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.naming.NamingException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.env.Environment;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class AppJndiDatasourceConfigSimpleNamingContextBuilderTest {

  private final Environment environment = mock(Environment.class);
  private final AppDatasourceConfig appDatasourceConfig = new AppDatasourceConfig(environment);

  @Test
  public void shouldCreateJndiDatasource() throws NamingException {
    final String jndiName = "java:comp/env/jdbc/myDatasource";
    final DataSource mockDatasource = mock(DataSource.class);

    final SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
    builder.bind(jndiName, mockDatasource);
    builder.activate();

    when(environment.getProperty(anyString())).thenReturn(jndiName);

    final DataSource result = appDatasourceConfig.appJndiDatasource();
    assertThat(result).isEqualTo(mockDatasource);
  }
}
