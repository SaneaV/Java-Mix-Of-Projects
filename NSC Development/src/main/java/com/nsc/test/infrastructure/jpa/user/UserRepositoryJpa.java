package com.nsc.test.infrastructure.jpa.user;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoryJpa extends CrudRepository<UserJpa, String> {

  List<UserJpa> findAll();

  Optional<UserJpa> getReferenceById(Long id);

  UserJpa save(UserJpa user);

  void deleteById(Long id);
}
