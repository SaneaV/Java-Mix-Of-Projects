package com.telegram.bot.infrastructure.remote.impl;

import static java.util.Collections.singletonList;

import com.telegram.bot.infrastructure.remote.CatFactRepository;
import com.telegram.bot.infrastructure.remote.model.CatFact;
import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
public class CatFactRepositoryImpl implements CatFactRepository {

  private static final String EMPTY = "";
  private static final String LANG = "lang";

  private final String catFactsPath;
  private final WebClient webClient;

  public CatFactRepositoryImpl(WebClient webClient,
      @Value("${cat.facts.path}") String catFactsPath) {
    this.webClient = webClient;
    this.catFactsPath = catFactsPath;
  }

  public String getFactAboutCat(String language) {
    final URI uri = UriComponentsBuilder.fromUriString(catFactsPath)
        .queryParam(LANG, language)
        .build().toUri();
    return webClient.get()
        .uri(uri)
        .retrieve()
        .bodyToMono(CatFact.class)
        .switchIfEmpty(Mono.just(new CatFact(singletonList(EMPTY))))
        .map(catFact -> catFact.getData().get(0))
        .block();
  }
}
