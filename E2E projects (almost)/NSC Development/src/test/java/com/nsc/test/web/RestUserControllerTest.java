package com.nsc.test.web;

import static java.util.Collections.singletonList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nsc.test.domain.user.User;
import com.nsc.test.infrastructure.jpa.user.UserAdapter;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(properties = "app.controller.type=REST")
public class RestUserControllerTest {

  private static final Long id = 1L;
  private static final String username = "test-username";
  private static final String password = "test-password";
  private static final String firstName = "test-firstName";
  private static final String lastName = "test-lastName";
  private static final LocalDate birthdate = LocalDate.of(2001, 5, 8);
  private static final String about = "test-about";
  private static final String address = "test-address";

  private static final String USER = "{\n"
      + "        \"id\": 1,\n"
      + "        \"username\": \"test-username\",\n"
      + "        \"password\": \"test-password\",\n"
      + "        \"firstName\": \"test-firstName\",\n"
      + "        \"lastName\": \"test-lastName\",\n"
      + "        \"birthdate\": \"2001-05-08\",\n"
      + "        \"about\": \"test-about\",\n"
      + "        \"address\": \"test-address\"\n"
      + "    }\n";

  private static final String USER_LIST = "[\n" + USER + "]";

  @MockBean
  private UserAdapter userAdapter;
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnListOfUsers() throws Exception {
    when(userAdapter.findAll()).thenReturn(singletonList(getUser()));

    mockMvc.perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(content().json(USER_LIST));
  }

  @Test
  public void shouldReturnUser() throws Exception {
    when(userAdapter.findById(any())).thenReturn(getUser());

    mockMvc.perform(get("/users/1"))
        .andExpect(status().isOk())
        .andExpect(content().json(USER));
  }

  @Test
  public void shouldSaveUser() throws Exception {
    when(userAdapter.save(any())).thenReturn(getUser());

    mockMvc.perform(post("/users")
            .contentType(APPLICATION_JSON)
            .content(USER))
        .andExpect(status().isOk())
        .andExpect(content().json(USER));
  }

  @Test
  public void shouldUpdateUser() throws Exception {
    when(userAdapter.update(any())).thenReturn(getUser());

    mockMvc.perform(put("/users")
            .contentType(APPLICATION_JSON)
            .content(USER))
        .andExpect(status().isOk())
        .andExpect(content().json(USER));
  }

  @Test
  public void shouldDeleteUser() throws Exception {
    mockMvc.perform(delete("/users/1"))
        .andExpect(status().isOk());
  }

  private User getUser() {
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

}
