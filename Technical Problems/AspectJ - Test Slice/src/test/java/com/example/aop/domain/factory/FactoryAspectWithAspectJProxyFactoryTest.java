package com.example.aop.domain.factory;

import static com.example.aop.domain.Utils.API_PRODUCT;
import static com.example.aop.domain.Utils.DB_PRODUCT;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class FactoryAspectWithAspectJProxyFactoryTest {

  @Test()
  public void shouldCallAspectAndReturnDbOnApi() {
    final RepositoryFactory repositoryFactory = mock(RepositoryFactory.class);
    final AspectJProxyFactory proxyFactory = new AspectJProxyFactory(repositoryFactory);
    proxyFactory.addAspect(FactoryAspect.class);
    final IRepositoryFactory proxy = proxyFactory.getProxy();

    proxy.getParticipantRepository(API_PRODUCT);
    verify(repositoryFactory, times(1)).getParticipantRepository(DB_PRODUCT);
  }

  @Test
  public void shouldCallAspectAndReturnApiOnDb() {
    final RepositoryFactory repositoryFactory = mock(RepositoryFactory.class);
    final AspectJProxyFactory proxyFactory = new AspectJProxyFactory(repositoryFactory);
    proxyFactory.addAspect(FactoryAspect.class);
    final IRepositoryFactory proxy = proxyFactory.getProxy();

    proxy.getParticipantRepository(DB_PRODUCT);
    verify(repositoryFactory, times(1)).getParticipantRepository(API_PRODUCT);
  }
}
