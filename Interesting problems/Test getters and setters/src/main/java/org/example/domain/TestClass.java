package org.example.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestClass {

  private int testInt;
  private double testDouble;
  private float testFloat;
  private long testLong;
  private boolean testBoolean;

  private Integer testInteger;
  private Double testDoubleClass;
  private Float testFloatClass;
  private Long testLongClass;
  private Boolean testBooleanClass;
  private String testString;
  private LocalDateTime testLocalDateTime;
  private LocalTime testLocalTime;

  private Map<String, String> testMapStringString;
  private Map<String, Integer> testMapStringInteger;
  private Map<Integer, Integer> testMapIntegerInteger;
  private Map<Integer, String> testMapIntegerString;

  private Set<String> testSetString;
  private Set<Integer> testSetInteger;

  private List<String> testListString;
  private List<Integer> testListInteger;

  private TestEnum testEnum;

  private Arrays arrays;
}
