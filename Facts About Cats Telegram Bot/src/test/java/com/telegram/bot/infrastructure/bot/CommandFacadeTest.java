package com.telegram.bot.infrastructure.bot;

import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.EMPTY;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.FACT_ABOUT_CATS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.telegram.bot.infrastructure.bot.command.CatFactCommand;
import com.telegram.bot.infrastructure.bot.command.SelectLanguageCommand;
import com.telegram.bot.infrastructure.bot.command.StartCommand;
import com.telegram.bot.infrastructure.bot.command.UnknownCommand;
import com.telegram.bot.infrastructure.service.UserPreferencesService;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@ExtendWith(SpringExtension.class)
@Import(CommandFacade.class)
public class CommandFacadeTest {

  //COMMAND
  private static final String START_COMMAND = "/start";

  //RESPONSE
  private static final String UNKNOWN_COMMAND_RESPONSE = "Мне неизвестна твоя команда.";
  private static final String START_COMMAND_RESPONSE = "Привет! Ты находишься в боте, который знает о котиках всё, пиши \"Факты о кошках\".";
  private static final String SELECT_LANGUAGE = "Please select the language you would like to communicate with the bot";

  @MockBean
  private UnknownCommand unknownCommand;
  @MockBean
  private StartCommand startCommand;
  @MockBean
  private CatFactCommand catFactCommand;
  @MockBean
  private SelectLanguageCommand selectLanguageCommand;
  @MockBean
  private UserPreferencesService userPreferencesService;
  @Inject
  private CommandFacade commandFacade;

  @Test
  void shouldReturnResultOfExistingCommand() {
    final long chatId = 10;
    final long userId = 20;
    final PartialBotApiMethod sendMessage = SendMessage.builder()
        .text(START_COMMAND_RESPONSE)
        .chatId(chatId)
        .build();
    final Update update = mock(Update.class);
    final Message message = mock(Message.class);
    final User user = mock(User.class);

    when(update.getMessage()).thenReturn(message);
    when(message.getText()).thenReturn(START_COMMAND);
    when(message.getFrom()).thenReturn(user);
    when(user.getId()).thenReturn(userId);
    when(userPreferencesService.userPreferencesExist(any())).thenReturn(true);
    when(startCommand.getCommand()).thenReturn(START_COMMAND);
    when(catFactCommand.getCommand()).thenReturn(EMPTY);
    when(unknownCommand.getCommand()).thenReturn(EMPTY);
    when(startCommand.execute(any(), any())).thenReturn(sendMessage);

    final PartialBotApiMethod<?> result = commandFacade.processCommand(update);

    assertThat(result).isExactlyInstanceOf(SendMessage.class);
    final SendMessage sendMessageResult = (SendMessage) result;
    assertThat(sendMessageResult.getChatId()).isEqualTo(String.valueOf(chatId));
    assertThat(sendMessageResult.getText()).isEqualTo(START_COMMAND_RESPONSE);
  }

  @Test
  void shouldReturnResultOfUnknownCommand() {
    final long chatId = 10;
    final long userId = 20;
    final PartialBotApiMethod sendMessage = SendMessage.builder()
        .text(UNKNOWN_COMMAND_RESPONSE)
        .chatId(chatId)
        .build();
    final Update update = mock(Update.class);
    final Message message = mock(Message.class);
    final User user = mock(User.class);

    when(update.getMessage()).thenReturn(message);
    when(message.getText()).thenReturn(EMPTY);
    when(message.getFrom()).thenReturn(user);
    when(user.getId()).thenReturn(userId);
    when(userPreferencesService.userPreferencesExist(any())).thenReturn(true);
    when(startCommand.getCommand()).thenReturn(START_COMMAND);
    when(catFactCommand.getCommand()).thenReturn(FACT_ABOUT_CATS);
    when(unknownCommand.getCommand()).thenReturn(EMPTY);
    when(unknownCommand.execute(any(), any())).thenReturn(sendMessage);

    final PartialBotApiMethod<?> result = commandFacade.processCommand(update);

    assertThat(result).isExactlyInstanceOf(SendMessage.class);
    final SendMessage sendMessageResult = (SendMessage) result;
    assertThat(sendMessageResult.getChatId()).isEqualTo(String.valueOf(chatId));
    assertThat(sendMessageResult.getText()).isEqualTo(UNKNOWN_COMMAND_RESPONSE);

    verify(userPreferencesService, atMostOnce()).userPreferencesExist(any());
    verify(startCommand, atMostOnce()).getCommand();
    verify(catFactCommand, atMostOnce()).getCommand();
    verify(unknownCommand, atMostOnce()).getCommand();
    verify(unknownCommand, atMostOnce()).execute(any(), any());
  }

  @Test
  void shouldReturnStartCommandAfterSetLanguage() {
    final long chatId = 10;
    final long userId = 20;
    final String messageText = "English";
    final PartialBotApiMethod sendMessage = SendMessage.builder()
        .text(START_COMMAND_RESPONSE)
        .chatId(chatId)
        .build();
    final Update update = mock(Update.class);
    final Message message = mock(Message.class);
    final User user = mock(User.class);

    when(update.getMessage()).thenReturn(message);
    when(message.getText()).thenReturn(messageText);
    when(message.getFrom()).thenReturn(user);
    when(user.getId()).thenReturn(userId);
    when(userPreferencesService.userPreferencesExist(any())).thenReturn(false);
    when(startCommand.execute(any(), any())).thenReturn(sendMessage);

    final PartialBotApiMethod<?> result = commandFacade.processCommand(update);

    assertThat(result).isExactlyInstanceOf(SendMessage.class);
    final SendMessage sendMessageResult = (SendMessage) result;
    assertThat(sendMessageResult.getChatId()).isEqualTo(String.valueOf(chatId));
    assertThat(sendMessageResult.getText()).isEqualTo(START_COMMAND_RESPONSE);

    verify(userPreferencesService, atMostOnce()).saveUserPreferences(any(), any());
    verify(startCommand, atMostOnce()).execute(any(), any());
  }

  @Test
  void shouldReturnSelectLanguageCommandIfLanguageNotSet() {
    final long chatId = 10;
    final long userId = 20;
    final String messageText = "Italian";
    final PartialBotApiMethod sendMessage = SendMessage.builder()
        .text(SELECT_LANGUAGE)
        .chatId(chatId)
        .build();
    final Update update = mock(Update.class);
    final Message message = mock(Message.class);
    final User user = mock(User.class);

    when(update.getMessage()).thenReturn(message);
    when(message.getText()).thenReturn(messageText);
    when(message.getFrom()).thenReturn(user);
    when(user.getId()).thenReturn(userId);
    when(userPreferencesService.userPreferencesExist(any())).thenReturn(false);
    when(selectLanguageCommand.execute(any(), any())).thenReturn(sendMessage);

    final PartialBotApiMethod<?> result = commandFacade.processCommand(update);

    assertThat(result).isExactlyInstanceOf(SendMessage.class);
    final SendMessage sendMessageResult = (SendMessage) result;
    assertThat(sendMessageResult.getChatId()).isEqualTo(String.valueOf(chatId));
    assertThat(sendMessageResult.getText()).isEqualTo(SELECT_LANGUAGE);

    verify(userPreferencesService, atMostOnce()).saveUserPreferences(any(), any());
    verify(selectLanguageCommand, atMostOnce()).execute(any(), any());
  }
}
