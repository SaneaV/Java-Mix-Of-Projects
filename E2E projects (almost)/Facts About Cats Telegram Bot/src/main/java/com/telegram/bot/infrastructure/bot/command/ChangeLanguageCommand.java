package com.telegram.bot.infrastructure.bot.command;

import static com.telegram.bot.infrastructure.bot.utils.LanguageUtils.getLanguage;
import static com.telegram.bot.infrastructure.bot.utils.MessageUtils.sendMessageWithFactAboutCats;

import com.telegram.bot.infrastructure.bot.Command;
import com.telegram.bot.infrastructure.service.TranslatesService;
import com.telegram.bot.infrastructure.service.UserPreferencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class ChangeLanguageCommand implements Command {

  private static final String LANGUAGES =
      "Russian \uD83C\uDDF7\uD83C\uDDFA - Ukrainian \uD83C\uDDFA\uD83C\uDDE6 - English \uD83C\uDDFA\uD83C\uDDF8 - "
          + "Русский \uD83C\uDDF7\uD83C\uDDFA - Украинский \uD83C\uDDFA\uD83C\uDDE6 - Английский \uD83C\uDDFA\uD83C\uDDF8 - "
          + "Російська \uD83C\uDDF7\uD83C\uDDFA - Українська \uD83C\uDDFA\uD83C\uDDE6 - Англійська \uD83C\uDDFA\uD83C\uDDF8";
  private static final String DB_TRANSLATION_CODE = "language_was_changed";
  private final UserPreferencesService userPreferencesService;
  private final TranslatesService translatesService;

  @Override
  public PartialBotApiMethod<?> execute(Update update, String language) {
    final String text = update.getMessage().getText();
    final String newLanguage = getLanguage(text);
    userPreferencesService.saveUserPreferences(update.getMessage().getFrom().getId(), newLanguage);
    return sendMessageWithFactAboutCats(getChatId(update), translatesService.getTranslateBy(newLanguage, DB_TRANSLATION_CODE),
        newLanguage);
  }

  @Override
  public String getCommand() {
    return LANGUAGES;
  }
}
