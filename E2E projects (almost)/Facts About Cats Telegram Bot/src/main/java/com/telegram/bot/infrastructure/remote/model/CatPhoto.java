package com.telegram.bot.infrastructure.remote.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CatPhoto {

  private final String url;

  @JsonCreator
  public CatPhoto(@JsonProperty("id") String id,
      @JsonProperty("url") String url,
      @JsonProperty("width") String width,
      @JsonProperty("height") String height) {
    this.url = url;
  }

  public CatPhoto(String url) {
    this.url = url;
  }
}
