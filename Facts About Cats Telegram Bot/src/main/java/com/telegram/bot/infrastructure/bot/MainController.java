package com.telegram.bot.infrastructure.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RestController
@RequiredArgsConstructor
public class MainController {

  private final MessageHandler messageHandler;

  @PostMapping(value = "/")
  public BotApiMethod<?> handler(@RequestBody Update update) {
    return messageHandler.onWebhookUpdateReceived(update);
  }
}
