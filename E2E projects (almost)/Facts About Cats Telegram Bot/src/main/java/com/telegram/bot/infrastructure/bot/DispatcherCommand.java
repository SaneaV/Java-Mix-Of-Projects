package com.telegram.bot.infrastructure.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class DispatcherCommand {

  private final CommandFacade commandFacade;

  public PartialBotApiMethod<?> execute(Update update) {
    return commandFacade.processCommand(update);
  }
}
