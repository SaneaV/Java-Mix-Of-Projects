package com.nsc.test.web;

import com.nsc.test.domain.user.User;
import com.nsc.test.infrastructure.jpa.user.UserAdapter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.controller.type", havingValue = "REST")
public class RestUserController {

  private final UserAdapter userAdapter;

  @GetMapping(path = "/users")
  public List<User> findAll() {
    return userAdapter.findAll();
  }

  @GetMapping(path = "/users/{id}")
  public User findById(@PathVariable Long id) {
    return userAdapter.findById(id);
  }

  @PostMapping(path = "/users")
  public User save(@RequestBody User user) {
    return userAdapter.save(user);
  }

  @PutMapping(path = "/users")
  public User update(@RequestBody User user) {
    return userAdapter.update(user);
  }

  @DeleteMapping(path = "/users/{id}")
  public void deleteById(@PathVariable Long id) {
    userAdapter.deleteById(id);
  }
}
