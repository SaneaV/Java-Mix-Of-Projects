package com.telegram.bot.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CatInfo {

  private final String fact;
  private final String photoUrl;
}
