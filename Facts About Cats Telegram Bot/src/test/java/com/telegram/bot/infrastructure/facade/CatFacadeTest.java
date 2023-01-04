//package com.telegram.bot.infrastructure.facade;
//
//import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.EMPTY;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.atMostOnce;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import com.telegram.bot.domain.CatInfo;
//import com.telegram.bot.facade.impl.CatInfoFacadeImpl;
//import com.telegram.bot.infrastructure.remote.CatFactRepository;
//import com.telegram.bot.infrastructure.remote.CatPhotoRepository;
//import javax.inject.Inject;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
//@Import(CatInfoFacadeImpl.class)
//public class CatFacadeTest {
//
//  private static final String FORBIDDEN_FACT = "Неверная команда, CatFacts!";
//  private static final String CAT_FACT = "Кошки не чувствуют вкус сладкого";
//  private static final String NO_FACTS_TEXT = "Факты о котиках закончились :(";
//  private static final String NO_FACTS_PHOTO = "https://cdnstatic.rg.ru/uploads/images/135/37/51/ponchik-1000.jpg";
//  private static final String URL = "Random-Url";
//
//  @MockBean
//  private CatFactRepository catFactRepository;
//  @MockBean
//  private CatPhotoRepository catPhotoRepository;
//  @Inject
//  private CatInfoFacadeImpl catFactFacade;
//
//  @Test
//  void shouldReturnFactAboutCat() {
//    when(catFactRepository.getFactAboutCat()).thenReturn(CAT_FACT);
//    when(catPhotoRepository.getPhotoOfCat()).thenReturn(URL);
//
//    final CatInfo result = catFactFacade.getFactAboutCats();
//
//    verify(catFactRepository, atMostOnce()).getFactAboutCat();
//    verify(catPhotoRepository, atMostOnce()).getPhotoOfCat();
//
//    assertThat(result.getFact()).isEqualTo(CAT_FACT);
//    assertThat(result.getPhotoUrl()).isEqualTo(URL);
//  }
//
//  @Test
//  void shouldReturnNoFactsAboutCat() {
//    when(catFactRepository.getFactAboutCat()).thenReturn(EMPTY);
//    when(catPhotoRepository.getPhotoOfCat()).thenReturn(EMPTY);
//
//    final CatInfo result = catFactFacade.getFactAboutCats();
//
//    verify(catFactRepository, atMostOnce()).getFactAboutCat();
//    verify(catPhotoRepository, atMostOnce()).getPhotoOfCat();
//
//    assertThat(result.getFact()).isEqualTo(NO_FACTS_TEXT);
//    assertThat(result.getPhotoUrl()).isEqualTo(NO_FACTS_PHOTO);
//  }
//
//  @Test
//  void shouldFilterFactAndReturnRightFact() {
//    when(catFactRepository.getFactAboutCat()).thenReturn(FORBIDDEN_FACT, CAT_FACT);
//    when(catPhotoRepository.getPhotoOfCat()).thenReturn(URL);
//
//    final CatInfo result = catFactFacade.getFactAboutCats();
//
//    verify(catFactRepository, times(2)).getFactAboutCat();
//    verify(catPhotoRepository, atMostOnce()).getPhotoOfCat();
//
//    assertThat(result.getFact()).isEqualTo(CAT_FACT);
//    assertThat(result.getPhotoUrl()).isEqualTo(URL);
//  }
//}
