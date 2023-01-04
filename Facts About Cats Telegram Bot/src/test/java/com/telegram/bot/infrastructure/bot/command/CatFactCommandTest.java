package com.telegram.bot.infrastructure.bot.command;

import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.CHANGE_LANGUAGE;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.FACT_ABOUT_CATS;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.getReplyKeyboard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.telegram.bot.domain.CatInfo;
import com.telegram.bot.facade.CatInfoFacade;
import com.telegram.bot.infrastructure.bot.utils.MessageUtils;
import com.telegram.bot.infrastructure.service.TranslatesService;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@ExtendWith(SpringExtension.class)
@Import({CatFactCommand.class, MessageUtils.class})
public class CatFactCommandTest {

  private static final String FACT = "RANDOM FACT";
  private static final String URL = "RANDOM URL";
  private static final String FACT_ABOUT_CATS_DB = "fact_about_cats";
  private static final String CHANGE_LANGUAGE_DB = "change_language";

  @MockBean
  private CatInfoFacade catFacade;
  @MockBean
  private TranslatesService translatesService;
  @Inject
  private CatFactCommand catFactCommand;

  @Test
  void shouldReturnSendMessage() {
    final CatInfo catInfo = new CatInfo(FACT, URL);
    final long chatId = 10;
    final String language = "English";
    final Update update = mock(Update.class);
    final Message message = mock(Message.class);

    when(update.getMessage()).thenReturn(message);
    when(message.getChatId()).thenReturn(chatId);
    when(translatesService.getTranslateBy(language, FACT_ABOUT_CATS_DB)).thenReturn(FACT_ABOUT_CATS);
    when(translatesService.getTranslateBy(language, CHANGE_LANGUAGE_DB)).thenReturn(CHANGE_LANGUAGE);
    when(catFacade.getFactAboutCats(anyString())).thenReturn(catInfo);

    final PartialBotApiMethod<?> result = catFactCommand.execute(update, language);

    assertThat(result).isExactlyInstanceOf(SendPhoto.class);
    final SendPhoto sendPhoto = (SendPhoto) result;
    assertThat(sendPhoto.getChatId()).isEqualTo(String.valueOf(chatId));
    assertThat(sendPhoto.getPhoto().getAttachName()).isEqualTo(URL);
    assertThat(sendPhoto.getCaption()).isEqualTo(FACT);
    assertThat(sendPhoto.getReplyMarkup()).isEqualTo(getReplyKeyboard(2, 1, FACT_ABOUT_CATS, CHANGE_LANGUAGE));
  }


  @Test
  void shouldReturnCommandName() {
    final String result = catFactCommand.getCommand();
    assertThat(result).isEqualTo(FACT_ABOUT_CATS);
  }
}
