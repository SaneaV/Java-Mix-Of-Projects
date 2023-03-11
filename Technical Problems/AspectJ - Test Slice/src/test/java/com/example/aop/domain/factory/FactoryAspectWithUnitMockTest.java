package com.example.aop.domain.factory;

import static com.example.aop.domain.Utils.API_PRODUCT;
import static com.example.aop.domain.Utils.DB_PRODUCT;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;

public class FactoryAspectWithUnitMockTest {

  private final ProceedingJoinPoint proceedingJoinPoint = mock(ProceedingJoinPoint.class);

  private final FactoryAspect factoryAspect = new FactoryAspect();

  @Test
  public void shouldCallAspectAndReturnDbOnApi() throws Throwable {
    factoryAspect.mapProduct(proceedingJoinPoint, API_PRODUCT);
    verify(proceedingJoinPoint, times(1)).proceed(new Object[]{DB_PRODUCT});
  }

  @Test
  public void shouldCallAspectAndReturnApiOnDb() throws Throwable {
    factoryAspect.mapProduct(proceedingJoinPoint, DB_PRODUCT);
    verify(proceedingJoinPoint, times(1)).proceed(new Object[]{API_PRODUCT});
  }
}
