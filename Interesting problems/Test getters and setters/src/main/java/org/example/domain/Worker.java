package org.example.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Worker {

  private String firstName;
  private String secondName;
  private Integer age;
  private Responsibility[] responsibilities;
  private Responsibility responsibility;
}
