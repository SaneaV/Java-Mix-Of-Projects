package com.telegram.bot.infrastructure.bot.command;

import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.ENGLISH;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.RUSSIAN;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.UKRAINIAN;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.getReplyKeyboard;
import static com.telegram.bot.infrastructure.bot.utils.Language.English;
import static com.telegram.bot.infrastructure.bot.utils.Language.Russian;
import static com.telegram.bot.infrastructure.bot.utils.Language.Ukrainian;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.telegram.bot.infrastructure.bot.utils.MessageUtils;
import com.telegram.bot.infrastructure.service.TranslatesService;
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

@ExtendWith(SpringExtension.class)
@Import({SelectLanguageCommand.class, MessageUtils.class})
public class SelectLanguageCommandTest {

  private static final String SELECT_LANGUAGE = "Change language - Изменить язык - Змінити мову";
  private static final String CHOOSE_YOUR_LANGUAGE = "Пожалуйста, выберите язык, на котором вы хотите общаться с ботом.";
  private static final String ENGLISH_DB = "english";
  private static final String RUSSIAN_DB = "russian";
  private static final String UKRAINIAN_DB = "ukrainian";
  private static final String DB_TRANSLATION_CODE = "chose_your_language";

  @MockBean
  private TranslatesService translatesService;
  @Inject
  private SelectLanguageCommand selectLanguageCommand;

  @Test
  void shouldReturnSendMessage() {
    final long chatId = 10;
    final String language = "English";
    final Update update = mock(Update.class);
    final Message message = mock(Message.class);

    when(update.getMessage()).thenReturn(message);
    when(message.getChatId()).thenReturn(chatId);
    when(translatesService.getTranslateBy(language, DB_TRANSLATION_CODE)).thenReturn(CHOOSE_YOUR_LANGUAGE);
    when(translatesService.getTranslateBy(language, ENGLISH_DB)).thenReturn(English.name());
    when(translatesService.getTranslateBy(language, RUSSIAN_DB)).thenReturn(Russian.name());
    when(translatesService.getTranslateBy(language, UKRAINIAN_DB)).thenReturn(Ukrainian.name());

    final PartialBotApiMethod<?> result = selectLanguageCommand.execute(update, language);

    assertThat(result).isExactlyInstanceOf(SendMessage.class);
    final SendMessage sendMessage = (SendMessage) result;
    assertThat(sendMessage.getChatId()).isEqualTo(String.valueOf(chatId));
    assertThat(sendMessage.getText()).isEqualTo(CHOOSE_YOUR_LANGUAGE);
    assertThat(sendMessage.getReplyMarkup()).isEqualTo(getReplyKeyboard(3, 1, ENGLISH, RUSSIAN, UKRAINIAN));
  }

  @Test
  void shouldReturnCommandName() {
    final String result = selectLanguageCommand.getCommand();
    assertThat(result).isEqualTo(SELECT_LANGUAGE);
  }

}
