package com.telegram.bot.infrastructure.remote.impl;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.telegram.bot.infrastructure.bot.command.utils.CommandUtils.EMPTY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.telegram.bot.infrastructure.bot.config.TestConfig;
import com.telegram.bot.infrastructure.remote.CatPhotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import({CatPhotoRepositoryImpl.class, TestConfig.class})
@TestPropertySource(properties = "cat.photo.path=/")
public class CatPhotoRepositoryIT {

  private static final String PATH = "/";
  private static final String CONTENT_TYPE = "Content-Type";
  private static final String STRING_RESPONSE = "https://cdn2.thecatapi.com/images/73p.jpg";
  private static final String RESPONSE_FROM_ENDPOINT = "[\n"
      + "    {\n"
      + "        \"id\": \"73p\",\n"
      + "        \"url\": \"https://cdn2.thecatapi.com/images/73p.jpg\",\n"
      + "        \"width\": 500,\n"
      + "        \"height\": 375\n"
      + "    }\n"
      + "]";

  @Autowired
  private WireMockServer wireMockServer;
  @Autowired
  private CatPhotoRepository catPhotoRepository;

  @Test
  void shouldReturnStringOnSuccess() {
    wireMockServer.stubFor(get(urlEqualTo(PATH))
        .willReturn(aResponse()
            .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .withBody(RESPONSE_FROM_ENDPOINT)));

    final String catPhotoUrl = catPhotoRepository.getPhotoOfCat();

    assertThat(catPhotoUrl).isEqualTo(STRING_RESPONSE);
  }

  @Test
  void shouldReturnEmptyStringIfEmptyBody() {
    wireMockServer.stubFor(get(urlEqualTo(PATH))
        .willReturn(aResponse()
            .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .withBody(EMPTY)));

    final String catPhotoUrl = catPhotoRepository.getPhotoOfCat();

    assertThat(catPhotoUrl).isEqualTo(EMPTY);
  }
}
