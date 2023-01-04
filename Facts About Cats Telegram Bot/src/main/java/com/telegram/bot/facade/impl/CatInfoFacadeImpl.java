package com.telegram.bot.facade.impl;

import static com.telegram.bot.infrastructure.bot.utils.LanguageUtils.getParamLanguage;
import static com.telegram.bot.infrastructure.utils.InputStreamReaderUtils.readFromInputStream;
import static org.springframework.util.StringUtils.hasText;

import com.telegram.bot.domain.CatInfo;
import com.telegram.bot.facade.CatInfoFacade;
import com.telegram.bot.infrastructure.remote.CatFactRepository;
import com.telegram.bot.infrastructure.remote.CatPhotoRepository;
import com.telegram.bot.infrastructure.service.TranslatesService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CatInfoFacadeImpl implements CatInfoFacade {

  private static final String EMPTY = "";
  private static final String DB_TRANSLATION = "no_facts_about_cats";
  private static final String NO_FACTS_PHOTO = "https://cdnstatic.rg.ru/uploads/images/135/37/51/ponchik-1000.jpg";
  private static final String FORBIDDEN_FACTS_FILE = "CatFactsFilter.txt";
  private static String forbiddenFacts;

  private final TranslatesService translatesService;

  static {
    final ClassLoader classLoader = CatInfoFacadeImpl.class.getClassLoader();
    try {
      forbiddenFacts = readFromInputStream(classLoader.getResourceAsStream(FORBIDDEN_FACTS_FILE));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private final CatFactRepository catFactRepository;
  private final CatPhotoRepository catPhotoRepository;

  @Override
  public CatInfo getFactAboutCats(String language) {
    final String factAboutCat = getFilteredFactAboutCats(language);
    final String photoOfCat = catPhotoRepository.getPhotoOfCat();

    if (!hasText(factAboutCat) || !hasText(photoOfCat)) {
      return new CatInfo(translatesService.getTranslateBy(language, DB_TRANSLATION), NO_FACTS_PHOTO);
    }

    return new CatInfo(factAboutCat, photoOfCat);
  }

  private String getFilteredFactAboutCats(String language) {
    final String paramLanguage = getParamLanguage(language);
    String factAboutCat;
    do {
      factAboutCat = catFactRepository.getFactAboutCat(paramLanguage);
    } while (forbiddenFacts.contains(factAboutCat) && !factAboutCat.equalsIgnoreCase(EMPTY));

    return factAboutCat;
  }
}
