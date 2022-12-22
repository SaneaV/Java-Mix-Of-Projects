package com.nsc.test.web;

import com.nsc.test.domain.user.User;
import com.nsc.test.infrastructure.jpa.user.UserAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.controller.type", havingValue = "UI")
public class MvcUserController {

  private final UserAdapter userAdapter;

  @GetMapping(path = "/")
  public String findAll(Model model) {
    model.addAttribute("users", userAdapter.findAll());
    return "/index";
  }

  @GetMapping(value = "/info-user/{id}")
  public String findById(@PathVariable("id") Long id, Model model) {
    model.addAttribute("user", userAdapter.findById(id));
    return "/info-user";
  }

  @GetMapping(value = "/add-user")
  public String addUser(Model model) {
    model.addAttribute("user", User.builder().build());
    return "/add-user";
  }

  @GetMapping(value = "/update-user/{id}")
  public String updateUser(@PathVariable("id") Long id, Model model) {
    model.addAttribute("user", userAdapter.findById(id));
    return "/update-user";
  }

  @PostMapping("/user")
  public String save(User user) {
    userAdapter.save(user);
    return "redirect:/";
  }

  @PostMapping("/user/{id}")
  public String update(@PathVariable Long id, User user) {
    user.setId(id);
    userAdapter.update(user);
    return "redirect:/";
  }

  @GetMapping(value = "/delete-user/{id}")
  public String delete(@PathVariable Long id) {
    userAdapter.deleteById(id);
    return "redirect:/";
  }
}
