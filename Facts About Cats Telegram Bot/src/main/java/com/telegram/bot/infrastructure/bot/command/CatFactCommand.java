package com.telegram.bot.infrastructure.bot.command;

import static com.telegram.bot.infrastructure.bot.utils.MessageUtils.sendPhotoWithFactAboutCats;

import com.telegram.bot.domain.CatInfo;
import com.telegram.bot.facade.CatInfoFacade;
import com.telegram.bot.infrastructure.bot.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CatFactCommand implements Command {

  private static final String FACT_ABOUT_CATS = "Fact about cats - Факт о кошках - Факт про кішок";
  private final CatInfoFacade catFactFacade;

  @Override
  public PartialBotApiMethod<?> execute(Update update, String language) {
    final CatInfo factAboutCat = catFactFacade.getFactAboutCats(language);
    return sendPhotoWithFactAboutCats(getChatId(update), factAboutCat.getFact(), factAboutCat.getPhotoUrl(), language);
  }

  @Override
  public String getCommand() {
    return FACT_ABOUT_CATS;
  }
}
