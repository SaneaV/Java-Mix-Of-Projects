package com.telegram.bot.infrastructure.bot;

import static org.springframework.util.StringUtils.hasText;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class MessageHandler extends TelegramWebhookBot {

  private final DispatcherCommand dispatcherCommand;
  @Setter
  @Value("${bot.token}")
  private String botToken;

  @SneakyThrows
  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    if (hasText(update.getMessage().getText())) {
      sendMessage(dispatcherCommand.execute(update));
    }
    return null;
  }

  @Override
  public String getBotUsername() {
    return null;
  }

  @Override
  public String getBotToken() {
    return botToken;
  }

  @Override
  public String getBotPath() {
    return null;
  }

  private void sendMessage(PartialBotApiMethod<?> message) throws TelegramApiException {
    if (message instanceof SendPhoto) {
      execute((SendPhoto) message);
    }
    if (message instanceof SendMessage) {
      execute((SendMessage) message);
    }
  }
}
