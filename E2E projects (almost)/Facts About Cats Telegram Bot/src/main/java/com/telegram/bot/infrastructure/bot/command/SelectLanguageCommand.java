package com.telegram.bot.infrastructure.bot.command;

import static com.telegram.bot.infrastructure.bot.utils.MessageUtils.sendMessageWithSelectYourLanguage;

import com.telegram.bot.infrastructure.bot.Command;
import com.telegram.bot.infrastructure.service.TranslatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class SelectLanguageCommand implements Command {

  private static final String SELECT_LANGUAGE = "Change language - Изменить язык - Змінити мову";
  private static final String DB_TRANSLATION_CODE = "chose_your_language";

  private final TranslatesService translatesService;

  @Override
  public PartialBotApiMethod<?> execute(Update update, String language) {
    return sendMessageWithSelectYourLanguage(getChatId(update), translatesService.getTranslateBy(language, DB_TRANSLATION_CODE),
        language);
  }

  @Override
  public String getCommand() {
    return SELECT_LANGUAGE;
  }
}
