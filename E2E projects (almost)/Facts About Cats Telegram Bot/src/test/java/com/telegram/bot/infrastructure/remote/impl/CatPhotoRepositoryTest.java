package com.telegram.bot.infrastructure.remote.impl;

import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.EMPTY;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.telegram.bot.infrastructure.remote.model.CatPhoto;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@Import(CatPhotoRepositoryImpl.class)
public class CatPhotoRepositoryTest {

  private static final String CAT_PHOTO = "RANDOM-URL";

  @MockBean
  private WebClient webClient;
  @Inject
  private CatPhotoRepositoryImpl catPhotoRepository;

  @SuppressWarnings("rawtypes")
  @Mock
  private WebClient.RequestHeadersSpec requestHeadersSpecMock;
  @SuppressWarnings("rawtypes")
  @Mock
  private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;
  @Mock
  private WebClient.ResponseSpec responseSpecMock;

  @BeforeEach
  void setup() {
    catPhotoRepository = new CatPhotoRepositoryImpl(webClient, EMPTY);
  }

  @SuppressWarnings("unchecked")
  @Test
  void shouldReturnStringOnSuccess() {
    final CatPhoto catPhoto = new CatPhoto("id", CAT_PHOTO, "100", "100");

    when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
    when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
    when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
    when(responseSpecMock.bodyToMono(ArgumentMatchers.<ParameterizedTypeReference<List<CatPhoto>>>any())).thenReturn(
        Mono.just(singletonList(catPhoto)));
    final String result = catPhotoRepository.getPhotoOfCat();

    assertThat(result).isEqualTo(CAT_PHOTO);
  }

  @SuppressWarnings("unchecked")
  @Test
  void shouldReturnEmptyStringOnEmptyResponse() {
    final CatPhoto catPhoto = new CatPhoto(EMPTY);

    when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
    when(requestHeadersUriSpecMock.uri(anyString())).thenReturn(requestHeadersSpecMock);
    when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
    when(responseSpecMock.bodyToMono(ArgumentMatchers.<ParameterizedTypeReference<List<CatPhoto>>>any())).thenReturn(
        Mono.just(singletonList(catPhoto)));
    final String result = catPhotoRepository.getPhotoOfCat();

    assertThat(result).isEqualTo(EMPTY);
  }
}
