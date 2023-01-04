package com.telegram.bot.infrastructure.bot;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@ExtendWith(MockitoExtension.class)
@Import({MessageHandler.class})
public class MessageHandlerTest {

  private static final String MESSAGE_TEXT = "Random Text";
  private static final String BOT_TOKEN = "RANDOM_UUID4";
  private static final String URL = "Random URL";

  @Mock
  private DispatcherCommand dispatcherCommand;
  @Spy
  @InjectMocks
  private MessageHandler messageHandler;

  @BeforeEach
  public void setUp() {
    messageHandler.setBotToken(BOT_TOKEN);
  }

  @Test
  void shouldReturnBotUsername() {
    assertThat(messageHandler.getBotUsername()).isNull();
  }

  @Test
  void shouldReturnBotToken() {
    assertThat(messageHandler.getBotToken()).isEqualTo(BOT_TOKEN);
  }

  @Test
  void shouldReturnBotPath() {
    assertThat(messageHandler.getBotPath()).isNull();
  }

  @Test
  void shouldReturnSendPhoto() throws TelegramApiException {
    final String chatId = "10";
    final Update update = mock(Update.class);
    final Message message = mock(Message.class);
    final PartialBotApiMethod sendMessage = SendPhoto.builder()
        .chatId(chatId)
        .caption(MESSAGE_TEXT)
        .photo(new InputFile(URL))
        .build();

    when(update.getMessage()).thenReturn(message);
    when(message.getText()).thenReturn(MESSAGE_TEXT);
    when(dispatcherCommand.execute(update)).thenReturn(sendMessage);
    doReturn(message).when(messageHandler).execute(any(SendPhoto.class));

    final BotApiMethod<?> result = messageHandler.onWebhookUpdateReceived(update);

    verify(dispatcherCommand, atMostOnce()).execute(update);
    verify(messageHandler, atMostOnce()).execute(any(SendPhoto.class));
    assertThat(result).isNull();
  }

  @Test
  void shouldReturnSendMessage() throws TelegramApiException {
    final String chatId = "10";
    final Update update = mock(Update.class);
    final Message message = mock(Message.class);
    final PartialBotApiMethod sendMessage = SendMessage.builder()
        .chatId(chatId)
        .text(MESSAGE_TEXT)
        .build();

    when(update.getMessage()).thenReturn(message);
    when(message.getText()).thenReturn(MESSAGE_TEXT);
    when(dispatcherCommand.execute(update)).thenReturn(sendMessage);
    doReturn(message).when(messageHandler).execute(any(SendMessage.class));

    final BotApiMethod<?> result = messageHandler.onWebhookUpdateReceived(update);

    verify(dispatcherCommand, atMostOnce()).execute(update);
    verify(messageHandler, atMostOnce()).execute(any(SendMessage.class));
    assertThat(result).isNull();
  }

  @Test
  void shouldThrowException() throws TelegramApiException {
    final String chatId = "10";
    final Update update = mock(Update.class);
    final Message message = mock(Message.class);
    final PartialBotApiMethod sendMessage = SendPhoto.builder()
        .chatId(chatId)
        .caption(MESSAGE_TEXT)
        .photo(new InputFile(URL))
        .build();

    when(update.getMessage()).thenReturn(message);
    when(message.getText()).thenReturn(MESSAGE_TEXT);
    when(dispatcherCommand.execute(update)).thenReturn(sendMessage);

    assertThrows(TelegramApiException.class, () -> messageHandler.onWebhookUpdateReceived(update));
  }
}
