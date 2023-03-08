package com.nsc.test.infrastructure.configuration;

import static com.nsc.test.infrastructure.configuration.ThymeleafDateFormatter.INSTANCE;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
  This class was created in order for Thymeleaf to use a custom date converter.
  Since the application can work with both REST implementation and Thymeleaf,
  which does not use ISO-8601;
 */

@Configuration
@ConditionalOnProperty(name = "app.controller.type", havingValue = "UI")
public class MvcConverterConfig implements WebMvcConfigurer {

  public void addFormatters(FormatterRegistry registry) {
    registry.addFormatter(INSTANCE);
  }
}
