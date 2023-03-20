package com.example.app;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.sql.DataSource;

public class ContextFactoryMockConfig implements InitialContextFactory {

  private static InitialContext initialContext;

  public InitialContext setup() throws NamingException {
    final DataSource mockDatasource = mock(DataSource.class);

    initialContext = mock(InitialContext.class);
    when(initialContext.lookup(anyString())).thenReturn(mockDatasource);
    return initialContext;
  }

  @Override
  public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
    if (initialContext == null) {
      initialContext = setup();
    }
    return initialContext;
  }
}
