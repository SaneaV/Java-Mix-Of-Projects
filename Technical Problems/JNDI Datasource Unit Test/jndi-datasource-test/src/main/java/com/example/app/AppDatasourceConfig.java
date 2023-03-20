package com.example.app;

import static java.util.Objects.requireNonNull;

import javax.naming.NamingException;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(value = "app.datasource.enabled", havingValue = "true")
public class AppDatasourceConfig {

  private final Environment environment;

  @Bean("appDatasource")
  @ConditionalOnProperty(value = "app.datasource.type", havingValue = "JNDI")
  public DataSource appJndiDatasource() throws NamingException {
    final String jndiName = environment.getProperty("app.datasource.jdniName");
    requireNonNull(jndiName, "Datasource JNDI name can not be null");
    return (DataSource) new JndiTemplate().lookup(jndiName);
  }

  @Bean("appDatasource")
  @ConfigurationProperties(prefix = "app.datasource")
  @ConditionalOnProperty(value = "app.datasource.type", havingValue = "JDBC")
  public DataSource appJdbcDatasource() {
    return DataSourceBuilder.create().build();
  }
}
