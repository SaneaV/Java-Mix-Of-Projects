package com.telegram.bot.infrastructure.bot.utils;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.util.StringUtils.hasText;

import com.telegram.bot.infrastructure.service.TranslatesService;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

@Component
@NoArgsConstructor(access = PRIVATE)
public class MessageUtils {

  private static final String FACT_ABOUT_CATS = "fact_about_cats";
  private static final String CHANGE_LANGUAGE = "change_language";
  private static final String ENGLISH = "english";
  private static final String RUSSIAN = "russian";
  private static final String UKRAINIAN = "ukrainian";
  private static final String NUMBER_OF_BUTTONS_NOT_EQUAL_TO_NUMBER_OF_TEXTS = "Number of buttons not equal to number of texts";
  private static final String TEXT_FOR_BUTTON_IS_EMPTY_OR_NULL = "Text for button is empty or null";

  private static TranslatesService translatesService;

  @Autowired
  public TranslatesService setTranslatesService(TranslatesService translatesService) {
    return MessageUtils.translatesService = translatesService;
  }

  public static SendMessage sendMessageWithFactAboutCats(String chatId, String text, String language) {
    return SendMessage.builder()
        .chatId(chatId)
        .text(text)
        .replyMarkup(getReplyKeyboard(2, 1, translatesService.getTranslateBy(language, FACT_ABOUT_CATS),
            translatesService.getTranslateBy(language, CHANGE_LANGUAGE)))
        .build();
  }

  public static SendPhoto sendPhotoWithFactAboutCats(String chatId, String text, String photoUrl, String language) {
    return SendPhoto.builder()
        .chatId(chatId)
        .photo(new InputFile(photoUrl))
        .caption(text)
        .replyMarkup(getReplyKeyboard(2, 1, translatesService.getTranslateBy(language, FACT_ABOUT_CATS),
            translatesService.getTranslateBy(language, CHANGE_LANGUAGE)))
        .build();
  }

  public static SendMessage sendMessageWithSelectYourLanguage(String chatId, String text, String language) {
    return SendMessage.builder()
        .chatId(chatId)
        .text(text)
        .replyMarkup(getReplyKeyboard(3, 1,
            translatesService.getTranslateBy(language, ENGLISH) + " \uD83C\uDDFA\uD83C\uDDF8",
            translatesService.getTranslateBy(language, RUSSIAN) + " \uD83C\uDDF7\uD83C\uDDFA",
            translatesService.getTranslateBy(language, UKRAINIAN) + " \uD83C\uDDFA\uD83C\uDDE6"))
        .build();
  }

  private static ReplyKeyboardMarkup getReplyKeyboard(int numberOfButtons, int buttonsPerRow, String... text) {

    if (numberOfButtons != text.length) {
      throw new RuntimeException(NUMBER_OF_BUTTONS_NOT_EQUAL_TO_NUMBER_OF_TEXTS);
    }

    final List<KeyboardRow> rows = new ArrayList<>();

    for (int i = 0, k = 0; i < numberOfButtons; i++) {
      final List<KeyboardButton> buttons = new ArrayList<>();
      for (int j = 0; j < buttonsPerRow; j++) {

        final String textForButton = text[k++];

        if (!hasText(textForButton)) {
          throw new RuntimeException(TEXT_FOR_BUTTON_IS_EMPTY_OR_NULL);
        }

        final KeyboardButton keyboardButton = new KeyboardButton(textForButton);
        buttons.add(keyboardButton);
      }
      final KeyboardRow keyboardRow = new KeyboardRow(buttons);
      rows.add(keyboardRow);
    }

    return new ReplyKeyboardMarkup(rows);
  }
}
