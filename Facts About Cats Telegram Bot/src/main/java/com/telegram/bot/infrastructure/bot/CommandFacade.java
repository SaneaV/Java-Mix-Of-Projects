package com.telegram.bot.infrastructure.bot;

import static com.telegram.bot.infrastructure.bot.utils.Language.English;
import static com.telegram.bot.infrastructure.bot.utils.LanguageUtils.getLanguage;
import static com.telegram.bot.infrastructure.bot.utils.LanguageUtils.isAcceptableLanguage;

import com.telegram.bot.infrastructure.bot.command.SelectLanguageCommand;
import com.telegram.bot.infrastructure.bot.command.StartCommand;
import com.telegram.bot.infrastructure.bot.command.UnknownCommand;
import com.telegram.bot.infrastructure.service.UserPreferencesService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CommandFacade {

  private final List<Command> commandList;
  private final UnknownCommand unknownCommand;
  private final SelectLanguageCommand selectLanguageCommand;
  private final StartCommand startCommand;
  private final UserPreferencesService userPreferencesService;

  public PartialBotApiMethod<?> processCommand(Update update) {
    final Message message = update.getMessage();
    final String messageText = message.getText();
    final Long userId = message.getFrom().getId();
    if (!userPreferencesService.userPreferencesExist(userId)) {
      if (isAcceptableLanguage(messageText)) {
        final String language = getLanguage(messageText);
        userPreferencesService.saveUserPreferences(userId, language);
        return startCommand.execute(update, language);
      }
      return selectLanguageCommand.execute(update, English.name());
    }

    final String language = userPreferencesService.getLanguageByUserId(userId);
    return commandList.stream()
        .filter(command -> command.getCommand().contains(messageText))
        .findFirst()
        .orElse(unknownCommand)
        .execute(update, language);
  }
}
