//package com.telegram.bot.infrastructure.remote.impl;
//
//import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.EMPTY;
//import static java.util.Collections.singletonList;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//import com.telegram.bot.infrastructure.remote.model.CatFact;
//import javax.inject.Inject;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.Mock;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@ExtendWith(SpringExtension.class)
//@Import(CatFactRepositoryImpl.class)
//public class CatFactRepositoryTest {
//
//  private static final String CAT_FACT = "Кошки не чувствуют вкус сладкого";
//
//  @MockBean
//  private WebClient webClient;
//  @Inject
//  private CatFactRepositoryImpl catFactRepository;
//
//  @SuppressWarnings("rawtypes")
//  @Mock
//  private WebClient.RequestHeadersSpec requestHeadersSpecMock;
//  @SuppressWarnings("rawtypes")
//  @Mock
//  private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;
//  @Mock
//  private WebClient.ResponseSpec responseSpecMock;
//
//  @SuppressWarnings("unchecked")
//  @Test
//  void shouldReturnStringOnSuccess() {
//    final CatFact catFact = new CatFact(singletonList(CAT_FACT));
//    when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
//    when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
//    when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
//    when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<CatFact>>notNull())).thenReturn(Mono.just(catFact));
//
//    final String result = catFactRepository.getFactAboutCat();
//
//    assertThat(result).isEqualTo(CAT_FACT);
//  }
//
//  @SuppressWarnings("unchecked")
//  @Test
//  void shouldReturnEmptyStringOnEmptyResponse() {
//    final CatFact catFact = new CatFact(singletonList(EMPTY));
//    when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
//    when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
//    when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
//    when(responseSpecMock.bodyToMono(ArgumentMatchers.<Class<CatFact>>notNull())).thenReturn(Mono.just(catFact));
//
//    final String result = catFactRepository.getFactAboutCat();
//
//    assertThat(result).isEqualTo(EMPTY);
//  }
//}
