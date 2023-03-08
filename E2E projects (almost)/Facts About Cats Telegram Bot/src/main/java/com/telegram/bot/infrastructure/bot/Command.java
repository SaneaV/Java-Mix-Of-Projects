package com.telegram.bot.infrastructure.bot;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {

  PartialBotApiMethod<?> execute(Update update, String language);

  String getCommand();

  default String getChatId(Update update) {
    return update.getMessage().getChatId().toString();
  }
}
