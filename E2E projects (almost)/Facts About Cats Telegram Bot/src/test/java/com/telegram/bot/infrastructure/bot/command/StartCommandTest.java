package com.telegram.bot.infrastructure.bot.command;

import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.CHANGE_LANGUAGE;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.FACT_ABOUT_CATS;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.getReplyKeyboard;
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
@Import({StartCommand.class, MessageUtils.class})
public class StartCommandTest {

  private static final String START_COMMAND = "/start";
  private static final String WELCOME_MESSAGE = "Привет! Ты находишься в боте, который знает о котиках всё, жми на кнопку \"Факты о кошках\".";
  private static final String DB_TRANSLATION_CODE = "welcome_message";
  private static final String FACT_ABOUT_CATS_DB = "fact_about_cats";
  private static final String CHANGE_LANGUAGE_DB = "change_language";

  @Inject
  private StartCommand startCommand;
  @MockBean
  private TranslatesService translatesService;

  @Test
  void shouldReturnSendMessage() {
    final long chatId = 10;
    final String language = "English";
    final Update update = mock(Update.class);
    final Message message = mock(Message.class);

    when(update.getMessage()).thenReturn(message);
    when(message.getChatId()).thenReturn(chatId);
    when(translatesService.getTranslateBy(language, FACT_ABOUT_CATS_DB)).thenReturn(FACT_ABOUT_CATS);
    when(translatesService.getTranslateBy(language, CHANGE_LANGUAGE_DB)).thenReturn(CHANGE_LANGUAGE);
    when(translatesService.getTranslateBy(language, DB_TRANSLATION_CODE)).thenReturn(WELCOME_MESSAGE);

    final PartialBotApiMethod<?> result = startCommand.execute(update, language);

    assertThat(result).isExactlyInstanceOf(SendMessage.class);
    final SendMessage sendMessage = (SendMessage) result;
    assertThat(sendMessage.getChatId()).isEqualTo(String.valueOf(chatId));
    assertThat(sendMessage.getText()).isEqualTo(WELCOME_MESSAGE);
    assertThat(sendMessage.getReplyMarkup()).isEqualTo(getReplyKeyboard(2, 1, FACT_ABOUT_CATS, CHANGE_LANGUAGE));
  }

  @Test
  void shouldReturnCommandName() {
    final String result = startCommand.getCommand();
    assertThat(result).isEqualTo(START_COMMAND);
  }
}
