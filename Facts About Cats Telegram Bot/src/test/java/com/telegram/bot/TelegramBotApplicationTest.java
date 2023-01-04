package com.telegram.bot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;

public class TelegramBotApplicationTest {

  @Test
  void testApplication() {
    final MockedStatic<SpringApplication> utilities = mockStatic(SpringApplication.class);
    utilities.when((MockedStatic.Verification) SpringApplication.run(TelegramBotApplication.class, new String[]{}))
        .thenReturn(null);
    TelegramBotApplication.main(new String[]{});

    assertThat(SpringApplication.run(TelegramBotApplication.class)).isEqualTo(null);
  }
}
