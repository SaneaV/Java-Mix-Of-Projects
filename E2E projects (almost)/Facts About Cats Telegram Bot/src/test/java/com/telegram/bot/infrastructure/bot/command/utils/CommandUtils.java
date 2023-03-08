package com.telegram.bot.infrastructure.bot.command.utils;

import static lombok.AccessLevel.PRIVATE;

import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

@NoArgsConstructor(access = PRIVATE)
public final class CommandUtils {

  public static final String FACT_ABOUT_CATS = "Fact about cats - Факт о кошках - Факт про кішок";
  public static final String CHANGE_LANGUAGE = "Change language";
  public static final String RUSSIAN = "Russian \uD83C\uDDF7\uD83C\uDDFA";
  public static final String UKRAINIAN = "Ukrainian \uD83C\uDDFA\uD83C\uDDE6";
  public static final String ENGLISH = "English \uD83C\uDDFA\uD83C\uDDF8";
  public static final String EMPTY = "";

  public static ReplyKeyboard getReplyKeyboard(int numberOfButtons, int buttonsPerRow, String... text) {
    final List<KeyboardRow> rows = new ArrayList<>();

    for (int i = 0, k = 0; i < numberOfButtons; i++) {
      final List<KeyboardButton> buttons = new ArrayList<>();
      for (int j = 0; j < buttonsPerRow; j++) {

        final String textForButton = text[k++];

        final KeyboardButton keyboardButton = new KeyboardButton(textForButton);
        buttons.add(keyboardButton);
      }
      final KeyboardRow keyboardRow = new KeyboardRow(buttons);
      rows.add(keyboardRow);
    }

    return new ReplyKeyboardMarkup(rows);
  }
}
