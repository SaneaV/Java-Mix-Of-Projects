package com.telegram.bot.infrastructure.utils;

import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PRIVATE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class InputStreamReaderUtils {

  public static String readFromInputStream(InputStream inputStream) throws IOException {
    final StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while (nonNull(line = br.readLine())) {
        resultStringBuilder.append(line).append("\n");
      }
    }
    return resultStringBuilder.toString();
  }
}
