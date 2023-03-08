package com.telegram.bot.infrastructure.remote.impl;

import static java.util.Collections.singletonList;

import com.telegram.bot.infrastructure.remote.CatPhotoRepository;
import com.telegram.bot.infrastructure.remote.model.CatPhoto;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class CatPhotoRepositoryImpl implements CatPhotoRepository {

  private static final String EMPTY = "";

  private final String catPhotoPath;
  private final WebClient webClient;

  public CatPhotoRepositoryImpl(WebClient webClient,
      @Value("${cat.photo.path}") String catPhotoPath) {
    this.webClient = webClient;
    this.catPhotoPath = catPhotoPath;
  }

  @Override
  public String getPhotoOfCat() {
    return webClient.get()
        .uri(catPhotoPath)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<List<CatPhoto>>() {
        })
        .switchIfEmpty(Mono.just(singletonList(new CatPhoto(EMPTY))))
        .map(catPhotos -> catPhotos.get(0).getUrl())
        .block();
  }
}
