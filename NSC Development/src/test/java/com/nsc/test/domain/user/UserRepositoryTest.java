package com.nsc.test.domain.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.nsc.test.infrastructure.jpa.user.UserAdapter;
import com.nsc.test.infrastructure.jpa.user.UserJpa;
import com.nsc.test.infrastructure.mapper.JpaMapper;
import com.nsc.test.infrastructure.mapper.JpaMapperImpl;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({UserAdapter.class, JpaMapperImpl.class})
public class UserRepositoryTest {

  private static final String username = "test-username";
  private static final String password = "test-password";
  private static final String firstName = "test-firstName";
  private static final String lastName = "test-lastName";
  private static final LocalDate birthdate = LocalDate.now();
  private static final String about = "test-about";
  private static final String address = "test-address";

  @Autowired
  private TestEntityManager entityManager;
  @Autowired
  private UserAdapter userAdapter;
  @Autowired
  private JpaMapper mapper;

  @Test
  public void shouldFindAllUsers() {
    final UserJpa userJpa = getUserJpa();
    final UserJpa persist = entityManager.persist(userJpa);

    final List<User> result = userAdapter.findAll();

    assertThat(result.size()).isEqualTo(1);
    assertThat(result.get(0)).usingRecursiveComparison().isEqualTo(getUser(persist.getId()));
  }

  @Test
  public void shouldFindById() {
    final UserJpa userJpa = getUserJpa();
    final UserJpa persist = entityManager.persist(userJpa);

    final User result = userAdapter.findById(persist.getId());

    assertThat(result).usingRecursiveComparison().isEqualTo(getUser(persist.getId()));
  }

  @Test
  public void shouldSaveUser() {
    final User result = userAdapter.save(getUser());
    assertThat(result).usingRecursiveComparison().isEqualTo(getUser(result.getId()));
  }

  @Test
  public void shouldUpdateUser() {
    final UserJpa userJpa = getUserJpa();
    final UserJpa persist = entityManager.persist(userJpa);

    final User updateUser = User.builder()
        .id(persist.getId())
        .username("newUsername")
        .password("newPassword")
        .firstName("newFirstName")
        .lastName("newLastName")
        .birthdate(birthdate.minusDays(1))
        .about("newAbout")
        .address("newAddress")
        .build();

    final User result = userAdapter.update(updateUser);
    assertThat(result).usingRecursiveComparison().isEqualTo(updateUser);
  }

  @Test
  public void shouldDeleteUser() {
    final UserJpa userJpa = getUserJpa();
    final UserJpa persist = entityManager.persist(userJpa);

    userAdapter.deleteById(persist.getId());

    final EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class,
        () -> userAdapter.findById(persist.getId()));
    assertThat(entityNotFoundException.getMessage()).isEqualTo(String.format("User with id %s not found", persist.getId()));
  }

  private UserJpa getUserJpa() {
    final UserJpa userJpa = new UserJpa();
    userJpa.setUsername(username);
    userJpa.setPassword(password);
    userJpa.setFirstName(firstName);
    userJpa.setLastName(lastName);
    userJpa.setBirthdate(birthdate);
    userJpa.setAbout(about);
    userJpa.setAddress(address);
    return userJpa;
  }

  private User getUser(Long id) {
    return User.builder()
        .id(id)
        .username(username)
        .password(password)
        .firstName(firstName)
        .lastName(lastName)
        .birthdate(birthdate)
        .about(about)
        .address(address)
        .build();
  }

  private User getUser() {
    return User.builder()
        .username(username)
        .password(password)
        .firstName(firstName)
        .lastName(lastName)
        .birthdate(birthdate)
        .about(about)
        .address(address)
        .build();
  }
}
