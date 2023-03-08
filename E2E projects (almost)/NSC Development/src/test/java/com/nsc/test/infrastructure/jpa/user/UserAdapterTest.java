package com.nsc.test.infrastructure.jpa.user;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nsc.test.domain.user.User;
import com.nsc.test.infrastructure.mapper.JpaMapper;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(UserAdapter.class)
public class UserAdapterTest {

  @Autowired
  private UserAdapter userAdapter;
  @MockBean
  private UserRepositoryJpa userRepositoryJpa;
  @MockBean
  private JpaMapper mapper;

  @Test
  public void shouldFindAll() {
    final UserJpa userJpa = new UserJpa();
    final User user = User.builder().build();

    when(userRepositoryJpa.findAll()).thenReturn(singletonList(userJpa));
    when(mapper.toEntity(userJpa)).thenReturn(user);

    final List<User> listOfUsers = userAdapter.findAll();

    assertThat(listOfUsers.size()).isEqualTo(1);
    assertThat(listOfUsers.get(0)).isEqualTo(user);
    verify(userRepositoryJpa, times(1)).findAll();
    verify(mapper, times(1)).toEntity(userJpa);
  }

  @Test
  public void shouldFindById() {
    final UserJpa userJpa = new UserJpa();
    final User user = User.builder().build();

    when(userRepositoryJpa.getReferenceById(any())).thenReturn(Optional.of(userJpa));
    when(mapper.toEntity(userJpa)).thenReturn(user);

    final User result = userAdapter.findById(1L);

    assertThat(result).isEqualTo(user);
    verify(userRepositoryJpa, times(1)).getReferenceById(any());
    verify(mapper, times(1)).toEntity(userJpa);
  }

  @Test
  public void shouldThrowExceptionIfNotFound() {
    final UserJpa userJpa = new UserJpa();

    when(userRepositoryJpa.getReferenceById(any())).thenReturn(Optional.empty());

    final EntityNotFoundException entityNotFoundException = assertThrows(EntityNotFoundException.class,
        () -> userAdapter.findById(1L));

    assertThat(entityNotFoundException.getMessage()).isEqualTo(String.format("User with id %s not found", 1L));
    verify(userRepositoryJpa, times(1)).getReferenceById(any());
    verify(mapper, times(0)).toEntity(userJpa);
  }

  @Test
  public void shouldSaveUser() {
    final UserJpa userJpa = new UserJpa();
    final User user = User.builder().build();

    when(userRepositoryJpa.save(any())).thenReturn(userJpa);
    when(mapper.toEntity(userJpa)).thenReturn(user);

    final User result = userAdapter.save(user);

    assertThat(result).isEqualTo(user);
    verify(userRepositoryJpa, times(1)).save(any());
    verify(mapper, times(1)).toEntity(userJpa);
  }

  @Test
  public void shouldUpdateUser() {
    final UserJpa userJpa = new UserJpa();
    final User user = User.builder().build();

    when(userRepositoryJpa.save(any())).thenReturn(userJpa);
    when(mapper.toEntity(userJpa)).thenReturn(user);

    final User result = userAdapter.update(user);

    assertThat(result).isEqualTo(user);
    verify(userRepositoryJpa, times(1)).save(any());
    verify(mapper, times(1)).toEntity(userJpa);
  }

  @Test
  public void shouldDeleteUser() {
    final UserJpa userJpa = new UserJpa();
    final User user = User.builder().build();

    when(mapper.toEntity(userJpa)).thenReturn(user);

    userAdapter.deleteById(1L);

    verify(userRepositoryJpa, times(1)).deleteById(anyLong());
  }
}
