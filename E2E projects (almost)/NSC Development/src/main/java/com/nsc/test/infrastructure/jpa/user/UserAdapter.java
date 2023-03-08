package com.nsc.test.infrastructure.jpa.user;

import static java.util.stream.Collectors.toList;

import com.nsc.test.domain.user.User;
import com.nsc.test.domain.user.UserRepository;
import com.nsc.test.infrastructure.mapper.JpaMapper;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserAdapter implements UserRepository {

  private static final String USER_NOT_FOUND = "User with id %s not found";

  private final UserRepositoryJpa userRepositoryJpa;
  private final JpaMapper mapper;

  @Override
  public List<User> findAll() {
    log.info("Fetching users list");
    return userRepositoryJpa.findAll().stream()
        .map(mapper::toEntity)
        .collect(toList());
  }

  @Override
  public User findById(Long id) {
    log.info("Fetching user by id {}", id);

    final Optional<UserJpa> optionalUserJpa = userRepositoryJpa.getReferenceById(id);
    if (optionalUserJpa.isPresent()) {
      return mapper.toEntity(optionalUserJpa.get());
    }

    throw new EntityNotFoundException(String.format(USER_NOT_FOUND, id));
  }

  @Override
  public User save(User user) {
    log.info("Saving new user with username: {}", user.getUsername());
    final UserJpa userJpa = userRepositoryJpa.save(mapper.toJpa(user));
    return mapper.toEntity(userJpa);
  }

  @Override
  public User update(User user) {
    log.info("Updating user with id: {}", user.getId());

    final UserJpa userJpa = mapper.toJpa(user);
    return mapper.toEntity(userRepositoryJpa.save(userJpa));
  }

  @Override
  @Transactional
  public void deleteById(Long id) {
    log.info("Deleting user with id: {}", id);

    userRepositoryJpa.deleteById(id);
  }
}
