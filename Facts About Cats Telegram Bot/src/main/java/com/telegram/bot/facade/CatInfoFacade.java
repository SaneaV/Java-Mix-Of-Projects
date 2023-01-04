package com.telegram.bot.facade;

import com.telegram.bot.domain.CatInfo;

public interface CatInfoFacade {

  CatInfo getFactAboutCats(String language);
}
