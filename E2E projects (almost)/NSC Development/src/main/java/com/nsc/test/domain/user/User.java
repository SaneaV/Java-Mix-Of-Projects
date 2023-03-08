package com.nsc.test.domain.user;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class User {

  private Long id;
  private final String username;
  private final String password;
  private final String firstName;
  private final String lastName;
  private final LocalDate birthdate;
  private final String about;
  private final String address;
}
