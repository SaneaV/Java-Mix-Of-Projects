package com.telegram.bot.infrastructure.bot.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Language {
  Russian("Russian - Русский - Російська", "rus"),
  Ukrainian("Ukrainian - Украинский - Українська", "ukr"),
  English("English - Английский - Англійська", "eng");

  private final String buttonText;
  private final String shortCode;
}
