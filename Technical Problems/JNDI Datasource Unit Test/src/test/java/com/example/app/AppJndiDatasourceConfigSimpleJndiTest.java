package com.example.app;

import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Hashtable;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;

public class AppJndiDatasourceConfigSimpleJndiTest {

  private final Environment environment = mock(Environment.class);
  private final AppDatasourceConfig appDatasourceConfig = new AppDatasourceConfig(environment);

  @Test
  public void shouldCreateJndiDatasource() throws NamingException {
    final String jndiName = "java:comp/env/jdbc/myDatasource";
    System.setProperty(INITIAL_CONTEXT_FACTORY, "org.osjava.sj.MemoryContextFactory");

    Hashtable<String, String> env = new Hashtable<>();
    env.put("org.osjava.sj.jndi.shared", "true");

    InitialContext ic = new InitialContext(env);

    ic.createSubcontext("java:/comp/env/jdbc");

    JdbcDataSource jdbcDataSource = new JdbcDataSource();
    jdbcDataSource.setUrl("jdbc:hsqldb:hsql://localhost/xdb");
    jdbcDataSource.setUser("sa");
    jdbcDataSource.setPassword("");

    ic.bind(jndiName, jdbcDataSource);
    when(environment.getProperty(anyString())).thenReturn(jndiName);

    final DataSource result = appDatasourceConfig.appJndiDatasource();

    assertThat(result).isInstanceOf(DataSource.class);
  }
}
