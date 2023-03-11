package com.example.aop.domain.factory;

import static com.example.aop.domain.Utils.API_PRODUCT;
import static com.example.aop.domain.Utils.DB_PRODUCT;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FactoryAspect {

  @Around("execution(* com.example.aop.domain.factory.RepositoryFactory.get*(String)) && args(originalProduct)")
  public Object mapProduct(ProceedingJoinPoint joinPoint, String originalProduct) throws Throwable {
    String product;
    if (originalProduct.equalsIgnoreCase(DB_PRODUCT)) {
      product = API_PRODUCT;
    } else {
      product = DB_PRODUCT;
    }
    return joinPoint.proceed(new Object[]{product});
  }
}
