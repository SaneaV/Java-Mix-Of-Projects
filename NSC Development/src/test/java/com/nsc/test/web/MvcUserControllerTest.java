package com.nsc.test.web;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.nsc.test.domain.user.User;
import com.nsc.test.infrastructure.jpa.user.UserAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest
public class MvcUserControllerTest {

  @MockBean
  private UserAdapter userAdapter;
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnIndexPage() throws Exception {
    final MvcResult mvcResult = mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("/index"))
        .andReturn();

    assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
  }

  @Test
  public void shouldInfoUserPage() throws Exception {
    final Long id = 1L;
    final User user = User.builder().build();

    when(userAdapter.findById(id)).thenReturn(user);

    final MvcResult mvcResult = mockMvc.perform(get("/info-user/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("/info-user"))
        .andReturn();

    assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
  }

  @Test
  public void shouldFormToAddUser() throws Exception {
    final MvcResult mvcResult = mockMvc.perform(get("/add-user"))
        .andExpect(status().isOk())
        .andExpect(view().name("/add-user"))
        .andReturn();

    assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
  }

  @Test
  public void shouldFormToUpdateUser() throws Exception {
    final Long id = 1L;
    final User user = User.builder().build();

    when(userAdapter.findById(id)).thenReturn(user);

    final MvcResult mvcResult = mockMvc.perform(get("/update-user/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("/update-user"))
        .andReturn();

    assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
  }

  @Test
  public void shouldSaveAndRedirect() throws Exception {
    final MvcResult mvcResult = mockMvc.perform(post("/user"))
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/"))
        .andReturn();

    assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
  }

  @Test
  public void shouldUpdateAndRedirect() throws Exception {
    final MvcResult mvcResult = mockMvc.perform(post("/user/1"))
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/"))
        .andReturn();

    assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
  }

  @Test
  public void shouldDeleteAndRedirect() throws Exception {
    final MvcResult mvcResult = mockMvc.perform(get("/delete-user/1"))
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/"))
        .andReturn();

    assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
  }
}
