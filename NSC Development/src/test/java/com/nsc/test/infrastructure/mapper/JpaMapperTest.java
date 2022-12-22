package com.nsc.test.infrastructure.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.nsc.test.domain.user.User;
import com.nsc.test.infrastructure.jpa.user.UserJpa;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(JpaMapperImpl.class)
class JpaMapperTest {

  private static final Long id = 1L;
  private static final String username = "test-username";
  private static final String password = "test-password";
  private static final String firstName = "test-firstName";
  private static final String lastName = "test-lastName";
  private static final LocalDate birthdate = LocalDate.now();
  private static final String about = "test-about";
  private static final String address = "test-address";

  @Autowired
  private JpaMapper jpaMapper;

  @Test
  public void shouldMapUserJpaToUser() {
    final UserJpa userJpa = new UserJpa(id, username, password, firstName, lastName, birthdate, about, address);

    final User result = jpaMapper.toEntity(userJpa);

    assertThat(result.getId()).isEqualTo(userJpa.getId());
    assertThat(result.getUsername()).isEqualTo(userJpa.getUsername());
    assertThat(result.getPassword()).isEqualTo(userJpa.getPassword());
    assertThat(result.getFirstName()).isEqualTo(userJpa.getFirstName());
    assertThat(result.getLastName()).isEqualTo(userJpa.getLastName());
    assertThat(result.getBirthdate()).isEqualTo(userJpa.getBirthdate());
    assertThat(result.getAbout()).isEqualTo(userJpa.getAbout());
    assertThat(result.getAddress()).isEqualTo(userJpa.getAddress());
  }

  @Test
  public void shouldMapUserToUserJpa() {
    final User user = User.builder()
        .id(id)
        .username(username)
        .password(password)
        .firstName(firstName)
        .lastName(lastName)
        .birthdate(birthdate)
        .about(about)
        .address(address)
        .build();

    final UserJpa result = jpaMapper.toJpa(user);

    assertThat(result.getId()).isEqualTo(user.getId());
    assertThat(result.getUsername()).isEqualTo(user.getUsername());
    assertThat(result.getPassword()).isEqualTo(user.getPassword());
    assertThat(result.getFirstName()).isEqualTo(user.getFirstName());
    assertThat(result.getLastName()).isEqualTo(user.getLastName());
    assertThat(result.getBirthdate()).isEqualTo(user.getBirthdate());
    assertThat(result.getAbout()).isEqualTo(user.getAbout());
    assertThat(result.getAddress()).isEqualTo(user.getAddress());
  }

  @Test
  public void shouldReturnNullIfUserJpaIsNull() {
    final UserJpa userJpa = null;

    final User result = jpaMapper.toEntity(userJpa);

    assertThat(result).isNull();
  }

  @Test
  public void shouldReturnNullIfUserIsNull() {
    final User user = null;

    final UserJpa result = jpaMapper.toJpa(user);

    assertThat(result).isNull();
  }
}
