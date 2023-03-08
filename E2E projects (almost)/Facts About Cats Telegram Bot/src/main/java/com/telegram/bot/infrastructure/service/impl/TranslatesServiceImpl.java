package com.telegram.bot.infrastructure.service.impl;

import static com.telegram.bot.infrastructure.bot.utils.Language.English;
import static com.telegram.bot.infrastructure.bot.utils.Language.Russian;
import static com.telegram.bot.infrastructure.bot.utils.Language.Ukrainian;

import com.telegram.bot.infrastructure.jpa.model.Translates;
import com.telegram.bot.infrastructure.jpa.repository.TranslatesRepository;
import com.telegram.bot.infrastructure.service.TranslatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TranslatesServiceImpl implements TranslatesService {

  private static final String LANGUAGE_NOT_FOUND = "Required language not found";

  private final TranslatesRepository translatesRepository;

  @Override
  public String getTranslateBy(String language, String shortCode) {
    final Translates translates = translatesRepository.getReferenceById(shortCode);
    return getTranslate(translates, language);
  }

  private String getTranslate(Translates translates, String language) {
    if (language.equalsIgnoreCase(English.name())) {
      return translates.getEng();
    } else if (language.equalsIgnoreCase(Russian.name())) {
      return translates.getRus();
    } else if (language.equalsIgnoreCase(Ukrainian.name())) {
      return translates.getUkr();
    } else {
      throw new RuntimeException(LANGUAGE_NOT_FOUND);
    }
  }
}
