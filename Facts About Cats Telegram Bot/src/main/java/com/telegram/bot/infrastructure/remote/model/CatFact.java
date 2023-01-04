package com.telegram.bot.infrastructure.remote.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;

@Getter
public class CatFact {

  private final List<String> data;

  @JsonCreator
  public CatFact(@JsonProperty("data") List<String> data) {
    this.data = data;
  }
}
