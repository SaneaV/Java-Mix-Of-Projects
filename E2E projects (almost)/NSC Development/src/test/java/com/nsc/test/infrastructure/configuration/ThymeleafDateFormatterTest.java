package com.nsc.test.infrastructure.configuration;

import static com.nsc.test.infrastructure.configuration.ThymeleafDateFormatter.INSTANCE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ThymeleafDateFormatterTest {

  private static final String DATE_FORMAT = "yyyy-MM-dd";

  private ThymeleafDateFormatter thymeleafDateFormatter;

  @BeforeEach
  public void init() {
    thymeleafDateFormatter = INSTANCE;
  }

  @Test
  public void shouldConvertStringToLocalDate() {
    final LocalDate currentDate = LocalDate.now();
    final String stringCurrentDate = currentDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    final LocalDate result = thymeleafDateFormatter.convert(stringCurrentDate);
    assertThat(result).isEqualTo(currentDate);
  }

  @Test
  public void shouldParseStringToLocalDate() {
    final LocalDate currentDate = LocalDate.now();
    final String stringCurrentDate = currentDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    final LocalDate result = thymeleafDateFormatter.parse(stringCurrentDate, null);
    assertThat(result).isEqualTo(currentDate);
  }

  @Test
  public void shouldPrintLocalDateToString() {
    final LocalDate currentDate = LocalDate.now();
    final String stringCurrentDate = currentDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    final String result = thymeleafDateFormatter.print(currentDate, null);
    assertThat(result).isEqualTo(stringCurrentDate);
  }
}
