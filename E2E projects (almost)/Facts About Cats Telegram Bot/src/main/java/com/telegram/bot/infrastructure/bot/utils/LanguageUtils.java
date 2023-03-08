package com.telegram.bot.infrastructure.bot.utils;

import static lombok.AccessLevel.PRIVATE;

import java.util.EnumSet;
import java.util.Set;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class LanguageUtils {

  private static final String EMPTY = "";
  private static final String SPACE = " ";
  private static final Set<Language> languages = EnumSet.allOf(Language.class);

  public static boolean isAcceptableLanguage(String text) {
    final String languageFromMessage = getLanguageFromMessage(text);
    return languages.stream()
        .map(Language::getButtonText)
        .anyMatch(buttonText -> buttonText.contains(languageFromMessage));
  }

  public static String getLanguage(String text) {
    return getLanguageEnum(text).name();
  }

  public static String getParamLanguage(String text) {
    return getLanguageEnum(text).getShortCode();
  }

  private static Language getLanguageEnum(String text) {
    final String languageFromMessage = getLanguageFromMessage(text);
    return languages.stream()
        .filter(language -> language.getButtonText().contains(languageFromMessage))
        .findAny()
        .get();
  }

  private static String getLanguageFromMessage(String text) {
    final String[] splitMessage = text.split(SPACE);
    return splitMessage.length >=1 ? splitMessage[0] : EMPTY;
  }
}
