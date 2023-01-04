package com.telegram.bot.infrastructure.bot.command;

import static com.telegram.bot.infrastructure.bot.utils.MessageUtils.sendMessageWithFactAboutCats;

import com.telegram.bot.infrastructure.bot.Command;
import com.telegram.bot.infrastructure.service.TranslatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UnknownCommand implements Command {

  private static final String EMPTY = "";
  private static final String DB_TRANSLATION_CODE = "unknown_command";

  private final TranslatesService translatesService;

  @Override
  public PartialBotApiMethod<?> execute(Update update, String language) {
    return sendMessageWithFactAboutCats(getChatId(update), translatesService.getTranslateBy(language, DB_TRANSLATION_CODE),
        language);
  }

  @Override
  public String getCommand() {
    return EMPTY;
  }
}
