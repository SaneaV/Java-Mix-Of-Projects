package com.nsc.test.infrastructure.jpa.user;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
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
  private UserRepositoryJpa userRepositoryJpa;

  @Test
  public void shouldFindAllUsers() {
    final UserJpa userJpa = getUserJpa();
    final UserJpa persist = entityManager.persist(userJpa);

    final List<UserJpa> result = userRepositoryJpa.findAll();

    assertThat(result.size()).isEqualTo(1);
    assertThat(result.get(0)).usingRecursiveComparison().isEqualTo(getUserJpa(persist.getId()));
  }

  @Test
  public void shouldFindById() {
    final UserJpa userJpa = getUserJpa();
    final UserJpa persist = entityManager.persist(userJpa);

    final Optional<UserJpa> result = userRepositoryJpa.getReferenceById(persist.getId());

    assertThat(result.get()).usingRecursiveComparison().isEqualTo(getUserJpa(persist.getId()));
  }

  @Test
  public void shouldSaveUser() {
    final UserJpa result = userRepositoryJpa.save(getUserJpa());
    assertThat(result).usingRecursiveComparison().isEqualTo(getUserJpa(result.getId()));
  }

  @Test
  public void shouldUpdateUser() {
    final UserJpa userJpa = getUserJpa();
    final UserJpa persist = entityManager.persist(userJpa);

    final UserJpa updateUser = new UserJpa();
    updateUser.setId(persist.getId());
    updateUser.setUsername("newUsername");
    updateUser.setPassword("newPassword");
    updateUser.setFirstName("newFirstName");
    updateUser.setLastName("newLastName");
    updateUser.setBirthdate(birthdate.minusDays(1));
    updateUser.setAbout("newAbout");
    updateUser.setAddress("newAddress");

    final UserJpa result = userRepositoryJpa.save(updateUser);
    assertThat(result).usingRecursiveComparison().isEqualTo(updateUser);
  }

  @Test
  public void shouldDeleteUser() {
    final UserJpa userJpa = getUserJpa();
    final UserJpa persist = entityManager.persist(userJpa);

    userRepositoryJpa.deleteById(persist.getId());

    final Optional<UserJpa> referenceById = userRepositoryJpa.getReferenceById(persist.getId());

    assertThat(referenceById).isEmpty();
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

  private UserJpa getUserJpa(Long id) {
    final UserJpa userJpa = new UserJpa();
    userJpa.setId(id);
    userJpa.setUsername(username);
    userJpa.setPassword(password);
    userJpa.setFirstName(firstName);
    userJpa.setLastName(lastName);
    userJpa.setBirthdate(birthdate);
    userJpa.setAbout(about);
    userJpa.setAddress(address);
    return userJpa;
  }
}
