package com.telegram.bot.infrastructure.bot;

import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.EMPTY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MainController.class)
public class MainControllerIT {

  private static final String PATH = "/";
  private static final String REQUEST_BODY = "{\n"
      + "    \"update_id\": 422297740,\n"
      + "    \"message\": {\n"
      + "        \"message_id\": 118,\n"
      + "        \"from\": {\n"
      + "            \"id\": 353461713,\n"
      + "            \"first_name\": \"Александр\",\n"
      + "            \"is_bot\": false,\n"
      + "            \"username\": \"SaneaV\",\n"
      + "            \"language_code\": \"en\"\n"
      + "        },\n"
      + "        \"date\": 1662490099,\n"
      + "        \"chat\": {\n"
      + "            \"id\": 353461713,\n"
      + "            \"type\": \"private\",\n"
      + "            \"first_name\": \"Александр\",\n"
      + "            \"username\": \"SaneaV\"\n"
      + "        },\n"
      + "        \"text\": \"факт о кошках\"\n"
      + "    }\n"
      + "}";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MessageHandler messageHandler;
  @Inject
  private MainController mainController;

  @Test
  void shouldReturnSendMessage() throws Exception {
    mockMvc.perform(post(PATH)
            .contentType(APPLICATION_JSON)
            .content(REQUEST_BODY))
        .andExpect(status().isOk())
        .andExpect(content().string(EMPTY));

    verify(messageHandler, atMostOnce()).onWebhookUpdateReceived(any());
  }
}
