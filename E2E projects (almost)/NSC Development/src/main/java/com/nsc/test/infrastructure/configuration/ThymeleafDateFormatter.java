package com.nsc.test.infrastructure.configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;

/*
  Custom LocalDate - String formatter to process Thymeleaf date format
 */

public enum ThymeleafDateFormatter implements Converter<String, LocalDate>, Formatter<LocalDate> {
  INSTANCE;

  private static final String DATE_FORMAT = "yyyy-MM-dd";

  @Override
  public LocalDate convert(String source) {
    return LocalDate.parse(source, DateTimeFormatter.ofPattern(DATE_FORMAT));
  }

  @Override
  public LocalDate parse(String text, Locale locale) {
    return LocalDate.parse(text, DateTimeFormatter.ofPattern(DATE_FORMAT));
  }

  @Override
  public String print(LocalDate object, Locale locale) {
    return object.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
  }
}
