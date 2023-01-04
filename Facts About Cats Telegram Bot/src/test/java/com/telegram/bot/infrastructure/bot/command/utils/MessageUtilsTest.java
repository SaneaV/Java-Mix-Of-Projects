package com.telegram.bot.infrastructure.bot.command.utils;

import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.CHANGE_LANGUAGE;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.ENGLISH;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.FACT_ABOUT_CATS;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.RUSSIAN;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.UKRAINIAN;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.getReplyKeyboard;
import static com.telegram.bot.infrastructure.bot.utils.Language.English;
import static com.telegram.bot.infrastructure.bot.utils.Language.Russian;
import static com.telegram.bot.infrastructure.bot.utils.Language.Ukrainian;
import static com.telegram.bot.infrastructure.bot.utils.MessageUtils.sendMessageWithFactAboutCats;
import static com.telegram.bot.infrastructure.bot.utils.MessageUtils.sendMessageWithSelectYourLanguage;
import static com.telegram.bot.infrastructure.bot.utils.MessageUtils.sendPhotoWithFactAboutCats;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.telegram.bot.infrastructure.bot.utils.MessageUtils;
import com.telegram.bot.infrastructure.service.TranslatesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;

@ExtendWith(SpringExtension.class)
@Import(MessageUtils.class)
public class MessageUtilsTest {

  private static final String FACT_ABOUT_CATS_DB = "fact_about_cats";
  private static final String CHANGE_LANGUAGE_DB = "change_language";
  private static final String ENGLISH_DB = "english";
  private static final String RUSSIAN_DB = "russian";
  private static final String UKRAINIAN_DB = "ukrainian";

  @MockBean
  private TranslatesService translatesService;

  @Test
  void shouldCreateSendMessageWithReplyKeyboard() {
    final String chatId = "10";
    final String text = "RandomText";
    final String language = "English";

    when(translatesService.getTranslateBy(language, FACT_ABOUT_CATS_DB)).thenReturn(FACT_ABOUT_CATS);
    when(translatesService.getTranslateBy(language, CHANGE_LANGUAGE_DB)).thenReturn(CHANGE_LANGUAGE);

    final SendMessage sendMessage = sendMessageWithFactAboutCats(chatId, text, language);

    assertThat(sendMessage.getChatId()).isEqualTo(chatId);
    assertThat(sendMessage.getText()).isEqualTo(text);
    assertThat(sendMessage.getReplyMarkup()).isEqualTo(getReplyKeyboard(2, 1, FACT_ABOUT_CATS, CHANGE_LANGUAGE));
  }

  @Test
  void shouldCreateSendPhotoWithReplyKeyboard() {
    final String chatId = "10";
    final String text = "RandomText";
    final String photoUrl = "RandomPhotoUrl";
    final String language = "English";

    when(translatesService.getTranslateBy(language, FACT_ABOUT_CATS_DB)).thenReturn(FACT_ABOUT_CATS);
    when(translatesService.getTranslateBy(language, CHANGE_LANGUAGE_DB)).thenReturn(CHANGE_LANGUAGE);

    final SendPhoto sendPhoto = sendPhotoWithFactAboutCats(chatId, text, photoUrl, language);

    assertThat(sendPhoto.getChatId()).isEqualTo(chatId);
    assertThat(sendPhoto.getPhoto()).isEqualTo(new InputFile(photoUrl));
    assertThat(sendPhoto.getCaption()).isEqualTo(text);
    assertThat(sendPhoto.getReplyMarkup()).isEqualTo(getReplyKeyboard(2, 1, FACT_ABOUT_CATS, CHANGE_LANGUAGE));
  }

  @Test
  void shouldCreateSendMessageWithLanguagesReplyKeyboard() {
    final String chatId = "10";
    final String text = "RandomText";
    final String language = "English";

    when(translatesService.getTranslateBy(language, ENGLISH_DB)).thenReturn(English.name());
    when(translatesService.getTranslateBy(language, RUSSIAN_DB)).thenReturn(Russian.name());
    when(translatesService.getTranslateBy(language, UKRAINIAN_DB)).thenReturn(Ukrainian.name());

    final SendMessage sendMessage = sendMessageWithSelectYourLanguage(chatId, text, language);

    assertThat(sendMessage.getChatId()).isEqualTo(chatId);
    assertThat(sendMessage.getText()).isEqualTo(text);
    assertThat(sendMessage.getReplyMarkup()).isEqualTo(getReplyKeyboard(3, 1, ENGLISH, RUSSIAN, UKRAINIAN));
  }
}
