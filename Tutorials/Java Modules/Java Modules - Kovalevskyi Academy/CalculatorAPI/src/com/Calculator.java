package com;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;

public interface Calculator {

  void start(String... args);

  static Calculator get() {
    return ServiceLoader
        .load(Calculator.class)
        .findFirst()
        .orElseThrow(() -> new RuntimeException("No calculator found!"));
  }

  static List<Calculator> getAll() {
    return ServiceLoader
        .load(Calculator.class)
        .stream()
        .map(Provider::get)
        .collect(toList());
  }
}
