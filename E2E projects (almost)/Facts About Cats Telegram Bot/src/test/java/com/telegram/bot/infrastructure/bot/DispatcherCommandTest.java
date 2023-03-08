package com.telegram.bot.infrastructure.bot;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@ExtendWith(SpringExtension.class)
@Import(DispatcherCommand.class)
public class DispatcherCommandTest {

  @MockBean
  private CommandFacade commandFacade;
  @Inject
  private DispatcherCommand dispatcherCommand;

  @Test
  void shouldReturnExecutedCommand() {
    final Update update = mock(Update.class);
    final String chatId = "10";
    final String text = "text";
    final PartialBotApiMethod sendMessage = new SendMessage(chatId, text);

    when(commandFacade.processCommand(update)).thenReturn(sendMessage);

    final PartialBotApiMethod<?> result = dispatcherCommand.execute(update);

    verify(commandFacade, atMostOnce()).processCommand(any());
    assertThat(result).isExactlyInstanceOf(SendMessage.class);
    final SendMessage sendMessageResult = (SendMessage) result;
    assertThat(sendMessageResult.getText()).isEqualTo(text);
    assertThat(sendMessageResult.getChatId()).isEqualTo(chatId);
  }
}
